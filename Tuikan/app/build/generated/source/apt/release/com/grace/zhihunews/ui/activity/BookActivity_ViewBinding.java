// Generated code from Butter Knife. Do not modify!
package com.grace.zhihunews.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.grace.zhihunews.R;
import com.grace.zhihunews.ui.view.BookPageContainer;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BookActivity_ViewBinding<T extends BookActivity> implements Unbinder {
  protected T target;

  @UiThread
  public BookActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.book_pager = Utils.findRequiredViewAsType(source, R.id.book_pager, "field 'book_pager'", BookPageContainer.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.book_pager = null;

    this.target = null;
  }
}
