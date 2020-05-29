package com.grace.zhihunews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.network.entity.Book;
import com.grace.zhihunews.network.entity.SimilarBook;
import com.grace.zhihunews.ui.activity.BookActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/1.
 */
public class SimilarBookAdapter extends RecyclerView.Adapter<SimilarBookAdapter.ViewHolder> {

    private Context mContext;
    private List<SimilarBook> similarBooks;

    public SimilarBookAdapter(Context context, List<SimilarBook> similarBooks) {
        mContext = context;
        this.similarBooks = similarBooks;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        //TODO 适配器修改
        @BindView(R.id.book_img)
        ImageView bookImg;
        @BindView(R.id.book_title)
        TextView bookTitle;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View storyView = LayoutInflater.from(context).inflate(R.layout.item_similar_book, parent, false);
        final ViewHolder viewHolder = new ViewHolder(storyView);
        storyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SimilarBookAdapter.ViewHolder holder, int position) {
        SimilarBook similarBook = similarBooks.get(position);
        holder.bookTitle.setText(similarBook.getBook_title());
        Picasso.with(mContext).load(similarBook.getBook_img()).into(holder.bookImg);
    }

    @Override
    public int getItemCount() {
        return similarBooks.size();
    }

}
