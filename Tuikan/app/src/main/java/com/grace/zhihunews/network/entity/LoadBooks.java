package com.grace.zhihunews.network.entity;

import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.annotation.Unique;
import com.litesuits.orm.db.enums.Relation;

import java.io.Serializable;
import java.util.List;

public class LoadBooks extends BaseEntity implements Serializable {

    private List<Book> books;

    public LoadBooks(List<Book> books) {
        this.books = books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }


}
