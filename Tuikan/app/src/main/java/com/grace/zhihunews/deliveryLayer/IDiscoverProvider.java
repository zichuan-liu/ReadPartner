package com.grace.zhihunews.deliveryLayer;

/**
 * Created by Administrator on 2016/9/2.
 */
public interface IDiscoverProvider {
    void getLatestNews();

    void getBeforeNews(String date);

    void refreshData();


}
