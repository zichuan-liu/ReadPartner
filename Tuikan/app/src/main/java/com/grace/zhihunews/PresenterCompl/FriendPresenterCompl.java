package com.grace.zhihunews.PresenterCompl;

import com.grace.zhihunews.App;
import com.grace.zhihunews.contract.FriendsContact;
import com.grace.zhihunews.deliveryLayer.FriendProvider;
import com.grace.zhihunews.event.FriendApplyLoadedEvent;
import com.grace.zhihunews.event.FriendLoadedEvent;
import com.grace.zhihunews.event.LoadFailureEvent;

import de.greenrobot.event.EventBus;

public class FriendPresenterCompl implements FriendsContact.IFriendPresenter {

    private App app;
    private FriendsContact.IFriendView mIFriendView;
    private FriendProvider friendProvider;

    public FriendPresenterCompl(App app, FriendsContact.IFriendView iFriendView) {
        this.app = app;
        mIFriendView = iFriendView;
        friendProvider = new FriendProvider(app);
        EventBus.getDefault().register(this);
    }

    @Override
    public void loadFriends() {
        friendProvider.getFriends();
    }

    @Override
    public void loadApplies() {
        friendProvider.getApplies();
    }

    @Override
    public void refreshData() {
        friendProvider.refreshData();
    }

    public void onEvent(FriendLoadedEvent event) {
        mIFriendView.showFriends(event.loadFriends);
    }

    public void onEvent(FriendApplyLoadedEvent event){
        mIFriendView.showApplies(event.loadApplies);
    }

    public void onEvent(LoadFailureEvent event) {
        mIFriendView.showLoadFailureMsg(event.errorMsg);
    }
}
