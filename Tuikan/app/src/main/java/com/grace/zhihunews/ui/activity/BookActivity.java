package com.grace.zhihunews.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.grace.zhihunews.R;
import com.grace.zhihunews.network.entity.Book;
import com.grace.zhihunews.ui.view.BookPageContainer;

/**
 * author : 刘子川
 * e-mail : 775269512@qq.com
 * date   : 2020/5/60:30
 * version: 1.0
 */
public class BookActivity extends Activity {

    BookPageContainer book_page_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        book_page_container = (BookPageContainer) findViewById(R.id.book_page_container);

        Intent intent=getIntent();
        Book book = (Book) intent.getSerializableExtra("book");
        book_page_container.setFilePath(book);
    }
}
