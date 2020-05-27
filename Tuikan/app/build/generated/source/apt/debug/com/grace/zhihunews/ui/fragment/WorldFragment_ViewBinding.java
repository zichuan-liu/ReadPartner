// Generated code from Butter Knife. Do not modify!
package com.grace.zhihunews.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.grace.zhihunews.R;
import com.zanlabs.widget.infiniteviewpager.InfiniteViewPager;
import com.zanlabs.widget.infiniteviewpager.indicator.CirclePageIndicator;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WorldFragment_ViewBinding<T extends WorldFragment> implements Unbinder {
  protected T target;

  @UiThread
  public WorldFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.mSwipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.srl_world, "field 'mSwipeRefreshLayout'", SwipeRefreshLayout.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.commendList = Utils.findRequiredViewAsType(source, R.id.rv_commend_list, "field 'commendList'", RecyclerView.class);
    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'mViewPager'", InfiniteViewPager.class);
    target.mIndicator = Utils.findRequiredViewAsType(source, R.id.indicator, "field 'mIndicator'", CirclePageIndicator.class);
    target.rvHeader = Utils.findRequiredViewAsType(source, R.id.rv_header, "field 'rvHeader'", RecyclerViewHeader.class);
    target.world_date = Utils.findRequiredViewAsType(source, R.id.world_date, "field 'world_date'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mSwipeRefreshLayout = null;
    target.toolbar = null;
    target.commendList = null;
    target.mViewPager = null;
    target.mIndicator = null;
    target.rvHeader = null;
    target.world_date = null;

    this.target = null;
  }
}
