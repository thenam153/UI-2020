package com.chenshop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.chenshop.R;
import com.chenshop.adapter.GAdapter;
import com.chenshop.layout.ExpandableHeightGridView;
import com.chenshop.model.bean.Beanclass;
import com.chenshop.view.activity.HomeActivity;
import com.chenshop.view.activity.ProductDetailActivity;

import java.util.ArrayList;

public class FragContentShopCart extends Fragment {


    private ExpandableHeightGridView gridview;
    private ArrayList<Beanclass> beanclassArrayList;
    private GAdapter gAdapter;
    private View view;

    private int[] IMAGEgrid = {R.drawable.q1, R.drawable.q2, R.drawable.q3, R.drawable.q4, R.drawable.q5, R.drawable.q6};
    private String[] TITLeGgrid = {"Khẩu trang", "Khăn tắm", "Combo tất", "Khăn lên nữ", "Khăn len đỏ", "Thắt lưng nữ"};
    private String[] DIscriptiongrid = {"Min 1% off", "Min 2% off", "Min 5% off", "Min 6% off", "Min 6% off", "Min 5% off"};
    private String[] Dategrid = {"69,000đ", "149,000đ", "100,000đ", "299,000đ", "299,000đ", "350,000đ"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmenttab1, container, false);

        gridview = (ExpandableHeightGridView) view.findViewById(R.id.gridview);
        beanclassArrayList = new ArrayList<Beanclass>();

        for (int i = 0; i < IMAGEgrid.length; i++) {

            Beanclass beanclass = new Beanclass(IMAGEgrid[i], TITLeGgrid[i], DIscriptiongrid[i], Dategrid[i]);
            beanclassArrayList.add(beanclass);

        }
        gAdapter = new GAdapter(getActivity(), beanclassArrayList);
        gridview.setExpanded(true);

        gridview.setAdapter(gAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                intent.putExtra(HomeActivity.BEAN_EXTRA, beanclassArrayList.get(position));
                startActivity(intent);
            }
        });
        return view;

    }
}