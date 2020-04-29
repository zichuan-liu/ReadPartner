package com.grace.zhihunews.network.entity;

import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.annotation.Unique;
import com.litesuits.orm.db.enums.Relation;

import java.io.Serializable;
import java.util.List;

public class RecommondBooks extends BaseEntity implements Serializable {

    private List<RecBook> recBooks;
    private List<TopStory> topStories;

    public RecommondBooks(List<RecBook> recBooks, List<TopStory> topStories) {
        this.recBooks = recBooks;
        this.topStories = topStories;
    }

    public void setRecBooks(List<RecBook> recBooks) {
        this.recBooks = recBooks;
    }

    public List<RecBook> getRecBooks() {
        return recBooks;
    }


    public List<TopStory> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<TopStory> topStories) {
        this.topStories = topStories;
    }
}
