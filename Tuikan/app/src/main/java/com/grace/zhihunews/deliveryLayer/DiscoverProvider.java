package com.grace.zhihunews.deliveryLayer;

import com.grace.zhihunews.App;
import com.grace.zhihunews.cache.ACache;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.event.RecBooksLoadEvent;
import com.grace.zhihunews.network.RetrofitFactory;
import com.grace.zhihunews.network.entity.RecBook;
import com.grace.zhihunews.network.entity.RecommondBooks;
import com.grace.zhihunews.network.entity.TopStory;
import com.grace.zhihunews.network.service.ZhifuService;

import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/1.
 */
public class DiscoverProvider implements IDiscoverProvider {

    public static final String RecommondBooks_FILE_NAME = "recommondBooks_FILE_NAME";

    private App app;
    private ACache cache;
    private ZhifuService zhifuService;

    public DiscoverProvider(App app) {
        this.app = app;
        cache = app.getCacheInstance();
        zhifuService = RetrofitFactory.getZhifuService();
    }

    @Override
    public void getRecBooks(){
        Call<RecommondBooks> call = zhifuService.getRecBooks();
        call.enqueue(new Callback<RecommondBooks>() {
            @Override
            public void onResponse(Call<RecommondBooks> call, Response<RecommondBooks> response) {
                List<TopStory> topStories = response.body().getTopStories();
                List<RecBook> recBooks = response.body().getRecBooks();
                RecommondBooks recommondBooks = new RecommondBooks(recBooks, topStories);
                EventBus.getDefault().post(new RecBooksLoadEvent(recommondBooks));
            }
            @Override
            public void onFailure(Call<RecommondBooks> call, Throwable t) {
                EventBus.getDefault().post(new LoadFailureEvent("请检查联网设置"));
            }
        });
        }

    @Override
    public void refreshData() {
//        DBHelper.getInstance(app).deleteAllBook();
    }
}
