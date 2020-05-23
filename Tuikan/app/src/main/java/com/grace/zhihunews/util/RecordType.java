package com.grace.zhihunews.util;

public enum RecordType {
    DAY("day.txt"), TODAY("today.txt"), CALENDAR("calendar.txt"),
    WORD("word.txt"), PLAN("plan.txt"), WORD_LIST("wordlist.txt");

    private String path;

    private RecordType(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}