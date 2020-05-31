// Generated code from Butter Knife. Do not modify!
package com.grace.zhihunews.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.grace.zhihunews.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BookListFragment_ViewBinding<T extends BookListFragment> implements Unbinder {
  protected T target;

  @UiThread
  public BookListFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.mSwipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipe_refresh_layout, "field 'mSwipeRefreshLayout'", SwipeRefreshLayout.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_book, "field 'toolbar'", Toolbar.class);
    target.toolbar_edit = Utils.findRequiredViewAsType(source, R.id.toolbar_edit, "field 'toolbar_edit'", Button.class);
    target.rvReadList = Utils.findRequiredViewAsType(source, R.id.rv_read, "field 'rvReadList'", RecyclerView.class);
    target.rvHeader = Utils.findRequiredViewAsType(source, R.id.rv_header, "field 'rvHeader'", RecyclerViewHeader.class);
    target.rvText = Utils.findRequiredViewAsType(source, R.id.rv_time, "field 'rvText'", TextView.class);
    target.qiandao = Utils.findRequiredViewAsType(source, R.id.qiandao, "field 'qiandao'", Button.class);
    target.lvcheng = Utils.findRequiredViewAsType(source, R.id.lvcheng, "field 'lvcheng'", ImageButton.class);
    target.rlGetGiftData = Utils.findRequiredViewAsType(source, R.id.rl_get_gift_view, "field 'rlGetGiftData'", RelativeLayout.class);
    target.rlQuedingBtn = Utils.findRequiredViewAsType(source, R.id.rl_queding_btn, "field 'rlQuedingBtn'", RelativeLayout.class);
    target.ivSun = Utils.findRequiredViewAsType(source, R.id.iv_sun, "field 'ivSun'", ImageView.class);
    target.tvGetSunValue = Utils.findRequiredViewAsType(source, R.id.tv_text_one, "field 'tvGetSunValue'", TextView.class);
    target.ivSunBg = Utils.findRequiredViewAsType(source, R.id.iv_sun_bg, "field 'ivSunBg'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mSwipeRefreshLayout = null;
    target.toolbar = null;
    target.toolbar_edit = null;
    target.rvReadList = null;
    target.rvHeader = null;
    target.rvText = null;
    target.qiandao = null;
    target.lvcheng = null;
    target.rlGetGiftData = null;
    target.rlQuedingBtn = null;
    target.ivSun = null;
    target.tvGetSunValue = null;
    target.ivSunBg = null;

    this.target = null;
  }
}
