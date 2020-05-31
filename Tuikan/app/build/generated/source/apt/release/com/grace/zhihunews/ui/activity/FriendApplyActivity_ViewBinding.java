// Generated code from Butter Knife. Do not modify!
package com.grace.zhihunews.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.grace.zhihunews.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FriendApplyActivity_ViewBinding<T extends FriendApplyActivity> implements Unbinder {
  protected T target;

  @UiThread
  public FriendApplyActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mSwipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.apply_refresh_layout, "field 'mSwipeRefreshLayout'", SwipeRefreshLayout.class);
    target.appliesList = Utils.findRequiredViewAsType(source, R.id.rv_applies_list, "field 'appliesList'", RecyclerView.class);
    target.recyclerViewHeader = Utils.findRequiredViewAsType(source, R.id.rv_header, "field 'recyclerViewHeader'", RecyclerViewHeader.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_apply, "field 'toolbar'", Toolbar.class);
    target.toolbar_title = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbar_title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mSwipeRefreshLayout = null;
    target.appliesList = null;
    target.recyclerViewHeader = null;
    target.toolbar = null;
    target.toolbar_title = null;

    this.target = null;
  }
}
