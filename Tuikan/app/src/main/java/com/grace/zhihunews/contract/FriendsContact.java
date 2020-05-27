package com.grace.zhihunews.contract;

import com.grace.zhihunews.contract.base.BasePresenter;
import com.grace.zhihunews.contract.base.BaseView;
import com.grace.zhihunews.network.entity.LoadApplies;
import com.grace.zhihunews.network.entity.LoadFriends;

public interface FriendsContact {
    interface IFriendView extends BaseView {
        void showFriends(LoadFriends loadFriends);
        void showApplies(LoadApplies loadApplies);
    }

    interface IFriendPresenter extends BasePresenter {
        void loadFriends();
        void loadApplies();
        void refreshData();
    }
}
