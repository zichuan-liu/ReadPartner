package com.grace.zhihunews.contract;

import com.grace.zhihunews.contract.base.BaseView;
import com.grace.zhihunews.network.entity.BeforeNews;
import com.grace.zhihunews.network.entity.LatestNews;
import com.grace.zhihunews.network.entity.TopStory;

import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public interface NewsListContact {

    interface INewsListView extends BaseView {
        void showLatestNews(LatestNews latestNews);

        void showBeforeNews(BeforeNews beforeNews);

        void gotoNewsDetailActivity(int id);

    }

    interface INewsListPresenter {
        void loadLatestNews();

        void loadBeforeNews(String date);

        void refreshData();


    }
}
