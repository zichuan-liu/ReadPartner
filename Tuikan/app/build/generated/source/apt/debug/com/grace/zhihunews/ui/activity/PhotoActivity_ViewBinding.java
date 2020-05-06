// Generated code from Butter Knife. Do not modify!
package com.grace.zhihunews.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.grace.zhihunews.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PhotoActivity_ViewBinding<T extends PhotoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public PhotoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.ivPhoto = Utils.findRequiredViewAsType(source, R.id.iv_photo, "field 'ivPhoto'", ImageView.class);
    target.mAppBar = Utils.findRequiredViewAsType(source, R.id.app_bar_layout, "field 'mAppBar'", AppBarLayout.class);
    target.mToolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'mToolbar'", Toolbar.class);
    target.mBottomNavigationBar = Utils.findRequiredViewAsType(source, R.id.bottom_navigation_bar, "field 'mBottomNavigationBar'", BottomNavigationBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivPhoto = null;
    target.mAppBar = null;
    target.mToolbar = null;
    target.mBottomNavigationBar = null;

    this.target = null;
  }
}
