package com.grace.zhihunews.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.grace.zhihunews.R;

public class WxSearchView extends KylinSearchView{

    private RelativeLayout searchFrame;
    private EditText edtSearch;
    private ImageView ivSearch;

    public WxSearchView(Context context) {
        super(context);
    }

    public WxSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public int getLayoutId() {
        return R.layout.layout_wx_search;
    }

    @Override
    public void initChildView() {
        searchFrame = (RelativeLayout) seachView.findViewById(R.id.rl_content_wx);
        edtSearch = (EditText) seachView.findViewById(R.id.edt_search_wx);
        ivSearch = (ImageView) seachView.findViewById(R.id.iv_search_wx);
    }

    @Override
    public ImageView getImageView() {
        return ivSearch;
    }

    @Override
    public EditText getEditText() {
        return edtSearch;
    }

    @Override
    public RelativeLayout getSearchFrame() {
        return searchFrame;
    }
}