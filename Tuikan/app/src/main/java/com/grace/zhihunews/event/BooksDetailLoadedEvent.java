package com.grace.zhihunews.event;

import com.grace.zhihunews.network.entity.BooksDetail;

/**
 * Created by Administrator on 2016/8/31.
 */
public class BooksDetailLoadedEvent {

    public BooksDetail booksDetail;

    public BooksDetailLoadedEvent(BooksDetail booksDetail) { this.booksDetail = booksDetail; }
}
