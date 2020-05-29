package com.grace.zhihunews.network.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */

/**
 * 如：http://news-at.zhihu.com/api/4/news/8725424（输入Story的id）返回的字符串对应的实体类
 */
public class BooksDetail implements Serializable {

    private int id;
    private String book_img;
    private String book_title;
    private String book_score;
    private String book_writer;
    private String book_progress;
    private String book_time;
    private String book_info;
    private String book_paragraph;

    private List<SimilarBook> similarBooks;

    public String getBook_time() {
        return book_time;
    }

    public void setBook_time(String book_time) {
        this.book_time = book_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook_img() {
        return book_img;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_score() {
        return book_score;
    }

    public void setBook_score(String book_score) {
        this.book_score = book_score;
    }

    public String getBook_writer() {
        return book_writer;
    }

    public void setBook_writer(String book_writer) {
        this.book_writer = book_writer;
    }

    public String getBook_progress() {
        return book_progress;
    }

    public void setBook_progress(String book_progress) {
        this.book_progress = book_progress;
    }

    public String getBook_info() {
        return book_info;
    }

    public void setBook_info(String book_info) {
        this.book_info = book_info;
    }

    public String getBook_paragraph() {
        return book_paragraph;
    }

    public void setBook_paragraph(String book_paragraph) {
        this.book_paragraph = book_paragraph;
    }

    public List<SimilarBook> getSimilarBooks() {
        return similarBooks;
    }

    public void setSimilarBooks(List<SimilarBook> similarBooks) {
        this.similarBooks = similarBooks;
    }
}
