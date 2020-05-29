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

public class BooksAdapter$ViewHolder_ViewBinding<T extends BooksAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public BooksAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.bookImg = Utils.findRequiredViewAsType(source, R.id.book_img, "field 'bookImg'", ImageView.class);
    target.bookTitle = Utils.findRequiredViewAsType(source, R.id.book_title, "field 'bookTitle'", TextView.class);
    target.bookWriter = Utils.findRequiredViewAsType(source, R.id.book_writer, "field 'bookWriter'", TextView.class);
    target.bookProgress = Utils.findRequiredViewAsType(source, R.id.book_progress, "field 'bookProgress'", TextView.class);
    target.bookTime = Utils.findRequiredViewAsType(source, R.id.book_time, "field 'bookTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bookImg = null;
    target.bookTitle = null;
    target.bookWriter = null;
    target.bookProgress = null;
    target.bookTime = null;

    this.target = null;
  }
}
