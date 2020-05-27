package com.grace.zhihunews.deliveryLayer;

import com.grace.zhihunews.App;
import com.grace.zhihunews.cache.ACache;
import com.grace.zhihunews.event.FriendApplyLoadedEvent;
import com.grace.zhihunews.event.FriendLoadedEvent;
import com.grace.zhihunews.event.LoadFailureEvent;
import com.grace.zhihunews.network.RetrofitFactory;
import com.grace.zhihunews.network.entity.Friend;
import com.grace.zhihunews.network.entity.LoadApplies;
import com.grace.zhihunews.network.entity.LoadFriends;
import com.grace.zhihunews.network.entity.FriendApply;
import com.grace.zhihunews.network.service.ZhifuService;

import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendProvider implements IFriendProvider {

    private App app;
    private ACache cache;
    private ZhifuService zhifuService;

    public FriendProvider(App app) {
        this.app = app;
        cache = app.getCacheInstance();
        zhifuService = RetrofitFactory.getZhifuService();
    }

    @Override
    public void getFriends() {
        Call<LoadFriends> call = zhifuService.getLoadFriends();
        call.enqueue(new Callback<LoadFriends>() {
            @Override
            public void onResponse(Call<LoadFriends> call, Response<LoadFriends> response) {
                List<Friend> friends = response.body().getFriends();
                LoadFriends loadFriends = new LoadFriends(friends);
                EventBus.getDefault().post(new FriendLoadedEvent(loadFriends));
            }
            @Override
            public void onFailure(Call<LoadFriends> call, Throwable t) {
                EventBus.getDefault().post(new LoadFailureEvent("请检查联网设置"));
            }
        });
    }

    @Override
    public void getApplies() {
        Call<LoadApplies> call = zhifuService.getLoadApplies();
        call.enqueue(new Callback<LoadApplies>() {
            @Override
            public void onResponse(Call<LoadApplies> call, Response<LoadApplies> response) {
                List<FriendApply> friendApplies = response.body().getApplies();
                LoadApplies loadApplies = new LoadApplies(friendApplies);
                EventBus.getDefault().post(new FriendApplyLoadedEvent(loadApplies));
            }
            @Override
            public void onFailure(Call<LoadApplies> call, Throwable t) {
                EventBus.getDefault().post(new LoadFailureEvent("请检查联网设置"));
            }
        });
    }

    @Override
    public void refreshData() {

    }
}
