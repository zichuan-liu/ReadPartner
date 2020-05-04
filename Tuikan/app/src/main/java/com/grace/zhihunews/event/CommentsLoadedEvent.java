package com.grace.zhihunews.event;

import com.grace.zhihunews.network.entity.SocialComments;
/**
 * Created by Administrator on 2016/9/9.
 */
public class CommentsLoadedEvent {

    public SocialComments socialComments;

    public CommentsLoadedEvent(SocialComments socialComments) {
        this.socialComments = socialComments;
    }

}
