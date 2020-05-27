package com.grace.zhihunews.network.entity;

import java.io.Serializable;
import java.util.List;

public class LoadApplies  extends BaseEntity implements Serializable {
    private List<FriendApply> friendApplies;

    public LoadApplies(List<FriendApply> friendApplies) {
        this.friendApplies = friendApplies;
    }

    public void setApplies(List<FriendApply> friendApplies) {
        this.friendApplies = friendApplies;
    }

    public List<FriendApply> getApplies() {
        return friendApplies;
    }
}
