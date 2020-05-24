package com.chenshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chenshop.R;
import com.chenshop.model.bean.Beanclass;
import com.chenshop.view.activity.ProductDetailActivity;
import com.chenshop.view.activity.RateActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class RateRecycleViewAdapter extends RecyclerView.Adapter<RateRecycleViewAdapter.RecyclerViewHolder> implements View.OnClickListener {
    Context context;

    ArrayList<Beanclass> beanList;
    public  static  final  String BEAN_EXTRA = "BeanClass";
    public RateRecycleViewAdapter(Context context, ArrayList<Beanclass> bean)
    {
        this.beanList = bean;
        this.context = context;
    }

    @NonNull
    @Override
    public RateRecycleViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item_my_rate, viewGroup, false);
        return new RateRecycleViewAdapter.RecyclerViewHolder(view);
    }

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull RateRecycleViewAdapter.RecyclerViewHolder recyclerViewHolder, int position) {

        // Beanclass bean = (Beanclass) getItem(position);

        recyclerViewHolder.image1.setImageResource(beanList.get(position).getImage1());
        recyclerViewHolder.title1.setText(beanList.get(position).getTitle1());
        recyclerViewHolder.discription1.setText(beanList.get(position).getDiscription1());
        recyclerViewHolder.subtitle1.setText(beanList.get(position).getDate1());
        recyclerViewHolder.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProduct(beanList.get(position));
            }
        });
        recyclerViewHolder.title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProduct(beanList.get(position));
            }
        });
        recyclerViewHolder.rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRate();
            }
        });
    }

    public void showProduct(Beanclass bean) {
        Intent i = new Intent(context, ProductDetailActivity.class);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra(BEAN_EXTRA, (Serializable) bean);
        context.startActivity(i);

    }

    public void showRate(){
        Intent intent = new Intent(context, RateActivity.class);
        context.startActivity(intent);
    }
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
        ImageView image1;
        TextView subtitle1;
        TextView discription1;
        TextView title1;
        TextView rate;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            image1 = (ImageView) itemView.findViewById(R.id.thumb);
            title1 = (TextView) itemView.findViewById(R.id.title);
            subtitle1 = (TextView) itemView.findViewById(R.id.subtitle);
            discription1 = (TextView) itemView.findViewById(R.id.description);
            rate = itemView.findViewById(R.id.rate);

        }
    }
}