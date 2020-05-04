package com.grace.zhihunews.network.entity;

import java.util.List;

public class SocialComments extends BaseEntity {
    private List<Comment> comments;
    private List<TopStory> topStories;

    public SocialComments(List<Comment> comments, List<TopStory> topStories) {
        this.comments = comments;
        this.topStories = topStories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<TopStory> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<TopStory> topStories) {
        this.topStories = topStories;
    }
}
