package com.grace.zhihunews.network.entity;

public class FriendApply extends BaseEntity {
    String icon;
    String id;
    String loginTime;
    String reason;
    String reading;
    String hopingTime;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getHopingTime() {
        return hopingTime;
    }

    public void setHopingTime(String hopingTime) {
        this.hopingTime = hopingTime;
    }

}
