package com.grace.zhihunews.PresenterCompl;

import com.grace.zhihunews.App;
import com.grace.zhihunews.contract.GirlsContact;
import com.grace.zhihunews.deliveryLayer.GirlsProvider;
import com.grace.zhihunews.event.BeforeNewsLoadedEvent;
import com.grace.zhihunews.event.GotoNewsDetailEvent;
import com.grace.zhihunews.event.LatestNewsLoadedEvent;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.event.TopStoriesLoadedEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/9/2.
 */
public class GirlsPresenterCompl implements GirlsContact.IGirlsPresenter {

    private App app;
    private GirlsContact.IGirlsView mIGirlsView;
    private GirlsProvider girlsProvider;

    public GirlsPresenterCompl(App app, GirlsContact.IGirlsView iGirlsView) {
        this.app = app;
        mIGirlsView = iGirlsView;
        girlsProvider = new GirlsProvider(app);

        EventBus.getDefault().register(this);
    }

    @Override
    public void loadLatestNews() {
        girlsProvider.getLatestNews();
    }

    @Override
    public void loadBeforeNews(String date) {
        girlsProvider.getBeforeNews(date);
    }


    @Override
    public void refreshData() {
        girlsProvider.refreshData();
    }


    //EventBus的onEvent
    public void onEvent(LatestNewsLoadedEvent event) {
        mIGirlsView.showLatestNews(event.latestNews);
    }

    public void onEvent(BeforeNewsLoadedEvent event) {
        mIGirlsView.showBeforeNews(event.beforeNews);
    }

    public void onEvent(TopStoriesLoadedEvent event) {
        //mNewsListView.showTopStories(event.topstories);
    }

    public void onEvent(LoadFailureEvent event) {
        mIGirlsView.showLoadFailureMsg(event.errorMsg);
    }

    public void onEvent(GotoNewsDetailEvent event) {
        mIGirlsView.gotoNewsDetailActivity(event.id);
    }

}
