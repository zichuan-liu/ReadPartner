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

public class CommentsAdapater$ViewHolder_ViewBinding<T extends CommentsAdapater.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public CommentsAdapater$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.commentIcon = Utils.findRequiredViewAsType(source, R.id.comment_icon, "field 'commentIcon'", ImageView.class);
    target.commentNickName = Utils.findRequiredViewAsType(source, R.id.comment_nickname, "field 'commentNickName'", TextView.class);
    target.commentTime = Utils.findRequiredViewAsType(source, R.id.comment_time, "field 'commentTime'", TextView.class);
    target.commentTitle = Utils.findRequiredViewAsType(source, R.id.comment_title, "field 'commentTitle'", TextView.class);
    target.commentContent = Utils.findRequiredViewAsType(source, R.id.comment_content, "field 'commentContent'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.commentIcon = null;
    target.commentNickName = null;
    target.commentTime = null;
    target.commentTitle = null;
    target.commentContent = null;

    this.target = null;
  }
}
