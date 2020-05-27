package com.grace.zhihunews.network.entity;

import java.io.Serializable;
import java.util.List;

public class LoadFriends extends BaseEntity implements Serializable {
    private List<Friend> friends;

    public LoadFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public List<Friend> getFriends() {
        return friends;
    }

}
