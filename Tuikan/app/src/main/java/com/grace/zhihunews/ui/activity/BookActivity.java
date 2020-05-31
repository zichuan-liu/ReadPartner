package com.grace.zhihunews.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.grace.zhihunews.R;
import com.grace.zhihunews.ui.view.BookPageContainer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : 刘子川
 * e-mail : 775269512@qq.com
 * date   : 2020/5/60:30
 * version: 1.0
 */
public class BookActivity extends Activity {

    public static final String KEY_PATH = "book_path";

    @BindView(R.id.book_pager)
    BookPageContainer book_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        ButterKnife.bind(this);
        String path = getIntent().getStringExtra(KEY_PATH);
        book_pager.setPath(path);
    }
}
