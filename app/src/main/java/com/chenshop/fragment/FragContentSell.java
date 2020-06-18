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

public class FragContentSell extends Fragment {


    private ExpandableHeightGridView gridview;
    private ArrayList<Beanclass> beanclassArrayList;
    private GAdapter gAdapter;
    private View view;

    private int[] IMAGEgrid = {R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4, R.drawable.w5, R.drawable.w6};
    private String[] TITLeGgrid = {"Quần soóc bò", "Quần sooc vải", "Quần dài nâu", "Quần jean nữ", "Chân váy đen", "Chân váy sọc"};
    private String[] DIscriptiongrid = {"Min 2% off", "Min 3% off", "Min 4% off", "Min 6% off", "Min 7% off", "Min 7% off"};
    private String[] Dategrid = {"499,000đ", "349,000đ", "499,000đ", "599,000đ", "299,000đ", "299,000đ"};


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