package com.grace.zhihunews.contract;

import com.grace.zhihunews.contract.base.BasePresenter;
import com.grace.zhihunews.contract.base.BaseView;
import com.grace.zhihunews.network.entity.BooksDetail;
import com.grace.zhihunews.network.entity.SimilarBook;

/**
 * Created by Administrator on 2016/9/1.
 */
public interface BooksDetailContract {

    interface IBooksDetailView extends BaseView {

        void showBooksDetail(BooksDetail booksDetail);

    }

    interface IBooksDetailPresenter extends BasePresenter {

        void loadBooksDetail(int id);
    }

}
