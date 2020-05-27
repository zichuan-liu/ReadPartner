package com.grace.zhihunews.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.grace.zhihunews.App;
import com.grace.zhihunews.PresenterCompl.FriendPresenterCompl;
import com.grace.zhihunews.R;
import com.grace.zhihunews.contract.FriendsContact;
import com.grace.zhihunews.network.entity.LoadApplies;
import com.grace.zhihunews.network.entity.LoadFriends;
import com.grace.zhihunews.network.entity.FriendApply;
import com.grace.zhihunews.ui.adapter.FriendApplyAdapter;
import com.grace.zhihunews.ui.base.BaseActivity;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendApplyActivity extends BaseActivity implements FriendsContact.IFriendView{
    @BindView(R.id.apply_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_applies_list)
    RecyclerView appliesList;
    @BindView(R.id.rv_header)
    RecyclerViewHeader recyclerViewHeader;
    @BindView(R.id.toolbar_apply)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    private List<FriendApply> friendApplies;
    private FriendApplyAdapter friendApplyAdapter;
    private FriendsContact.IFriendPresenter iFriendPresenter;



    @Override
    protected void initVariables() {
        friendApplies = new ArrayList<>();
        friendApplyAdapter = new FriendApplyAdapter(FriendApplyActivity.this,friendApplies);
        iFriendPresenter = new FriendPresenterCompl((App)getApplicationContext(), this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_apply);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        toolbar_title.setText("新的好友");
        toolbar.inflateMenu(R.menu.menu_friend);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FriendApplyActivity.this);

        appliesList.setLayoutManager(linearLayoutManager);
        appliesList.setAdapter(friendApplyAdapter);
        appliesList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(FriendApplyActivity.this)
                .colorResId(R.color.divider_grey)
                .size(getResources().getDimensionPixelSize(R.dimen.divider_height))
                .margin(getResources().getDimensionPixelSize(R.dimen.spacing_normal_high),
                        getResources().getDimensionPixelSize(R.dimen.spacing_normal_high))
                .build());
        recyclerViewHeader.attachTo(appliesList,true);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            iFriendPresenter.loadApplies();
            iFriendPresenter.refreshData();
            (new Handler()).postDelayed(() -> mSwipeRefreshLayout.setRefreshing(false), 1200);
        });
    }

    @Override
    protected void loadData() {
        iFriendPresenter.loadApplies();
    }

    @Override
    public void showFriends(LoadFriends loadFriends) {

    }

    @Override
    public void showApplies(LoadApplies loadApplies) {
        friendApplies.clear();
        List<FriendApply> newApplies = loadApplies.getApplies();
        friendApplies.addAll(newApplies);
        friendApplyAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadFailureMsg(String errorMsg) {

    }
}
