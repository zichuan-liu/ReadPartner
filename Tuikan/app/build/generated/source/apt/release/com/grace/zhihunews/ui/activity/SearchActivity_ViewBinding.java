// Generated code from Butter Knife. Do not modify!
package com.grace.zhihunews.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.grace.zhihunews.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchActivity_ViewBinding<T extends SearchActivity> implements Unbinder {
  protected T target;

  @UiThread
  public SearchActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.rvReadList = Utils.findRequiredViewAsType(source, R.id.rv_read, "field 'rvReadList'", RecyclerView.class);
    target.mSwipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipe_refresh_layout, "field 'mSwipeRefreshLayout'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rvReadList = null;
    target.mSwipeRefreshLayout = null;

    this.target = null;
  }
}
