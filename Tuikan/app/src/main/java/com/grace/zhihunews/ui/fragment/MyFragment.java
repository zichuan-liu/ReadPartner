package com.grace.zhihunews.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : 刘子川
 * e-mail : 775269512@qq.com
 * date   : 2020/5/516:04
 * version: 1.0
 */
public class MyFragment extends BaseFragment {
    @BindView(R.id.iv_avatar_head)
    ImageView miv_avatar;
    @BindView(R.id.tv_nickname)
    TextView tv_nickname;
    @BindView(R.id.tv_introduction)
    TextView tv_introduction;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    @BindView(R.id.rv_cake_word)
    TextView rv_cake_word;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Unbinder unbinder;
    private Context mContext;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        toolbar.setTitle("");
        toolbar.inflateMenu(R.menu.menu_my);
        toolbar.setOnMenuItemClickListener(new JumpAboutFragment());
//        miv_avatar.setOnClickListener(new JumpAboutFragment());
    }

    @Override
    protected void loadData() {}


    public class JumpAboutFragment implements Toolbar.OnMenuItemClickListener{
        /**
         * https://www.cnblogs.com/codingblock/p/4808809.html
         * @param item
         */
        //Fragment跳转Fragment
//        @SuppressLint("ResourceType")
        @Override
        public boolean onMenuItemClick(MenuItem item){
            switch (item.getItemId()) {
                case R.id.action_setting:
                    Log.i("lzc",  "嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻");
                    //            getFragmentManager()
                    //                    .beginTransaction()
                    //                    .replace(R.layout.activity_main, new AboutFragment())
                    //                    .commit();
                    Intent intent = new Intent(mContext, AboutFragment.class);
                    mContext.startActivity(intent);
                    break;
            }
            return true;
        }
    }


    //BookListContact.INewsListView接口方法实现
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
