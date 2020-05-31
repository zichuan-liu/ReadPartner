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
import com.grace.zhihunews.event.GotoBooksDetailEvent;
import com.grace.zhihunews.event.GotoBooksPagerEvent;
import com.grace.zhihunews.network.entity.Book;
import com.grace.zhihunews.ui.activity.BookActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/9/1.
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private Context mContext;
    private List<Book> books;

    public BooksAdapter(Context context, List<Book> books) {
        mContext = context;
        this.books = books;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        //TODO 适配器修改
        @BindView(R.id.book_img)
        ImageView bookImg;
        @BindView(R.id.book_title)
        TextView bookTitle;
        @BindView(R.id.book_writer)
        TextView bookWriter;
        @BindView(R.id.book_progress)
        TextView bookProgress;
        @BindView(R.id.book_time)
        TextView bookTime;


        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View storyView = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        final ViewHolder viewHolder = new ViewHolder(storyView);
        storyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new GotoBooksPagerEvent(books.get(viewHolder.getLayoutPosition()).getId()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BooksAdapter.ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bookTitle.setText(book.getTitle());
        holder.bookWriter.setText(book.getWriter());
        holder.bookProgress.setText(book.getProgress());
        holder.bookTime.setText(book.getTime());
        Picasso.with(mContext).load(book.getImage()).into(holder.bookImg);
    }

    @Override
    public int getItemCount() {
            return books.size();
    }

}
