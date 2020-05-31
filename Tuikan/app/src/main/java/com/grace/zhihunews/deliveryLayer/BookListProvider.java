package com.grace.zhihunews.deliveryLayer;

import android.content.Context;

import com.grace.zhihunews.App;
import com.grace.zhihunews.R;
import com.grace.zhihunews.cache.ACache;
import com.grace.zhihunews.cache.DBHelper;
import com.grace.zhihunews.event.BooksLoadedEvent;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.network.RetrofitFactory;
import com.grace.zhihunews.network.entity.Book;
import com.grace.zhihunews.network.entity.LoadBooks;
import com.grace.zhihunews.network.service.ZhifuService;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
    private Context context;
    private ZhifuService zhifuService;

    public BookListProvider(App app, Context context) {
        this.app = app;
        this.context = context;
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
                        String fileName = book.getTitle()+".txt";
                        File file = context.getExternalFilesDir(null);
                        try {
                            String rawPath = book.getTxt_path();
                            InputStream inputStream = context.getResources().getAssets().open(rawPath);
                            file = new File(file.getPath()+"/"+fileName);
                            OutputStream outputStream = new FileOutputStream(file);
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
                            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                            String b;
                            while ((b = br.readLine())!=null){
                                bw.write(b);
                            }
                            bw.close();
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        book.setTxt_path(file.getPath());
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
