// Generated code from Butter Knife. Do not modify!
package com.grace.zhihunews.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.grace.zhihunews.R;
import com.zanlabs.widget.infiniteviewpager.InfiniteViewPager;
import com.zanlabs.widget.infiniteviewpager.indicator.CirclePageIndicator;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DiscoverFragment_ViewBinding<T extends DiscoverFragment> implements Unbinder {
  protected T target;

  @UiThread
  public DiscoverFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.mSwipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.srl_discover, "field 'mSwipeRefreshLayout'", SwipeRefreshLayout.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.recommendList = Utils.findRequiredViewAsType(source, R.id.rv_recommend_list, "field 'recommendList'", RecyclerView.class);
    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'mViewPager'", InfiniteViewPager.class);
    target.mIndicator = Utils.findRequiredViewAsType(source, R.id.indicator, "field 'mIndicator'", CirclePageIndicator.class);
    target.rvHeader = Utils.findRequiredViewAsType(source, R.id.rv_header, "field 'rvHeader'", RecyclerViewHeader.class);
    target.discover_free = Utils.findRequiredViewAsType(source, R.id.discover_free, "field 'discover_free'", Button.class);
    target.discover_rank = Utils.findRequiredViewAsType(source, R.id.discover_rank, "field 'discover_rank'", Button.class);
    target.discover_vip = Utils.findRequiredViewAsType(source, R.id.discover_vip, "field 'discover_vip'", Button.class);
    target.discover_hear = Utils.findRequiredViewAsType(source, R.id.discover_hear, "field 'discover_hear'", Button.class);
    target.discover_menu = Utils.findRequiredViewAsType(source, R.id.discover_menu, "field 'discover_menu'", GridLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mSwipeRefreshLayout = null;
    target.toolbar = null;
    target.recommendList = null;
    target.mViewPager = null;
    target.mIndicator = null;
    target.rvHeader = null;
    target.discover_free = null;
    target.discover_rank = null;
    target.discover_vip = null;
    target.discover_hear = null;
    target.discover_menu = null;

    this.target = null;
  }
}
