package com.grace.zhihunews.ui.fragment;

/**
 * author : 刘子川
 * e-mail : 775269512@qq.com
 * date   : 2020/5/2914:47
 * version: 1.0
 */
public interface OnClickAddListener {
    /**
     * 点击确定按钮事件
     */
    public void onPositiveClick();
    /**
     * 点击取消按钮事件
     */
    public void onNegtiveClick();

    public void onPathClick();

    public void onImgClick();

    public void onTitleClick();

    public void onWriteClick();
}
