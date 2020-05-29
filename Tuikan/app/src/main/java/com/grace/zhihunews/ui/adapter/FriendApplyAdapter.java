package com.grace.zhihunews.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.network.entity.FriendApply;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendApplyAdapter extends RecyclerView.Adapter<FriendApplyAdapter.ViewHolder> {
    private Context mContext;
    private List<FriendApply> friendApplies;

    public FriendApplyAdapter(Context context, List<FriendApply> friendApplies) {
        mContext = context;
        this.friendApplies = friendApplies;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        //TODO 适配器修改
        @BindView(R.id.friend_icon)
        ImageView friendIcon;
        @BindView(R.id.friend_id)
        TextView friendId;
        @BindView(R.id.friend_login_time)
        TextView friendLoginTime;
        @BindView(R.id.friend_reason)
        TextView friendReason;
        @BindView(R.id.friend_reading)
        TextView friendReading;
        @BindView(R.id.friend_hoping)
        TextView friendHoping;
        @BindView(R.id.agree_button)
        Button agreeButton;
        @BindView(R.id.disagree_button)
        Button disagreeButton;


        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public FriendApplyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View storyView = LayoutInflater.from(context).inflate(R.layout.item_friend_apply, parent, false);
        final FriendApplyAdapter.ViewHolder viewHolder = new FriendApplyAdapter.ViewHolder(storyView);
        storyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventBus.getDefault().post(new GotoBooksDetailEvent(friends.get(viewHolder.getLayoutPosition()).getId()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FriendApplyAdapter.ViewHolder holder, int position) {
        FriendApply friendApply = friendApplies.get(position);
        Picasso.with(mContext).load(friendApply.getIcon()).into(holder.friendIcon);
        holder.friendId.setText(friendApply.getId());
        holder.friendLoginTime.setText(friendApply.getLoginTime());
        holder.friendReason.setText(friendApply.getReason());
        holder.friendReading.setText(friendApply.getReading());
        holder.friendHoping.setText(friendApply.getHopingTime());
    }

    @Override
    public int getItemCount() {
        return friendApplies.size();
    }
}
