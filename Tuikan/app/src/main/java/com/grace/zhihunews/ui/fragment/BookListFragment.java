package com.grace.zhihunews.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.grace.zhihunews.App;
import com.grace.zhihunews.PresenterCompl.BookListPresenterCompl;
import com.grace.zhihunews.R;
import com.grace.zhihunews.contract.BookListContact;
import com.grace.zhihunews.network.entity.BeforeNews;
import com.grace.zhihunews.network.entity.LatestNews;
import com.grace.zhihunews.network.entity.Story;
import com.grace.zhihunews.ui.activity.NewsDetailActivity;
import com.grace.zhihunews.ui.adapter.StoriesAdapter;
import com.grace.zhihunews.ui.base.BaseFragment;
import com.grace.zhihunews.ui.listener.EndlessRecyclerViewScrollListener;
import com.grace.zhihunews.util.DateUtil;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2016/9/1.
 */
public class BookListFragment extends BaseFragment implements BookListContact.IBookListView {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.toolbar_book)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    Button toolbarTitle;
    @BindView(R.id.rv_read)
    RecyclerView rvReadList;
    @BindView(R.id.rv_header)
    RecyclerViewHeader rvHeader;

    private BookListContact.IBookListPresenter mBookListPresenter;
    private List<String> dateList;
    private Unbinder unbinder;
    private List<Story> mStories;
    private StoriesAdapter storiesAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_news_list;
    }

    @Override
    protected void initVariables() {
        mStories = new ArrayList<>();
        dateList = new ArrayList<>();
        storiesAdapter = new StoriesAdapter(getActivity(), mStories);
        mBookListPresenter = new BookListPresenterCompl((App) getActivity().getApplicationContext(), this);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        toolbar.setTitle("");
        toolbarTitle.setText(R.string.MainActivity_title_edit);
        toolbar.inflateMenu(R.menu.menu_main);

        rvReadList.setLayoutManager(linearLayoutManager);
        rvReadList.setAdapter(storiesAdapter);
        rvReadList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.divider_grey)
                .size(getResources().getDimensionPixelSize(R.dimen.divider_height))
                .margin(getResources().getDimensionPixelSize(R.dimen.spacing_normal_high),
                        getResources().getDimensionPixelSize(R.dimen.spacing_normal_high))
                .build());

        rvHeader.attachTo(rvReadList, true);
        rvReadList.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemCount) {
                String farthestDate;
                farthestDate = dateList.get(dateList.size() - 1);
                Log.d("farthestDate", farthestDate);
                String previousDate = DateUtil.getPreviousDay(farthestDate);
                Log.d("previousDate", previousDate);
                dateList.add(previousDate);
                for (int i = 0; i < dateList.size(); i++) {
                    Log.d("dataList", i + dateList.get(i));
                }
                mBookListPresenter.loadBeforeBook(previousDate);
            }
        });
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mSwipeRefreshLayout.setRefreshing(true);

            mBookListPresenter.refreshData();
            String latestDate = DateUtil.getLatestDate();
            if (dateList != null) {
                dateList.clear();
                dateList.add(latestDate);
            }
            mBookListPresenter.loadLatestBook();

            (new Handler()).postDelayed(() -> mSwipeRefreshLayout.setRefreshing(false), 1200);
        });
    }

    @Override
    protected void loadData() {
        String latestDate = DateUtil.getLatestDate();
        dateList.add(latestDate);
        mBookListPresenter.loadLatestBook();
    }

    //BookListContact.INewsListView接口方法实现
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showLatestBook(LatestNews latestNews) {
        mStories.clear();
        List<Story> stories = latestNews.getStories();
        mStories.addAll(stories);
        storiesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBeforeBook(BeforeNews beforeNews) {
        List<Story> stories = beforeNews.getStories();
        mStories.addAll(stories);
        int curSize = storiesAdapter.getItemCount();
        storiesAdapter.notifyItemRangeChanged(curSize, mStories.size() - 1);
    }


    @Override
    public void gotoNewsBookActivity(int id) {
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.KEY_STORY_ID, id);
        startActivity(intent);
        //overridePendingTransition(R.anim.hold, android.R.anim.fade_in);
    }

    @Override
    public void showLoadFailureMsg(String errorMsg) {

    }
}
