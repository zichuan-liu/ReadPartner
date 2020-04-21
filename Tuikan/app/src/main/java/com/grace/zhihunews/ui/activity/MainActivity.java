package com.grace.zhihunews.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.grace.zhihunews.R;
import com.grace.zhihunews.ui.base.BaseActivity;
import com.grace.zhihunews.ui.fragment.AboutFragment;
import com.grace.zhihunews.ui.fragment.BookListFragment;
import com.grace.zhihunews.ui.fragment.FeatureListFragment;
import com.grace.zhihunews.ui.fragment.GirlsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private BookListFragment mBookListFragment;
    private GirlsFragment mGirlsFragment;
    private AboutFragment mAboutFragment;
    private FeatureListFragment mFeatureListFragment;
    private FragmentManager fm;

    @Override
    protected void initVariables() {
        fm = getFragmentManager();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .addItem(new BottomNavigationItem(R.mipmap.ic_main, "首页").setInActiveColorResource(R.color.grey).setActiveColorResource(R.color.item_green))
                .addItem(new BottomNavigationItem(R.mipmap.ic_discover, "发现").setInActiveColorResource(R.color.grey).setActiveColorResource(R.color.item_green))
                .addItem(new BottomNavigationItem(R.mipmap.ic_world, "世界").setInActiveColorResource(R.color.grey).setActiveColorResource(R.color.item_green))
                .addItem(new BottomNavigationItem(R.mipmap.ic_about_me, "我的").setInActiveColorResource(R.color.grey).setActiveColorResource(R.color.item_green))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        showFragment(0);
                        break;
                    case 1:
                        showFragment(1);
                        break;
                    case 2:
                        showFragment(2);
                        break;
                    case 3:
                        showFragment(3);
                        break;
                }

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        showFragment(0);
    }

    @Override
    protected void loadData() {

    }

    private void showFragment(int position) {
        FragmentTransaction ft = fm.beginTransaction();
        hideAllFragment(ft);
        switch (position) {
            case 0 : if (mBookListFragment != null) {
                        ft.show(mBookListFragment);
                    } else {
                        mBookListFragment = new BookListFragment();
                        ft.add(R.id.frame_layout, mBookListFragment);
                    }
                break;
            case 1 : if (mGirlsFragment != null) {
                        ft.show(mGirlsFragment);
                    } else {
                        mGirlsFragment = new GirlsFragment();
                        ft.add(R.id.frame_layout, mGirlsFragment);
                    }
                break;
            case 2 : if (mFeatureListFragment != null) {
                        ft.show(mFeatureListFragment);
                    } else {
                        mFeatureListFragment = new FeatureListFragment();
                        ft.add(R.id.frame_layout, mFeatureListFragment);
                    }
                break;
            case 3 : if (mAboutFragment != null) {
                        ft.show(mAboutFragment);
                    } else {
                        mAboutFragment = new AboutFragment();
                        ft.add(R.id.frame_layout, mAboutFragment);
                    }
                break;
        }
        ft.commit();
    }

    private void hideAllFragment(FragmentTransaction ft) {
        if (mBookListFragment != null) {
            ft.hide(mBookListFragment);
        }
        if (mGirlsFragment != null) {
            ft.hide(mGirlsFragment);
        }
        if (mFeatureListFragment != null) {
            ft.hide(mFeatureListFragment);
        }
        if (mAboutFragment != null) {
            ft.hide(mAboutFragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //super.onRestoreInstanceState(savedInstanceState);
    }
}
