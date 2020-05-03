package com.grace.zhihunews.network.entity;

import com.litesuits.orm.db.annotation.Unique;

import java.io.Serializable;

/**
 * 滚动窗口广告类
 */

public class TopStory extends BaseEntity implements Serializable {

    @Unique
    private int id;
    private String image;

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}
