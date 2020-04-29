package com.grace.zhihunews.event;
import com.grace.zhihunews.network.entity.LoadBooks;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
public class BooksLoadedEvent {

    public LoadBooks loadBooks;

    public BooksLoadedEvent(LoadBooks loadBooks) { this.loadBooks = loadBooks; }
}
