package com.grace.zhihunews.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.grace.zhihunews.App;
import com.grace.zhihunews.PresenterCompl.DiscoverPresenterCompl;
import com.grace.zhihunews.R;
import com.grace.zhihunews.contract.DiscoverContact;
import com.grace.zhihunews.network.entity.RecBook;
import com.grace.zhihunews.network.entity.RecommondBooks;
import com.grace.zhihunews.network.entity.TopStory;
import com.grace.zhihunews.ui.activity.BookInfoActivity;
import com.grace.zhihunews.ui.activity.BooksDetailActivity;
import com.grace.zhihunews.ui.adapter.RecBooksAdapter;
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
public class DiscoverFragment extends BaseFragment implements DiscoverContact.IDiscoverView {
    @BindView(R.id.srl_discover)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_recommend_list)
    RecyclerView recommendList;
    @BindView(R.id.view_pager)
    InfiniteViewPager mViewPager;
    @BindView(R.id.indicator)
    CirclePageIndicator mIndicator;
    @BindView(R.id.rv_header)
    RecyclerViewHeader rvHeader;
    @BindView(R.id.discover_free)
    Button discover_free;

    @BindView(R.id.discover_rank)
    Button discover_rank;
    @BindView(R.id.discover_vip)
    Button discover_vip;
    @BindView(R.id.discover_hear)
    Button discover_hear;
    @BindView(R.id.discover_menu)
    GridLayout discover_menu;

    private Unbinder unbinder;
    private List<RecBook> recBooks;
    private RecBooksAdapter recBooksAdapter;
    private DiscoverContact.IDiscoverPresenter iDiscoverPresenter;
    private Context mContext;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initVariables() {
        recBooks = new ArrayList<>();
        mContext = getActivity();
        recBooksAdapter = new RecBooksAdapter(getActivity(), recBooks);
        iDiscoverPresenter = new DiscoverPresenterCompl((App) getActivity().getApplicationContext(), this);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        toolbar.setTitle("");
        toolbar.inflateMenu(R.menu.menu_discover);

        recommendList.setLayoutManager(linearLayoutManager);
        recommendList.setAdapter(recBooksAdapter);
        recommendList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.divider_grey)
                .size(getResources().getDimensionPixelSize(R.dimen.divider_height))
                .margin(getResources().getDimensionPixelSize(R.dimen.spacing_normal_high),
                        getResources().getDimensionPixelSize(R.dimen.spacing_normal_high))
                .build());

        rvHeader.attachTo(recommendList, true);
//        recommendList.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
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

            iDiscoverPresenter.refreshData();
            iDiscoverPresenter.loadRecBooks();

            (new Handler()).postDelayed(() -> mSwipeRefreshLayout.setRefreshing(false), 1200);
        });

        discoverMenuClickListener();

    }

    private void discoverMenuClickListener() {
        discover_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "暂未开启编辑功能", Toast.LENGTH_SHORT).show();
            }
        });
        discover_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "这个也没开启噢", Toast.LENGTH_SHORT).show();
            }
        });
        discover_vip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "没想到吧这个也没做", Toast.LENGTH_SHORT).show();
            }
        });
        discover_hear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "无可奉告", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void loadData() {
        iDiscoverPresenter.loadRecBooks();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    @Override
//    public void gotoNewsDetailActivity(int id) {
//        Intent intent = new Intent(getActivity(), BooksDetailActivity.class);
//        intent.putExtra(BooksDetailActivity.KEY_STORY_ID, id);
//        startActivity(intent);
//        //overridePendingTransition(R.anim.hold, android.R.anim.fade_in);
//    }


    @Override
    public void showReBooks(RecommondBooks recommondBooks) {
        List<TopStory> topStories = recommondBooks.getTopStories();
        TopStoriesAdapter topStoriesAdapter = new TopStoriesAdapter(getActivity(), topStories);
        mViewPager.setAdapter(topStoriesAdapter);
        mViewPager.setAutoScrollTime(3000);
        mViewPager.startAutoScroll();
        mIndicator.setViewPager(mViewPager);

        recBooks.clear();
        List<RecBook> newRecbooks = recommondBooks.getRecBooks();
        recBooks.addAll(newRecbooks);
        recBooksAdapter.notifyDataSetChanged();
    }

    @Override
    public void gotoBooksDetailActivity(int id) {
        Intent intent = new Intent(getActivity(), BookInfoActivity.class);
        intent.putExtra(BooksDetailActivity.KEY_STORY_ID, id);
        startActivity(intent);
    }

    @Override
    public void showLoadFailureMsg(String errorMsg) {

    }
}
