package com.grace.zhihunews.contract;

import com.grace.zhihunews.contract.base.BasePresenter;
import com.grace.zhihunews.contract.base.BaseView;
import com.grace.zhihunews.network.entity.SocialComments;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public interface WorldContact {

    interface IWorldView extends BaseView {

        void showComments(SocialComments socialComments);
    }

    interface IWorldPresenter extends BasePresenter {
        void loadComments();
        void refreshData();
    }
}
