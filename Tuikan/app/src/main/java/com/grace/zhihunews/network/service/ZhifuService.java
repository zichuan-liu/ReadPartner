package com.grace.zhihunews.network.service;

import com.grace.zhihunews.network.entity.LoadBooks;
import com.grace.zhihunews.network.entity.NewsDetail;
import com.grace.zhihunews.network.entity.RecommondBooks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/8/31.
 */
public interface ZhifuService {

    //https://www.fastmock.site/mock/1634373f74402d465e2c7dd9e40e743c/readPartner
    @GET("api/getPreBooks")
    Call<LoadBooks> getPreBooks();

    @GET("api/getRecBooks")
    Call<RecommondBooks> getRecBooks();

    //http://news-at.zhihu.com/api/4/news/8725424
    @GET("api/4/news/{id}")
    Call<NewsDetail> getNewsDetail(@Path("id") int id);
}
