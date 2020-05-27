package com.grace.zhihunews.event;

import com.grace.zhihunews.network.entity.LoadApplies;

public class FriendApplyLoadedEvent {
    public LoadApplies loadApplies;
    public FriendApplyLoadedEvent(LoadApplies loadApplies){
        this.loadApplies = loadApplies;
    }
}
