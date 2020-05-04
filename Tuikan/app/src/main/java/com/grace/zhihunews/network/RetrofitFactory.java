package com.grace.zhihunews.network;

import com.grace.zhihunews.network.service.ZhifuService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/8/31.
 */
public class RetrofitFactory {

    public static final String ZHIHU_URL = "https://www.fastmock.site/mock/1634373f74402d465e2c7dd9e40e743c/readPartner/";

    private static ZhifuService mZhifuService;

    //ZhifuService：单例模式。
    public static synchronized ZhifuService getZhifuService() {
        if (mZhifuService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ZHIHU_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mZhifuService = retrofit.create(ZhifuService.class);
        }
        return mZhifuService;
    }


}
