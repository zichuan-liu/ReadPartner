package com.grace.zhihunews.event;
import com.grace.zhihunews.network.entity.LoadBooks;
import com.grace.zhihunews.network.entity.RecommondBooks;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
public class RecBooksLoadEvent {

    public RecommondBooks recommondBooks;

    public RecBooksLoadEvent(RecommondBooks recommondBooks) { this.recommondBooks = recommondBooks; }
}
