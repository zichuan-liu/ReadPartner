// Generated code from Butter Knife. Do not modify!
package com.grace.zhihunews.ui.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.grace.zhihunews.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FriendAdapater$ViewHolder_ViewBinding<T extends FriendAdapater.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public FriendAdapater$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.friendIcon = Utils.findRequiredViewAsType(source, R.id.friend_icon, "field 'friendIcon'", ImageView.class);
    target.friendId = Utils.findRequiredViewAsType(source, R.id.friend_id, "field 'friendId'", TextView.class);
    target.friendTime = Utils.findRequiredViewAsType(source, R.id.friend_time, "field 'friendTime'", TextView.class);
    target.friendStatus = Utils.findRequiredViewAsType(source, R.id.friend_status, "field 'friendStatus'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.friendIcon = null;
    target.friendId = null;
    target.friendTime = null;
    target.friendStatus = null;

    this.target = null;
  }
}
