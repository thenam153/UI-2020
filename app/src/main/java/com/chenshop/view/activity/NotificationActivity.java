package com.chenshop.view.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chenshop.R;

public class NotificationActivity extends AppCompatActivity {

    LinearLayout linear1, linear2, linear3, linear4;
    TextView discount, activity, txtNoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // Add back button
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Drawable newdrawable = getResources().getDrawable(R.drawable.backarrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(newdrawable);

        linear1 = (LinearLayout) findViewById(R.id.linear1);
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        linear3 = (LinearLayout) findViewById(R.id.linear3);
        linear4 = (LinearLayout) findViewById(R.id.linear4);
        discount = (TextView) findViewById(R.id.discount);
        activity = (TextView) findViewById(R.id.activity);

        txtNoti = findViewById(R.id.txt_noti);
        String noti = getIntent().getStringExtra(ChooseAccountingActivity.SUCCESS);
        txtNoti.setText(noti);

        linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear2.setVisibility(View.VISIBLE);
                linear1.setVisibility(View.GONE);
                discount.setVisibility(View.VISIBLE);
            }
        });

        linear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linear2.setVisibility(View.GONE);
                linear1.setVisibility(View.VISIBLE);
                discount.setVisibility(View.GONE);
            }
        });
        linear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear4.setVisibility(View.VISIBLE);
                linear3.setVisibility(View.GONE);
                activity.setVisibility(View.VISIBLE);
            }
        });

        linear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linear4.setVisibility(View.GONE);
                linear3.setVisibility(View.VISIBLE);
                activity.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // end the activity
        if (id == android.R.id.home) {
            //this.finish();
            startActivity(new Intent(NotificationActivity.this, HomeActivity.class));
        } else {
            if (isTaskRoot()) {
                startActivity(new Intent(NotificationActivity.this, HomeActivity.class));
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
