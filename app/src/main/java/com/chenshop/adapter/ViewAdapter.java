package com.chenshop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.chenshop.fragment.FragContent;
import com.chenshop.fragment.FragContentSell;
import com.chenshop.fragment.FragContentShopCart;

public class ViewAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ViewAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragContent tab1 = new FragContent();
                return tab1;
            case 1:
                FragContentSell tab2 = new FragContentSell();
                return tab2;
            case 2:
                FragContentShopCart tab3 = new FragContentShopCart();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}