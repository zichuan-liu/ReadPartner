package com.grace.zhihunews.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.network.entity.Friend;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class FriendAdapater extends RecyclerView.Adapter<FriendAdapater.ViewHolder> {
    private Context mContext;
    private List<Friend> friends;

    public FriendAdapater(Context context, List<Friend> friends) {
        mContext = context;
        this.friends = friends;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        //TODO 适配器修改
        @BindView(R.id.friend_icon)
        ImageView friendIcon;
        @BindView(R.id.friend_id)
        TextView friendId;
        @BindView(R.id.friend_time)
        TextView friendTime;
        @BindView(R.id.friend_status)
        TextView friendStatus;


        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public FriendAdapater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View storyView = LayoutInflater.from(context).inflate(R.layout.item_friend, parent, false);
        final FriendAdapater.ViewHolder viewHolder = new FriendAdapater.ViewHolder(storyView);
        storyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventBus.getDefault().post(new GotoNewsDetailEvent(friends.get(viewHolder.getLayoutPosition()).getId()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FriendAdapater.ViewHolder holder, int position) {
        Friend friend = friends.get(position);
        Picasso.with(mContext).load(friend.getIcon()).into(holder.friendIcon);
        holder.friendId.setText(friend.getId());
        holder.friendTime.setText(friend.getTime());
        holder.friendStatus.setText(friend.getStatus());
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }
}
