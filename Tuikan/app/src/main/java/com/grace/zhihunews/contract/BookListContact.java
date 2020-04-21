package com.grace.zhihunews.contract;

import com.grace.zhihunews.contract.base.BaseView;
import com.grace.zhihunews.network.entity.BeforeNews;
import com.grace.zhihunews.network.entity.LatestNews;

/**
 * Created by Administrator on 2016/9/1.
 */
public interface BookListContact {

    interface IBookListView extends BaseView {
        void showLatestBook(LatestNews latestNews);

        void showBeforeBook(BeforeNews beforeNews);

        void gotoNewsBookActivity(int id);

    }

    interface IBookListPresenter {
        void loadLatestBook();

        void loadBeforeBook(String date);

        void refreshData();


    }
}
