package com.grace.zhihunews.contract;

import com.grace.zhihunews.contract.base.BasePresenter;
import com.grace.zhihunews.contract.base.BaseView;
import com.grace.zhihunews.network.entity.BeforeNews;
import com.grace.zhihunews.network.entity.Girl;
import com.grace.zhihunews.network.entity.LatestNews;

import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public interface GirlsContact {

    interface IGirlsView extends BaseView {

        void showLatestNews(LatestNews latestNews);
        void showBeforeNews(BeforeNews beforeNews);
        void gotoNewsDetailActivity(int id);

    }

    interface IGirlsPresenter extends BasePresenter {
        void loadLatestNews();

        void loadBeforeNews(String date);

        void refreshData();

    }
}
