package com.chenshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.chenshop.R;
import com.chenshop.model.bean.Feedback;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.RecyclerViewHolder> implements View.OnClickListener {
    Context context;

    ArrayList<Feedback> beanList;
    public static final String BEAN_EXTRA = "BeanClass";

    public CommentAdapter(Context context, ArrayList<Feedback> bean) {
        this.beanList = bean;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item_rate, viewGroup, false);
        return new CommentAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.username.setText(beanList.get(i).getFullName());
        recyclerViewHolder.comment.setText(beanList.get(i).getContent());
        recyclerViewHolder.date.setText(beanList.get(i).getDateComment());
        recyclerViewHolder.ratingBar.setRating(beanList.get(i).getRating());
    }

    @NonNull


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    private Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public void onClick(View v) {

    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView comment;
        TextView date;
        RatingBar ratingBar;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.txt_name);
            comment = (TextView) itemView.findViewById(R.id.txt_comment);
            date = (TextView) itemView.findViewById(R.id.txt_time);
            ratingBar = itemView.findViewById(R.id.rt_rate);

        }
    }
}