package com.grace.zhihunews.PresenterCompl;

import com.grace.zhihunews.App;
import com.grace.zhihunews.contract.BooksDetailContract;
import com.grace.zhihunews.deliveryLayer.BooksDetailProvider;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.event.BooksDetailLoadedEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/9/1.
 */
public class BooksDetailPresenterCompl implements BooksDetailContract.IBooksDetailPresenter {

    private App app;
    private BooksDetailContract.IBooksDetailView mNewsDetailView;
    private BooksDetailProvider mNewsDetailManager;

    public BooksDetailPresenterCompl(App app, BooksDetailContract.IBooksDetailView newsDetailView) {
        this.app = app;
        mNewsDetailView = newsDetailView;
        mNewsDetailManager = new BooksDetailProvider(app);

        EventBus.getDefault().register(this);
    }

    @Override
    public void loadBooksDetail(int id) {
        mNewsDetailManager.getBooksDetail(id);
    }

    public void onEvent(BooksDetailLoadedEvent event) {
        mNewsDetailView.showBooksDetail(event.booksDetail);
    }

    public void onEvent(LoadFailureEvent event) {
        mNewsDetailView.showLoadFailureMsg(event.errorMsg);
    }

}
