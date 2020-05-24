package com.grace.zhihunews.ui.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.ui.base.BaseFragment;
import com.grace.zhihunews.ui.view.FriendDialog;
import com.grace.zhihunews.ui.view.ShopDialog;
import com.grace.zhihunews.ui.view.TaskDialog;
import com.grace.zhihunews.util.ScreenUtils;

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
    @BindView(R.id.iv_task)
    ImageView iv_task;
    @BindView(R.id.iv_shop)
    ImageView iv_shop;
    @BindView(R.id.iv_friend)
    ImageView iv_friend;
    @BindView(R.id.rv_cake_word)
    TextView rv_cake_word;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Unbinder unbinder;
    private Context mContext;
    private Boolean flag_add;


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
        flag_add=false;
        toolbar.inflateMenu(R.menu.menu_my);
        toolbar.setOnMenuItemClickListener(new JumpAboutFragment());
//        miv_avatar.setOnClickListener(new JumpAboutFragment());
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (! flag_add) {
                    flag_add=true;
                    ObjectAnimator.ofFloat(iv_add, "rotation", 0F, -45F).setDuration(300).start();//设置图片旋转效果为360度旋转
                    ObjectAnimator.ofFloat(iv_task,"alpha",0F,1F).setDuration(300).start();//设置图片透明效果为1像素
                    ObjectAnimator.ofFloat(iv_friend,"alpha",0F,1F).setDuration(300).start();//设置图片透明效果为1像素
                    ObjectAnimator.ofFloat(iv_shop,"alpha",0F,1F).setDuration(300).start();//设置图片透明效果为1像素

                    iv_task.setVisibility(View.VISIBLE);
                    iv_friend.setVisibility(View.VISIBLE);
                    iv_shop.setVisibility(View.VISIBLE);
                    ObjectAnimator.ofFloat(iv_friend,"translationX",0f,220F).setDuration(400).start();//设置X轴移动200像素
                    ObjectAnimator.ofFloat(iv_task,"translationY",0F,-220F).setDuration(400).start();;//设置Y轴移动200像素
                    ObjectAnimator.ofFloat(iv_shop,"translationY",0F,-170F).setDuration(400).start();;//设置Y轴移动200像素
                    ObjectAnimator.ofFloat(iv_shop,"translationX",0F,170F).setDuration(400).start();;//设置Y轴移动200像素

                    clickIconListen();
                }
                else {
                    flag_add=false;
                    ObjectAnimator.ofFloat(iv_add, "rotation", 0F, 45F).setDuration(300).start();//设置图片旋转效果为360度旋转
                    ObjectAnimator.ofFloat(iv_task,"alpha",1F,0F).setDuration(300).start();//设置图片透明效果为1像素
                    ObjectAnimator.ofFloat(iv_friend,"alpha",1F,0F).setDuration(300).start();//设置图片透明效果为1像素
                    ObjectAnimator.ofFloat(iv_shop,"alpha",1F,0F).setDuration(300).start();//设置图片透明效果为1像素
                    ObjectAnimator.ofFloat(iv_friend,"translationX",0f,-220F).setDuration(300).start();//设置X轴移动200像素
                    ObjectAnimator.ofFloat(iv_task,"translationY",0F,220F).setDuration(300).start();;//设置Y轴移动200像素
                    ObjectAnimator.ofFloat(iv_shop,"translationY",0F,170F).setDuration(300).start();;//设置Y轴移动200像素
                    ObjectAnimator.ofFloat(iv_shop,"translationX",0F,-170F).setDuration(300).start();;//设置Y轴移动200像素
                    iv_task.setVisibility(View.INVISIBLE);
                    iv_friend.setVisibility(View.INVISIBLE);
                    iv_shop.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void clickIconListen() {
        iv_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskDialog taskDialog = new TaskDialog(mContext);
                taskDialog.isSingle(true);
                taskDialog.setOnClickBottomListener(
                        new OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                taskDialog.dismiss();
                            }
                            @Override
                            public void onNegtiveClick() {
                                taskDialog.dismiss();
                            }
                        }

                ).show();
                taskDialog.getWindow().setLayout((int)(ScreenUtils.getScreenWidth(mContext)*0.9),LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });
        iv_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FriendDialog friendDialog = new FriendDialog(mContext);
                friendDialog.isSingle(true);
                friendDialog.setOnClickBottomListener(
                        new OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                friendDialog.dismiss();
                            }
                            @Override
                            public void onNegtiveClick() {
                                friendDialog.dismiss();
                            }
                        }

                ).show();
                friendDialog.getWindow().setLayout((int)(ScreenUtils.getScreenWidth(mContext)*0.9),LinearLayout.LayoutParams.WRAP_CONTENT);

            }
        });
        iv_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopDialog shopDialog = new ShopDialog(mContext);
                shopDialog.isSingle(true);
                shopDialog.setOnClickBottomListener(
                        new OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                shopDialog.dismiss();
                            }
                            @Override
                            public void onNegtiveClick() {
                                shopDialog.dismiss();
                            }
                        }

                ).show();
                shopDialog.getWindow().setLayout((int)(ScreenUtils.getScreenWidth(mContext)*0.9),LinearLayout.LayoutParams.WRAP_CONTENT);

            }
        });
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
