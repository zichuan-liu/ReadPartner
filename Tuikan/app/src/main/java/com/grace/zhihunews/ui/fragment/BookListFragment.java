package com.grace.zhihunews.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.grace.zhihunews.App;
import com.grace.zhihunews.PresenterCompl.BookListPresenterCompl;
import com.grace.zhihunews.R;
import com.grace.zhihunews.contract.BookListContact;
import com.grace.zhihunews.network.entity.Book;
import com.grace.zhihunews.network.entity.LoadBooks;
import com.grace.zhihunews.ui.activity.ExchangeActivity;
import com.grace.zhihunews.ui.activity.SearchActivity;
import com.grace.zhihunews.ui.adapter.BooksAdapter;
import com.grace.zhihunews.ui.base.BaseFragment;
import com.grace.zhihunews.ui.view.AddDialog;
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
    @BindView(R.id.toolbar_edit)
    Button toolbar_edit;
    @BindView(R.id.rv_read)
    RecyclerView rvReadList;
    @BindView(R.id.rv_header)
    RecyclerViewHeader rvHeader;
    @BindView(R.id.rv_time)
    TextView rvText;
    @BindView(R.id.qiandao)
    Button qiandao;
    @BindView(R.id.lvcheng)
    ImageButton lvcheng;

    private BookListContact.IBookListPresenter mBookListPresenter;
    private Unbinder unbinder;
    private List<Book> mBooks;
    private BooksAdapter booksAdapter;
    private Context mContext;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_book_list;
    }

    @Override
    protected void initVariables() {
        mBooks = new ArrayList<>();
        booksAdapter = new BooksAdapter(getActivity(), mBooks);
        mBookListPresenter = new BookListPresenterCompl((App) getActivity().getApplicationContext(), this);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        toolbar.setTitle("");
        toolbar_edit.setText(R.string.MainActivity_title_edit);
        toolbar.inflateMenu(R.menu.menu_main);

        rvReadList.setLayoutManager(linearLayoutManager);
        rvReadList.setAdapter(booksAdapter);
        rvReadList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.divider_grey)
                .size(getResources().getDimensionPixelSize(R.dimen.divider_height))
                .margin(getResources().getDimensionPixelSize(R.dimen.spacing_normal_high),
                        getResources().getDimensionPixelSize(R.dimen.spacing_normal_high))
                .build());

        rvHeader.attachTo(rvReadList, true);

//        rvReadList.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
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
//                mBookListPresenter.loadBeforeBook(previousDate);
//            }
//        });
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mSwipeRefreshLayout.setRefreshing(true);

            mBookListPresenter.refreshData();
            mBookListPresenter.loadBook();

            (new Handler()).postDelayed(() -> mSwipeRefreshLayout.setRefreshing(false), 1200);
        });

        /**
         * 测试阅读器
         */
        qiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qiandao.setText("已签到");
            }

        });
        lvcheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ExchangeActivity.class));
            }

        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        startActivity(new Intent(mContext, SearchActivity.class));
                        break;
                    case R.id.action_add:
                        AddDialog addDialog = new AddDialog(mContext);
                        addDialog.setOnClickBottomListener(
                                new OnClickBottomListener() {

                                    /**
                                     * 点击确定按钮事件
                                     */
                                    @Override
                                    public void onPositiveClick() {
                                        Toast.makeText(mContext, "请正确填写书籍", Toast.LENGTH_SHORT).show();
                                    }

                                    /**
                                     * 点击取消按钮事件
                                     */
                                    @Override
                                    public void onNegtiveClick() {
                                        addDialog.dismiss();
                                    }
                                }

                        ).show();
                        break;
                    case R.id.toolbar_edit:
                        Toast.makeText(mContext, "暂未开启编辑功能", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }


    @Override
    protected void loadData() {
        mBookListPresenter.loadBook();
    }

    //BookListContact.INewsListView接口方法实现
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showBook(LoadBooks loadBooks) {
        mBooks.clear();
        List<Book> books = loadBooks.getBooks();
        mBooks.addAll(books);
        booksAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadFailureMsg(String errorMsg) {

    }


}
