package com.grace.zhihunews.contract;

import com.grace.zhihunews.contract.base.BasePresenter;
import com.grace.zhihunews.contract.base.BaseView;
import com.grace.zhihunews.network.entity.RecommondBooks;

import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public interface DiscoverContact {

    interface IDiscoverView extends BaseView {

        void showReBooks(RecommondBooks recommondBooks);
//        void gotoNewsDetailActivity(int id);

    }

    interface IDiscoverPresenter extends BasePresenter {
        void loadRecBooks();
        void refreshData();

    }
}
