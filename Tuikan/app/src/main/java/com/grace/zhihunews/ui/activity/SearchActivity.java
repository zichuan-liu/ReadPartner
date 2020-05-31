package com.grace.zhihunews.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.grace.zhihunews.App;
import com.grace.zhihunews.PresenterCompl.BookListPresenterCompl;
import com.grace.zhihunews.R;
import com.grace.zhihunews.contract.BookListContact;
import com.grace.zhihunews.contract.OnSearchListener;
import com.grace.zhihunews.network.entity.Book;
import com.grace.zhihunews.network.entity.LoadBooks;
import com.grace.zhihunews.ui.adapter.BooksAdapter;
import com.grace.zhihunews.ui.view.KylinSearchView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : 刘子川
 * e-mail : 775269512@qq.com
 * date   : 2020/5/2322:06
 * version: 1.0
 */
public class SearchActivity extends AppCompatActivity implements OnSearchListener, BookListContact.IBookListView {
    @BindView(R.id.rv_read)
    RecyclerView rvReadList;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private BooksAdapter booksAdapter;

    private KylinSearchView searchViewDemo;
    private Button sv_back;
    private Unbinder unbinder;
    private BookListContact.IBookListPresenter mBookListPresenter;
    private List<Book> mBooks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        unbinder = ButterKnife.bind(this);

        mBooks = new ArrayList<>();
        booksAdapter = new BooksAdapter(this, mBooks);
        mBookListPresenter = new BookListPresenterCompl((App) this.getApplicationContext(), this, SearchActivity.this);
        searchViewDemo = (KylinSearchView) findViewById(R.id.sv_default);
        searchViewDemo.setOnSearchListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvReadList.setLayoutManager(linearLayoutManager);
        rvReadList.setAdapter(booksAdapter);
        rvReadList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .colorResId(R.color.divider_grey)
                .size(getResources().getDimensionPixelSize(R.dimen.divider_height))
                .margin(getResources().getDimensionPixelSize(R.dimen.spacing_normal_high),
                        getResources().getDimensionPixelSize(R.dimen.spacing_normal_high))
                .build());

        sv_back = (Button) findViewById(R.id.sv_back);
        sv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mSwipeRefreshLayout.setRefreshing(true);

            mBookListPresenter.refreshData();
            mBookListPresenter.loadBook();

            (new Handler()).postDelayed(() -> mSwipeRefreshLayout.setRefreshing(false), 1200);
        });
    }


    @Override
    public void search(String content) {
        Toast.makeText(this,"搜索内容： "+content,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        unbinder.unbind();
        super.finish();
    }

    @Override
    public void showBook(LoadBooks loadBooks) {
        mBooks.clear();
        List<Book> books = loadBooks.getBooks();
        mBooks.addAll(books);
        booksAdapter.notifyDataSetChanged();
    }

    @Override
    public void gotoBooksPagerActivity(int id) {

    }

    @Override
    public void showLoadFailureMsg(String errorMsg) {

    }
}
