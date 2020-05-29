package com.grace.zhihunews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.grace.zhihunews.App;
import com.grace.zhihunews.PresenterCompl.BooksDetailPresenterCompl;
import com.grace.zhihunews.R;
import com.grace.zhihunews.contract.BooksDetailContract;
import com.grace.zhihunews.network.entity.BooksDetail;
import com.grace.zhihunews.ui.base.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BooksDetailActivity extends BaseActivity implements BooksDetailContract.IBooksDetailView {

    public static final String KEY_STORY_ID = "story_id";

    @BindView(R.id.iv_header_img)
    ImageView ivHeaderImg;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.tv_img_source)
    TextView tvImgSource;
    @BindView(R.id.wb_story_content)
    WebView wbNewsContent;


    private BooksDetailContract.IBooksDetailPresenter mNewsDetailPresenter;
    private BooksDetail mBooksDetail;
    private int id;

    @Override
    protected void initVariables() {
        mNewsDetailPresenter = new BooksDetailPresenterCompl((App)getApplication(), this);
        id = getIntent().getIntExtra(KEY_STORY_ID, 8740001);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        mNewsDetailPresenter.loadBooksDetail(id);
    }

    //BooksDetailContract.INewsDetailView接口方法
    @Override
    public void showBooksDetail(BooksDetail booksDetail) {
        mBooksDetail = booksDetail;
//        String htmlData = "<link rel=\"stylesheet\" type=\"text/css\" href=\"zhihu.css\" />" + mBooksDetail.getBody();
//        wbNewsContent.loadDataWithBaseURL("file:///android_asset/", htmlData, "text/html", "utf-8", null);
//
//        tvHeaderTitle.setText(mBooksDetail.getTitle());
//        tvImgSource.setText(mBooksDetail.getImage_source());
//        Picasso.with(this).load(mBooksDetail.getImage()).into(ivHeaderImg);
    }

    @Override
    public void showLoadFailureMsg(String errorMsg) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.hold, android.R.anim.fade_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_news_detail, menu);
        return true;
    }

}
