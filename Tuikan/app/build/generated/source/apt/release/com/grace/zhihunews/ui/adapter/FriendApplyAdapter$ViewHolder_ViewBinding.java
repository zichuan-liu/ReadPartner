// Generated code from Butter Knife. Do not modify!
package com.grace.zhihunews.ui.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.grace.zhihunews.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FriendApplyAdapter$ViewHolder_ViewBinding<T extends FriendApplyAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public FriendApplyAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.friendIcon = Utils.findRequiredViewAsType(source, R.id.friend_icon, "field 'friendIcon'", ImageView.class);
    target.friendId = Utils.findRequiredViewAsType(source, R.id.friend_id, "field 'friendId'", TextView.class);
    target.friendLoginTime = Utils.findRequiredViewAsType(source, R.id.friend_login_time, "field 'friendLoginTime'", TextView.class);
    target.friendReason = Utils.findRequiredViewAsType(source, R.id.friend_reason, "field 'friendReason'", TextView.class);
    target.friendReading = Utils.findRequiredViewAsType(source, R.id.friend_reading, "field 'friendReading'", TextView.class);
    target.friendHoping = Utils.findRequiredViewAsType(source, R.id.friend_hoping, "field 'friendHoping'", TextView.class);
    target.agreeButton = Utils.findRequiredViewAsType(source, R.id.agree_button, "field 'agreeButton'", Button.class);
    target.disagreeButton = Utils.findRequiredViewAsType(source, R.id.disagree_button, "field 'disagreeButton'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.friendIcon = null;
    target.friendId = null;
    target.friendLoginTime = null;
    target.friendReason = null;
    target.friendReading = null;
    target.friendHoping = null;
    target.agreeButton = null;
    target.disagreeButton = null;

    this.target = null;
  }
}
