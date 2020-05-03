package com.grace.zhihunews.network.entity;

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
