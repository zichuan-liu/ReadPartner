package com.grace.zhihunews.PresenterCompl;

import android.util.Log;

import com.grace.zhihunews.App;
import com.grace.zhihunews.contract.DiscoverContact;
import com.grace.zhihunews.contract.WorldContact;
import com.grace.zhihunews.deliveryLayer.DiscoverProvider;
import com.grace.zhihunews.deliveryLayer.WorldProvider;
import com.grace.zhihunews.event.CategoriesLoadedEvent;
import com.grace.zhihunews.event.CategoryFeatureLoadedEvent;
import com.grace.zhihunews.event.CommentsLoadedEvent;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.network.entity.video.Category;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/9/9.
 */
public class WorldPresenterCompl implements WorldContact.IWorldPresenter {

    private App app;
    private WorldContact.IWorldView mIWorldView;
    private WorldProvider worldProvider;

    public WorldPresenterCompl(App app, WorldContact.IWorldView iWorldView) {
        this.app = app;
        mIWorldView = iWorldView;
        worldProvider = new WorldProvider(app);

        EventBus.getDefault().register(this);
    }

    @Override
    public void loadComments() {
        worldProvider.getComments();
    }

    @Override
    public void refreshData() {
        worldProvider.refreshData();
    }

    public void onEvent(CommentsLoadedEvent event) {
        mIWorldView.showComments(event.socialComments);
    }

    public void onEvent(LoadFailureEvent event) {
        mIWorldView.showLoadFailureMsg(event.errorMsg);
    }

}
