package com.grace.zhihunews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.grace.zhihunews.App;
import com.grace.zhihunews.PresenterCompl.FriendPresenterCompl;
import com.grace.zhihunews.R;
import com.grace.zhihunews.contract.FriendsContact;
import com.grace.zhihunews.contract.WorldContact;
import com.grace.zhihunews.deliveryLayer.FriendProvider;
import com.grace.zhihunews.network.entity.Friend;
import com.grace.zhihunews.network.entity.LoadApplies;
import com.grace.zhihunews.network.entity.LoadFriends;
import com.grace.zhihunews.ui.adapter.CommentsAdapater;
import com.grace.zhihunews.ui.adapter.FriendAdapater;
import com.grace.zhihunews.ui.base.BaseActivity;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamActivity extends BaseActivity implements FriendsContact.IFriendView{
    @BindView(R.id.team_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_friends_list)
    RecyclerView friendsList;
    @BindView(R.id.rv_header)
    RecyclerViewHeader recyclerViewHeader;
    @BindView(R.id.toolbar_team)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.team_new_friend)
    Button newFriendButton;

    private List<Friend> friends;
    private FriendAdapater friendAdapater;
    private FriendsContact.IFriendPresenter iFriendPresenter;



    @Override
    protected void initVariables() {
        friends = new ArrayList<>();
        friendAdapater = new FriendAdapater(TeamActivity.this,friends);
        iFriendPresenter = new FriendPresenterCompl((App)getApplicationContext(), this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_team);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        toolbar_title.setText("组队");
        toolbar.inflateMenu(R.menu.menu_friend);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TeamActivity.this);

        friendsList.setLayoutManager(linearLayoutManager);
        friendsList.setAdapter(friendAdapater);
        friendsList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(TeamActivity.this)
                .colorResId(R.color.divider_grey)
                .size(getResources().getDimensionPixelSize(R.dimen.divider_height))
                .margin(getResources().getDimensionPixelSize(R.dimen.spacing_normal_high),
                        getResources().getDimensionPixelSize(R.dimen.spacing_normal_high))
                .build());
        recyclerViewHeader.attachTo(friendsList,true);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            iFriendPresenter.loadFriends();
            iFriendPresenter.refreshData();
            (new Handler()).postDelayed(() -> mSwipeRefreshLayout.setRefreshing(false), 1200);
        });

        newFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeamActivity.this, FriendApplyActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void loadData() {
        iFriendPresenter.loadFriends();
    }

    @Override
    public void showFriends(LoadFriends loadFriends) {
        friends.clear();
        List<Friend> newFriends = loadFriends.getFriends();
        friends.addAll(newFriends);
        friendAdapater.notifyDataSetChanged();
    }

    @Override
    public void showApplies(LoadApplies loadApplies) {

    }

    @Override
    public void showLoadFailureMsg(String errorMsg) {

    }
}
