package com.grace.zhihunews.PresenterCompl;

import android.content.Context;

import com.grace.zhihunews.App;
import com.grace.zhihunews.contract.BookListContact;
import com.grace.zhihunews.deliveryLayer.BookListProvider;
import com.grace.zhihunews.deliveryLayer.IBookListProvider;
import com.grace.zhihunews.event.BooksLoadedEvent;
import com.grace.zhihunews.event.GotoBooksPagerEvent;
import com.grace.zhihunews.event.LoadFailureEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/9/1.
 */
public class BookListPresenterCompl implements BookListContact.IBookListPresenter {

    private App app;
    private Context context;
    private BookListContact.IBookListView mBookListView;
    private IBookListProvider mBookListProvider;

    public BookListPresenterCompl(App app, BookListContact.IBookListView newListView, Context context) {
        this.app = app;
        this.context = context;
        mBookListView = newListView;
        mBookListProvider = new BookListProvider(app,context);

        EventBus.getDefault().register(this);
    }

    @Override
    public void loadBook() {
        mBookListProvider.getBooks();
    }

    @Override
    public void refreshData() {
        mBookListProvider.refreshData();
    }


    //EventBus的onEvent
    public void onEvent(BooksLoadedEvent event) {
        mBookListView.showBook(event.loadBooks);
    }


    public void onEvent(LoadFailureEvent event) {
        mBookListView.showLoadFailureMsg(event.errorMsg);
    }

    public void onEvent(GotoBooksPagerEvent event) {
        mBookListView.gotoBooksPagerActivity(event.id);
    }

}
