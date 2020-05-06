package com.grace.zhihunews.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.ui.activity.BookActivity;
import com.grace.zhihunews.ui.base.BaseFragment;

/**
 * author : 刘子川
 * e-mail : 775269512@qq.com
 * date   : 2020/5/516:04
 * version: 1.0
 */
public class MyFragment extends BaseFragment {
    ImageView miv_avatar;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);
        miv_avatar = (ImageView) view.findViewById(R.id.iv_avatar);
        miv_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }

        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
