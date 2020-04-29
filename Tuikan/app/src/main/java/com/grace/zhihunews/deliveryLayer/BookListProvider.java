package com.grace.zhihunews.deliveryLayer;

import com.grace.zhihunews.App;
import com.grace.zhihunews.cache.ACache;
import com.grace.zhihunews.cache.DBHelper;
import com.grace.zhihunews.event.BooksLoadedEvent;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.network.RetrofitFactory;
import com.grace.zhihunews.network.entity.Book;
import com.grace.zhihunews.network.entity.LoadBooks;
import com.grace.zhihunews.network.service.ZhifuService;



import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/1.
 */
public class BookListProvider implements IBookListProvider {

    public static final String LoadBooks_FILE_NAME = "LoadBooks";

    private App app;
    private ACache cache;
    private ZhifuService zhifuService;

    public BookListProvider(App app) {
        this.app = app;
        cache = app.getCacheInstance();
        zhifuService = RetrofitFactory.getZhifuService();
    }

    @Override
    public void getBooks(){
        List<Book> books = DBHelper.getInstance(app).loadBooks();   //TODO 第二次刷新后size清空，需要解决
        if (books.size() > 0) {
            LoadBooks loadBooks = new LoadBooks(books);
            EventBus.getDefault().post(new BooksLoadedEvent(loadBooks));
        } else {    // 预装
            Call<LoadBooks> call = zhifuService.getPreBooks();
            call.enqueue(new Callback<LoadBooks>() {
                @Override
                public void onResponse(Call<LoadBooks> call, Response<LoadBooks> response) {
                    List<Book> preBooks = response.body().getBooks();
                    for (Book book : preBooks) {
                        DBHelper.getInstance(app).saveBook(book);
                    }
                    EventBus.getDefault().post(new BooksLoadedEvent(response.body()));
                }
                @Override
                public void onFailure(Call<LoadBooks> call, Throwable t) {
                    EventBus.getDefault().post(new LoadFailureEvent("已读书本列表加载失败"));
                }
            });
        }
    }

    @Override
    public void refreshData() {
        DBHelper.getInstance(app).deleteAllBook();
    }
}
