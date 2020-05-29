package com.grace.zhihunews.deliveryLayer;


import com.grace.zhihunews.App;
import com.grace.zhihunews.cache.ACache;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.event.BooksDetailLoadedEvent;
import com.grace.zhihunews.network.RetrofitFactory;
import com.grace.zhihunews.network.entity.BooksDetail;
import com.grace.zhihunews.network.service.ZhifuService;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/1.
 */
public class BooksDetailProvider implements IBooksDetailProvider {

    private App app;
    private ACache cache;
    private ZhifuService zhifuService;

    public BooksDetailProvider(App app) {
        this.app = app;
        cache = app.getCacheInstance();
        zhifuService = RetrofitFactory.getZhifuService();
    }

    @Override
    public void getBooksDetail(final int id){
        Call<BooksDetail> call = zhifuService.getBooksDetail(id);
        call.enqueue(new Callback<BooksDetail>() {
            @Override
            public void onResponse(Call<BooksDetail> call, Response<BooksDetail> response) {
                EventBus.getDefault().post(new BooksDetailLoadedEvent(response.body()));
            }

            @Override
            public void onFailure(Call<BooksDetail> call, Throwable t) {
                EventBus.getDefault().post(new LoadFailureEvent("内容加载失败"));
            }
        });
    }

    private void saveBooksDetailToCache(int id, BooksDetail booksDetail) {
        cache.put(Integer.toString(id), booksDetail, ACache.TIME_DAY * 14);
    }

    private BooksDetail readBooksDetailFromCache(int id) {
        BooksDetail booksDetail = (BooksDetail) cache.getAsObject(Integer.toString(id));
        return booksDetail;
    }

}
