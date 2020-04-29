package com.grace.zhihunews.contract;

import com.grace.zhihunews.contract.base.BaseView;
import com.grace.zhihunews.network.entity.LoadBooks;

/**
 * Created by Administrator on 2016/9/1.
 */
public interface BookListContact {

    interface IBookListView extends BaseView {
        void showBook(LoadBooks loadBooks);

        //TODO 链接到阅读器
//        void gotoNewsBookActivity(int id);

    }

    interface IBookListPresenter {
        void loadBook();

        void refreshData();


    }
}
