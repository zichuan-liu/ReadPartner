package com.grace.zhihunews.event;

import com.grace.zhihunews.network.entity.LoadFriends;

public class FriendLoadedEvent {
    public LoadFriends loadFriends;
    public FriendLoadedEvent(LoadFriends loadFriends){
        this.loadFriends = loadFriends;
    }

}
