package com.grace.zhihunews.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.grace.zhihunews.network.entity.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/16.
 */
public class DBHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "book.db";
    public static final int VERSION = 1;
    private static DBHelper dbHelper;
    private SQLiteDatabase db;

    private static final String CREATE_BOOK = "create table Book (" +
            "_id integer primary key autoincrement, " +
            "id integer unique, " +
            "title text," +
            "writer text," +
            "image text," +
            "txt_path text," +
            "progress text," +
            "time text)";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public synchronized static DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        onCreate(db);
    }

    public long saveBook(Book book) {
        long book_id = 0;
        SQLiteDatabase db = getWritableDatabase();
        if (book != null) {
            ContentValues values = new ContentValues();
            values.put("id", book.getId());
            values.put("title", book.getTitle());
            values.put("writer", book.getWriter());
            values.put("image", book.getImage());
            values.put("txt_path", book.getTxt_path());
            values.put("progress", book.getProgress());
            values.put("time", book.getTime());
            book_id = db.insert("Book", null, values);
        }
        return book_id;
    }

    public List<Book> loadBooks() {
        SQLiteDatabase db = getWritableDatabase();
        List<Book> books = new ArrayList<>();
        Cursor cursor = db.query("Book", new String[] { "title","writer","image","txt_path","progress","time" }, null, null, null, null, "time");
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                book.setWriter(cursor.getString(cursor.getColumnIndex("writer")));
                book.setImage(cursor.getString(cursor.getColumnIndex("image")));
                book.setTxt_path(cursor.getString(cursor.getColumnIndex("txt_path")));
                book.setProgress(cursor.getString(cursor.getColumnIndex("progress")));
                book.setTime(cursor.getString(cursor.getColumnIndex("time")));
                books.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return books;
    }

//    public long deleteStoriesByDate(String date) {
//        SQLiteDatabase db = getWritableDatabase();
//        return db.delete("Story", "date = ?", new String[] {date});
//    }

    public long deleteAllBook() {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("Book", null, null);
    }
}
