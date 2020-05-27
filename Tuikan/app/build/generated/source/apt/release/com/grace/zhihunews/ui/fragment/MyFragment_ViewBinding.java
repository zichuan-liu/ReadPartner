// Generated code from Butter Knife. Do not modify!
package com.grace.zhihunews.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.grace.zhihunews.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyFragment_ViewBinding<T extends MyFragment> implements Unbinder {
  protected T target;

  @UiThread
  public MyFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.miv_avatar = Utils.findRequiredViewAsType(source, R.id.iv_avatar_head, "field 'miv_avatar'", ImageView.class);
    target.tv_nickname = Utils.findRequiredViewAsType(source, R.id.tv_nickname, "field 'tv_nickname'", TextView.class);
    target.tv_introduction = Utils.findRequiredViewAsType(source, R.id.tv_introduction, "field 'tv_introduction'", TextView.class);
    target.iv_add = Utils.findRequiredViewAsType(source, R.id.iv_add, "field 'iv_add'", ImageView.class);
    target.iv_task = Utils.findRequiredViewAsType(source, R.id.iv_task, "field 'iv_task'", ImageView.class);
    target.iv_shop = Utils.findRequiredViewAsType(source, R.id.iv_shop, "field 'iv_shop'", ImageView.class);
    target.iv_friend = Utils.findRequiredViewAsType(source, R.id.iv_friend, "field 'iv_friend'", ImageView.class);
    target.rv_cake_word = Utils.findRequiredViewAsType(source, R.id.rv_cake_word, "field 'rv_cake_word'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.cake = Utils.findRequiredViewAsType(source, R.id.cake, "field 'cake'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.miv_avatar = null;
    target.tv_nickname = null;
    target.tv_introduction = null;
    target.iv_add = null;
    target.iv_task = null;
    target.iv_shop = null;
    target.iv_friend = null;
    target.rv_cake_word = null;
    target.toolbar = null;
    target.cake = null;

    this.target = null;
  }
}
