// Generated code from Butter Knife. Do not modify!
package com.grace.zhihunews.ui.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.grace.zhihunews.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TopStoriesAdapter$ViewHolder_ViewBinding<T extends TopStoriesAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public TopStoriesAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.ivImage = Utils.findRequiredViewAsType(source, R.id.iv_top_story, "field 'ivImage'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivImage = null;

    this.target = null;
  }
}
