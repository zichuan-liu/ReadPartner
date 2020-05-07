package com.grace.zhihunews.ui.fragment;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
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

    private Unbinder unbinder;


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
        miv_avatar.setOnClickListener(new JumpAboutFragment());
    }

    @Override
    protected void loadData() {}


    public class JumpAboutFragment implements View.OnClickListener{
        /**
         * https://www.cnblogs.com/codingblock/p/4808809.html
         * @param view
         */
        //Fragment跳转Fragment
        @SuppressLint("ResourceType")
        @Override
        public void onClick(View view){
            Log.i("lzc",  "嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻");
//            getFragmentManager()
//                    .beginTransaction()
//                    .replace(R.layout.activity_main, new AboutFragment())
//                    .commit();

            //获取fragment的实例
            AboutFragment aboutFragment=new AboutFragment();
            //获取Fragment的管理器
            FragmentManager fragmentManager=getActivity().getFragmentManager();
            //开启fragment的事物,在这个对象里进行fragment的增删替换等操作。
            FragmentTransaction ft=fragmentManager.beginTransaction();
            //跳转到fragment，第一个参数为所要替换的位置id，第二个参数是替换后的fragment
            ft.replace(R.id.frame_layout
                    ,aboutFragment);
            //提交事物
            ft.commit();
        }
    }


    //BookListContact.INewsListView接口方法实现
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
