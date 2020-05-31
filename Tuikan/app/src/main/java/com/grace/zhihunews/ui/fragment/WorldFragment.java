package com.grace.zhihunews.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.grace.zhihunews.App;
import com.grace.zhihunews.PresenterCompl.WorldPresenterCompl;
import com.grace.zhihunews.R;
import com.grace.zhihunews.contract.WorldContact;
import com.grace.zhihunews.network.entity.Comment;
import com.grace.zhihunews.network.entity.SocialComments;
import com.grace.zhihunews.network.entity.TopStory;
import com.grace.zhihunews.ui.activity.CalendarActivity;
import com.grace.zhihunews.ui.activity.SearchActivity;
import com.grace.zhihunews.ui.activity.TeamActivity;
import com.grace.zhihunews.ui.adapter.CommentsAdapater;
import com.grace.zhihunews.ui.adapter.TopStoriesAdapter;
import com.grace.zhihunews.ui.base.BaseFragment;
import com.grace.zhihunews.util.CalendarDao;
import com.grace.zhihunews.util.RecordType;
import com.grace.zhihunews.util.SDUtil;
import com.grace.zhihunews.util.WRUtil;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zanlabs.widget.infiniteviewpager.InfiniteViewPager;
import com.zanlabs.widget.infiniteviewpager.indicator.CirclePageIndicator;

import java.io.IOException;
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
    @BindView(R.id.world_date)
    Button world_date;
    @BindView(R.id.world_team)
    Button worldTeam;


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
        SDUtil sdUtil = new SDUtil();


        sdUtil = new SDUtil(getActivity());
        WRUtil wrUtil = new WRUtil();
        wrUtil.writeFile(getActivity(), "20", RecordType.DAY);
        try {
            sdUtil.saveFileToSD(RecordType.CALENDAR.getPath(), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sdUtil.saveFileToSD(RecordType.CALENDAR.getPath(), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        wrUtil.writeFile(getActivity(), "2020-05-20", RecordType.CALENDAR);
        wrUtil.writeFile(getActivity(), "2020-05-21", RecordType.CALENDAR);
        wrUtil.writeFile(getActivity(), "2020-05-22", RecordType.CALENDAR);
        wrUtil.writeFile(getActivity(), "2020-05-23", RecordType.CALENDAR);
        wrUtil.writeFile(getActivity(), "2020-05-24", RecordType.CALENDAR);
        String dayStr = null;
        try {
            dayStr = sdUtil.readFromSD(RecordType.DAY.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String dayCountStr = dayStr + "/180";

        Spannable dayCountStrSet = new SpannableString(dayCountStr);
        dayCountStrSet.setSpan(new ForegroundColorSpan(Color.WHITE), 0, dayCountStrSet.length() - 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


//        dayCountStrSet.setSpan(new AbsoluteSizeSpan(40, true), 0, dayStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

//        dayCountStrSet.setSpan(new AbsoluteSizeSpan(18, true), dayStr.length(), dayCountStrSet.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        /**
         * 这个try先执行一次，然后注释掉
         */

        try {
            sdUtil.saveFileToSD(RecordType.CALENDAR.getPath(), "");
            sdUtil.saveFileToSD(RecordType.WORD.getPath(), "");
        } catch (Exception e) {
            System.out.println("ERROR1");
            e.printStackTrace();
        }
        sdUtil.initFile();

        try {
            String res = sdUtil.readFromSD("calendar.txt");
            System.out.println(res);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("**********");
        CalendarDao calendarDao = new CalendarDao();
        //        String res = calendarDao.listDay("2019", "05", getContext());
        //       System.out.println(res);
        sdUtil = new SDUtil(getActivity());
        sdUtil.verifyStoragePermissions(getActivity());
        try {
            sdUtil.saveFileToSD("day.txt", "2");
            Thread.sleep(2000);
            String content = sdUtil.readFromSD("day.txt");
            System.out.println("ccccccontent" + content);
        } catch (Exception ex) {
            ex.printStackTrace();
            //
        }
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

        world_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivity(intent);
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        startActivity(new Intent(getActivity(), SearchActivity.class));
                        break;
                }
                return true;
            }});
        worldTeam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TeamActivity.class);
                startActivity(intent);
            }
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
