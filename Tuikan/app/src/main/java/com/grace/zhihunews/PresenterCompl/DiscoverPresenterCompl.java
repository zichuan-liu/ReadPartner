package com.grace.zhihunews.PresenterCompl;

import com.grace.zhihunews.App;
import com.grace.zhihunews.contract.DiscoverContact;
import com.grace.zhihunews.deliveryLayer.DiscoverProvider;
import com.grace.zhihunews.event.BeforeNewsLoadedEvent;
import com.grace.zhihunews.event.GotoNewsDetailEvent;
import com.grace.zhihunews.event.LatestNewsLoadedEvent;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.event.TopStoriesLoadedEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/9/2.
 */
public class DiscoverPresenterCompl implements DiscoverContact.IDiscoverPresenter {

    private App app;
    private DiscoverContact.IDiscoverView mIDiscoverView;
    private DiscoverProvider discoverProvider;

    public DiscoverPresenterCompl(App app, DiscoverContact.IDiscoverView iDiscoverView) {
        this.app = app;
        mIDiscoverView = iDiscoverView;
        discoverProvider = new DiscoverProvider(app);

        EventBus.getDefault().register(this);
    }

    @Override
    public void loadLatestNews() {
        discoverProvider.getLatestNews();
    }

    @Override
    public void loadBeforeNews(String date) {
        discoverProvider.getBeforeNews(date);
    }


    @Override
    public void refreshData() {
        discoverProvider.refreshData();
    }


    //EventBus的onEvent
    public void onEvent(LatestNewsLoadedEvent event) {
        mIDiscoverView.showLatestNews(event.latestNews);
    }

    public void onEvent(BeforeNewsLoadedEvent event) {
        mIDiscoverView.showBeforeNews(event.beforeNews);
    }

    public void onEvent(TopStoriesLoadedEvent event) {
        //mNewsListView.showTopStories(event.topstories);
    }

    public void onEvent(LoadFailureEvent event) {
        mIDiscoverView.showLoadFailureMsg(event.errorMsg);
    }

    public void onEvent(GotoNewsDetailEvent event) {
        mIDiscoverView.gotoNewsDetailActivity(event.id);
    }

}
