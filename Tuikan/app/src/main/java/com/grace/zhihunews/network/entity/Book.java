package com.grace.zhihunews.network.entity;

import com.litesuits.orm.db.annotation.Collate;
import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.MapCollection;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.annotation.Unique;
import com.litesuits.orm.db.annotation.UniqueCombine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class Book extends BaseEntity implements Serializable {

    private int id;
    private String title;
    private String writer;
    private String image;
    private String txt_path;
    private String progress;
    private String time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTxt_path() {
        return txt_path;
    }

    public void setTxt_path(String txt_path) {
        this.txt_path = txt_path;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
