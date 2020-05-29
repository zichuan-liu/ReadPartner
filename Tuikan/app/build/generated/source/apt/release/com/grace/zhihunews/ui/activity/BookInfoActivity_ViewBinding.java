// Generated code from Butter Knife. Do not modify!
package com.grace.zhihunews.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.grace.zhihunews.R;
import com.grace.zhihunews.ui.view.CommentExpandableListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BookInfoActivity_ViewBinding<T extends BookInfoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public BookInfoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.book_img = Utils.findRequiredViewAsType(source, R.id.book_img, "field 'book_img'", ImageView.class);
    target.book_title = Utils.findRequiredViewAsType(source, R.id.book_title, "field 'book_title'", TextView.class);
    target.book_score = Utils.findRequiredViewAsType(source, R.id.book_score, "field 'book_score'", AppCompatRatingBar.class);
    target.book_score_data = Utils.findRequiredViewAsType(source, R.id.book_score_data, "field 'book_score_data'", TextView.class);
    target.book_writer = Utils.findRequiredViewAsType(source, R.id.book_writer, "field 'book_writer'", TextView.class);
    target.book_progress = Utils.findRequiredViewAsType(source, R.id.book_progress, "field 'book_progress'", TextView.class);
    target.book_time = Utils.findRequiredViewAsType(source, R.id.book_time, "field 'book_time'", TextView.class);
    target.book_info = Utils.findRequiredViewAsType(source, R.id.book_info, "field 'book_info'", TextView.class);
    target.book_paragraph = Utils.findRequiredViewAsType(source, R.id.book_paragraph, "field 'book_paragraph'", TextView.class);
    target.rv_similar_list = Utils.findRequiredViewAsType(source, R.id.rv_similar_list, "field 'rv_similar_list'", RecyclerView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_normal, "field 'toolbar'", Toolbar.class);
    target.book_onload = Utils.findRequiredViewAsType(source, R.id.book_onload, "field 'book_onload'", Button.class);
    target.book_download = Utils.findRequiredViewAsType(source, R.id.book_download, "field 'book_download'", Button.class);
    target.book_read = Utils.findRequiredViewAsType(source, R.id.book_read, "field 'book_read'", Button.class);
    target.book_histor = Utils.findRequiredViewAsType(source, R.id.book_histor, "field 'book_histor'", Button.class);
    target.book_light = Utils.findRequiredViewAsType(source, R.id.book_light, "field 'book_light'", Button.class);
    target.expandableListView = Utils.findRequiredViewAsType(source, R.id.detail_page_lv_comment, "field 'expandableListView'", CommentExpandableListView.class);
    target.bt_comment = Utils.findRequiredViewAsType(source, R.id.detail_page_do_comment, "field 'bt_comment'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.book_img = null;
    target.book_title = null;
    target.book_score = null;
    target.book_score_data = null;
    target.book_writer = null;
    target.book_progress = null;
    target.book_time = null;
    target.book_info = null;
    target.book_paragraph = null;
    target.rv_similar_list = null;
    target.toolbar = null;
    target.book_onload = null;
    target.book_download = null;
    target.book_read = null;
    target.book_histor = null;
    target.book_light = null;
    target.expandableListView = null;
    target.bt_comment = null;

    this.target = null;
  }
}
