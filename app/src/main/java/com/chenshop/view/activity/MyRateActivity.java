package com.chenshop.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.chenshop.R;
import com.chenshop.adapter.RateRecycleViewAdapter;
import com.chenshop.model.bean.Beanclass;

import java.util.ArrayList;

public class MyRateActivity extends AppCompatActivity {

    private ArrayList<Beanclass> beanclassArrayList;
    private RateRecycleViewAdapter recyclerViewAdapter;

    private ArrayList<Integer> IMAGEgrid = new ArrayList<Integer>();
    private ArrayList<String> TITLeGgrid = new ArrayList<String>();
    private ArrayList<String>  DIscriptiongrid = new ArrayList<String>();
    private ArrayList<String> Dategrid = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rate);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        Drawable newdrawable = getResources().getDrawable(R.drawable.backarrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(newdrawable);

        beanclassArrayList = new ArrayList<Beanclass>();
        IMAGEgrid.add(R.drawable.grooming7);
        IMAGEgrid.add(R.drawable.grooming3);
        TITLeGgrid.add("Túi du lịch");
        TITLeGgrid.add("Dao cạo râu gỗ");
        DIscriptiongrid.add("Min 30% off");
        DIscriptiongrid.add( "Min 70% off");
        Dategrid.add("200,000đ");
        Dategrid.add("200,000đ");

        getdataIntent();

        for (int i = 0; i < IMAGEgrid.size(); i++) {

            Beanclass beanclass = new Beanclass(IMAGEgrid.get(i), TITLeGgrid.get(i), DIscriptiongrid.get(i), Dategrid.get(i));
            beanclassArrayList.add(beanclass);
        }

        //display list of product
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewAdapter = new RateRecycleViewAdapter(MyRateActivity.this, beanclassArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // end the activity
        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void getdataIntent() {
        Beanclass data = (Beanclass) getIntent().getSerializableExtra("data");
        if(data == null) {
            return;
        }
        else {
            IMAGEgrid.add(0,data.getImage1());
            TITLeGgrid.add(0,data.getTitle1());
            DIscriptiongrid.add(0,data.getDiscription1());
            Dategrid.add(0,data.getDate1());
        }
    }
}
