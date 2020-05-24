package com.chenshop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.chenshop.R;
import com.chenshop.adapter.GridviewAdapter;
import com.chenshop.layout.ExpandableHeightGridView;
import com.chenshop.model.bean.Beanclass;
import com.chenshop.view.activity.HomeActivity;
import com.chenshop.view.activity.ProductDetailActivity;

import java.util.ArrayList;

public class TabFragment1 extends Fragment {


    private ExpandableHeightGridView gridview;
    private ArrayList<Beanclass> beanclassArrayList;
    private GridviewAdapter gridviewAdapter;
    private View view;

    private int[] IMAGEgrid = {R.drawable.pik1, R.drawable.pik2, R.drawable.pik3, R.drawable.pik4, R.drawable.pik5, R.drawable.pik6};
    private String[] TITLeGgrid = {"Áo phông unisex(trắng)", "Áo phông unisex(đen)", "Váy liền nữ(hồng)", "Áo phông nữ(hồng)", "Áo phông nữ(hồng)", "Áo sơ mi nữ"};
    private String[] DIscriptiongrid = {"Min 5% off", "Min 5% off", "Min 3% off", "Min 4% off", "Min 5% off", "Min 5% off"};
    private String[] Dategrid = {"249,000đ", "249,000đ", "549,000đ", "245,000đ", "245,000đ", "299,000đ"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmenttab1, container, false);

        gridview = (ExpandableHeightGridView) view.findViewById(R.id.gridview);
        beanclassArrayList = new ArrayList<Beanclass>();

        for (int i = 0; i < IMAGEgrid.length; i++) {

            Beanclass beanclass = new Beanclass(IMAGEgrid[i], TITLeGgrid[i], DIscriptiongrid[i], Dategrid[i]);
            beanclassArrayList.add(beanclass);

        }
        gridviewAdapter = new GridviewAdapter(getActivity(), beanclassArrayList);
        gridview.setExpanded(true);

        gridview.setAdapter(gridviewAdapter);

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