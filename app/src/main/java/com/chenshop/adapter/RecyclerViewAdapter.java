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
import android.widget.Toast;

import com.chenshop.R;
import com.chenshop.model.bean.Beanclass;
import com.chenshop.view.activity.CartActivity;
import com.chenshop.view.activity.ProductDetailActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> implements View.OnClickListener {
    Context context;
    CartActivity cartActivity = new CartActivity();
    TextView textView;

    ArrayList<Beanclass> beanList;
    public  static  final  String BEAN_EXTRA = "BeanClass";
    public RecyclerViewAdapter(Context context, ArrayList<Beanclass> bean, TextView textView)
    {
        this.beanList = bean;
        this.context = context;
        this.textView = textView;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item_cart, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int position) {

       // Beanclass bean = (Beanclass) getItem(position);

        recyclerViewHolder.image1.setImageResource(beanList.get(position).getImage1());
        recyclerViewHolder.title1.setText(beanList.get(position).getTitle1());
        recyclerViewHolder.discription1.setText(beanList.get(position).getDiscription1());
        recyclerViewHolder.subtitle1.setText(beanList.get(position).getDate1());
        recyclerViewHolder.image1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showProduct(beanList.get(position));
            }
        });
        recyclerViewHolder.title1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showProduct(beanList.get(position));
            }
        });
        recyclerViewHolder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beanList.remove(position);
                notifyItemChanged(position);
                notifyItemRangeChanged(position, beanList.size());

                textView.setText("0đ");
                Toast.makeText(context,"Đã xóa sản phẩm" ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showProduct(Beanclass bean) {
        Intent i = new Intent(context, ProductDetailActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra(BEAN_EXTRA, (Serializable) bean);
        context.startActivity(i);

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
        ImageView remove;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            image1 = (ImageView) itemView.findViewById(R.id.thumb);
            title1 = (TextView) itemView.findViewById(R.id.title);
            subtitle1 = (TextView) itemView.findViewById(R.id.subtitle);
            discription1 = (TextView) itemView.findViewById(R.id.description);
            remove = (ImageView) itemView.findViewById(R.id.remove);

        }
    }
}
