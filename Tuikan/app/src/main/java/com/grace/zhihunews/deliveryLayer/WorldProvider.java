package com.grace.zhihunews.deliveryLayer;

import com.grace.zhihunews.App;
import com.grace.zhihunews.cache.ACache;
import com.grace.zhihunews.event.CommentsLoadedEvent;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.network.RetrofitFactory;
import com.grace.zhihunews.network.entity.Comment;
import com.grace.zhihunews.network.entity.SocialComments;
import com.grace.zhihunews.network.entity.TopStory;
import com.grace.zhihunews.network.service.ZhifuService;

import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/9.
 */
public class WorldProvider implements IWorldProvider {

    public static final String Comments_FILE_NAME = "commets_FILE_NAME";

    private App app;
    private ACache cache;
    private ZhifuService zhifuService;

    public WorldProvider(App app) {
        this.app = app;
        cache = app.getCacheInstance();
        zhifuService = RetrofitFactory.getZhifuService();
    }

    @Override
    public void getComments() {
        Call<SocialComments> call = zhifuService.getSocialComments();
        call.enqueue(new Callback<SocialComments>() {
            @Override
            public void onResponse(Call<SocialComments> call, Response<SocialComments> response) {
                List<TopStory> topStories = response.body().getTopStories();
                List<Comment> comments = response.body().getComments();
                SocialComments socialComments = new SocialComments(comments, topStories);
                EventBus.getDefault().post(new CommentsLoadedEvent(socialComments));
            }
            @Override
            public void onFailure(Call<SocialComments> call, Throwable t) {
                EventBus.getDefault().post(new LoadFailureEvent("请检查联网设置"));
            }
        });
    }

    @Override
    public void refreshData() {

    }
}
