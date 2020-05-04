package com.grace.zhihunews.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.event.GotoNewsDetailEvent;
import com.grace.zhihunews.network.entity.Book;
import com.grace.zhihunews.network.entity.Comment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class CommentsAdapater extends RecyclerView.Adapter<CommentsAdapater.ViewHolder> {
    private Context mContext;
    private List<Comment> comments;

    public CommentsAdapater(Context context, List<Comment> comments) {
        mContext = context;
        this.comments = comments;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        //TODO 适配器修改
        @BindView(R.id.comment_icon)
        ImageView commentIcon;
        @BindView(R.id.comment_nickname)
        TextView commentNickName;
        @BindView(R.id.comment_time)
        TextView commentTime;
        @BindView(R.id.comment_title)
        TextView commentTitle;
        @BindView(R.id.comment_content)
        TextView commentContent;


        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public CommentsAdapater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View storyView = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        final CommentsAdapater.ViewHolder viewHolder = new CommentsAdapater.ViewHolder(storyView);
        storyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new GotoNewsDetailEvent(comments.get(viewHolder.getLayoutPosition()).getId()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommentsAdapater.ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        Picasso.with(mContext).load(comment.getIcon()).into(holder.commentIcon);
        holder.commentNickName.setText(comment.getNickname());
        holder.commentTime.setText(comment.getTime());
        holder.commentTitle.setText(comment.getTitle());
        holder.commentContent.setText(comment.getContent());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
