package com.grace.zhihunews.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.grace.zhihunews.App;
import com.grace.zhihunews.PresenterCompl.WorldPresenterCompl;
import com.grace.zhihunews.R;
import com.grace.zhihunews.contract.WorldContact;
import com.grace.zhihunews.network.entity.Comment;
import com.grace.zhihunews.network.entity.SocialComments;
import com.grace.zhihunews.network.entity.TopStory;
import com.grace.zhihunews.ui.adapter.CommentsAdapater;
import com.grace.zhihunews.ui.adapter.TopStoriesAdapter;
import com.grace.zhihunews.ui.base.BaseFragment;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zanlabs.widget.infiniteviewpager.InfiniteViewPager;
import com.zanlabs.widget.infiniteviewpager.indicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/9/2.
 */
public class WorldFragment extends BaseFragment implements WorldContact.IWorldView {
    @BindView(R.id.srl_world)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_commend_list)
    RecyclerView commendList;
    @BindView(R.id.view_pager)
    InfiniteViewPager mViewPager;
    @BindView(R.id.indicator)
    CirclePageIndicator mIndicator;
    @BindView(R.id.rv_header)
    RecyclerViewHeader rvHeader;


    private Unbinder unbinder;
    private List<Comment> comments;
    private CommentsAdapater commentsAdapater;
    private WorldContact.IWorldPresenter iWorldPresenter;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_world;
    }

    @Override
    protected void initVariables() {
        comments = new ArrayList<>();
        commentsAdapater = new CommentsAdapater(getActivity(), comments);
        iWorldPresenter = new WorldPresenterCompl((App) getActivity().getApplicationContext(), this);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        toolbar.setTitle("");
        toolbar.inflateMenu(R.menu.menu_discover);

        commendList.setLayoutManager(linearLayoutManager);
        commendList.setAdapter(commentsAdapater);
        commendList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.divider_grey)
                .size(getResources().getDimensionPixelSize(R.dimen.divider_height))
                .margin(getResources().getDimensionPixelSize(R.dimen.spacing_normal_high),
                        getResources().getDimensionPixelSize(R.dimen.spacing_normal_high))
                .build());

        rvHeader.attachTo(commendList, true);
//        commendList.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
//            @Override
//            public void onLoadMore(int page, int totalItemCount) {
//                String farthestDate;
//                farthestDate = dateList.get(dateList.size() - 1);
//                Log.d("farthestDate", farthestDate);
//                String previousDate = DateUtil.getPreviousDay(farthestDate);
//                Log.d("previousDate", previousDate);
//                dateList.add(previousDate);
//                for (int i = 0; i < dateList.size(); i++) {
//                    Log.d("dataList", i + dateList.get(i));
//                }
//                iDiscoverPresenter.loadBeforeNews(previousDate);
//            }
//        });
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mSwipeRefreshLayout.setRefreshing(true);

            iWorldPresenter.refreshData();
            iWorldPresenter.loadComments();

            (new Handler()).postDelayed(() -> mSwipeRefreshLayout.setRefreshing(false), 1200);
        });

    }

    @Override
    protected void loadData() {
        iWorldPresenter.loadComments();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showComments(SocialComments socialComments) {
        List<TopStory> topStories = socialComments.getTopStories();
        TopStoriesAdapter topStoriesAdapter = new TopStoriesAdapter(getActivity(), topStories);
        mViewPager.setAdapter(topStoriesAdapter);
        mViewPager.setAutoScrollTime(3000);
        mViewPager.startAutoScroll();
        mIndicator.setViewPager(mViewPager);

        comments.clear();
        List<Comment> newComments = socialComments.getComments();
        comments.addAll(newComments);
        commentsAdapater.notifyDataSetChanged();
    }

    @Override
    public void showLoadFailureMsg(String errorMsg) {

    }

}
