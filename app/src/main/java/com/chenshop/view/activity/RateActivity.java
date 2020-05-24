package com.chenshop.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.chenshop.R;
import com.chenshop.adapter.CommentAdapter;
import com.chenshop.model.bean.Feedback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RateActivity extends AppCompatActivity {

    private ArrayList<Feedback> lstComment;
    private RatingBar ratingBar;
    private TextView txtRatePoint;

    private ImageButton btnSend;
    private EditText userMess;
    private float ratePoint;
    private CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        /// Add back button
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Drawable newDrawable = getResources().getDrawable(R.drawable.backarrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(newDrawable);


        ratingBar = findViewById(R.id.rb_rate);
        txtRatePoint = findViewById(R.id.txt_rate_point);
        userMess = findViewById(R.id.user_message);
        btnSend = findViewById(R.id.btn_send);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                txtRatePoint.setText(String.valueOf(v));
                ratePoint = ratingBar.getRating();
                if (ratePoint == 0) {
                    txtRatePoint.setText("0/5");
                } else if (ratePoint == 0.5) {
                    txtRatePoint.setText("0.5/5");
                } else if (ratePoint == 1) {
                    txtRatePoint.setText("1/5");
                } else if (ratePoint == 1.5) {
                    txtRatePoint.setText("1.5/5");
                } else if (ratePoint == 2) {
                    txtRatePoint.setText("2/5");
                } else if (ratePoint == 2.5) {
                    txtRatePoint.setText("2.5/5");
                } else if (ratePoint == 3) {
                    txtRatePoint.setText("3/5");
                } else if (ratePoint == 3.5) {
                    txtRatePoint.setText("3.5/5");
                } else if (ratePoint == 4) {
                    txtRatePoint.setText("4/5");
                } else if (ratePoint == 4.5) {
                    txtRatePoint.setText("4.5/5");
                } else if (ratePoint == 5) {
                    txtRatePoint.setText("5/5");
                }

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstComment = new ArrayList<>();
                Feedback feedback = new Feedback("Đỗ Văn Nam", userMess.getText().toString(), ratePoint, getCurrentTime());
                lstComment.add(feedback);


                //display list of product
                final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                commentAdapter = new CommentAdapter(RateActivity.this, lstComment);
                recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
                recyclerView.setAdapter(commentAdapter);

                ratingBar.setRating(0);
                userMess.setText("");
                userMess.setVisibility(View.GONE);
                btnSend.setVisibility(View.GONE);


            }
        });

    }

    public String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
    }
    /*private List<Feedback> getListData() {
        return Database.FEEDBACK.list();
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // end the activity
        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


}

