package com.chenshop.view.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chenshop.R;
import com.chenshop.layout.ChildAnimationExample;
import com.chenshop.layout.SliderLayout;
import com.chenshop.model.bean.Beanclass;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.io.Serializable;
import java.util.HashMap;

public class ProductDetailActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, Serializable {
    public static final String BEAN = "Bean";
    SliderLayout mDemoSlider;

    TextView buy, est, itemname, price, buynow, addtocart;

    ImageView btnBack, imgChat;

    //    Typeface fonts1,fonts2;
    LinearLayout linear1, linear2, linear3, linear4, linear5, linear6;

    TextView discription3, discription2, discription1;
    RatingBar mRatingBar;
    Beanclass dataproduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Beanclass product = (Beanclass) getIntent().getSerializableExtra(HomeActivity.BEAN_EXTRA);
        Drawable newdrawable = getResources().getDrawable(R.drawable.backarrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(newdrawable);

//                ***********discription**********
        buy = (TextView) findViewById(R.id.buy);
        est = findViewById(R.id.est);
        itemname = findViewById(R.id.itemname);
        price = findViewById(R.id.price);
        mRatingBar = (RatingBar) findViewById(R.id.rating) ;
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        discription1 = (TextView) findViewById(R.id.discription1);
        buynow = (TextView) findViewById(R.id.buynow);
        addtocart = (TextView) findViewById(R.id.buy);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });

        dataproduct = product;
        /*btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        mRatingBar.setRating(4);
        mRatingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(ProductDetailActivity.this,MyRateActivity.class);
                intent.putExtra("data",dataproduct);
                startActivity(intent);
                return false;
            }

        });

        imgChat = findViewById(R.id.img_chat);
        imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, ChatActivity.class);
                intent.putExtra(ProductDetailActivity.BEAN, product);
                startActivityForResult(intent, 100);
            }
        });

        setProductDetails(product);

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBuyNowActivity(product);
            }
        });

        linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear2.setVisibility(View.VISIBLE);
                linear1.setVisibility(View.GONE);
                discription1.setVisibility(View.VISIBLE);
            }
        });

        linear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linear2.setVisibility(View.GONE);
                linear1.setVisibility(View.VISIBLE);
                discription1.setVisibility(View.GONE);
            }
        });


//                ***********use and care**********

        linear3 = (LinearLayout) findViewById(R.id.linear3);
        linear4 = (LinearLayout) findViewById(R.id.linear4);
        discription2 = (TextView) findViewById(R.id.discription2);


        linear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                linear4.setVisibility(View.VISIBLE);
                linear3.setVisibility(View.GONE);
                discription2.setVisibility(View.VISIBLE);

            }
        });

        linear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                linear4.setVisibility(View.GONE);
                linear3.setVisibility(View.VISIBLE);
                discription2.setVisibility(View.GONE);


            }
        });


//                ***********design**********

        linear5 = (LinearLayout) findViewById(R.id.linear5);
        linear6 = (LinearLayout) findViewById(R.id.linear6);
        discription3 = (TextView) findViewById(R.id.discription3);


        linear5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                linear6.setVisibility(View.VISIBLE);
                linear5.setVisibility(View.GONE);
                discription3.setVisibility(View.VISIBLE);

            }
        });

        linear6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                linear6.setVisibility(View.GONE);
                linear5.setVisibility(View.VISIBLE);
                discription3.setVisibility(View.GONE);


            }
        });


        //********Slider*********

        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        if (product != null) {
            file_maps.put("1", product.getImage1());
        } else {
            file_maps.put("1", R.drawable.rain6);
        }
        file_maps.put("2", R.drawable.rain4);
        file_maps.put("3", R.drawable.rain5);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    //  .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this);


            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new ChildAnimationExample());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);


    }

    private void openBuyNowActivity(Beanclass product) {

        Intent intent = new Intent(ProductDetailActivity.this, PaymentActivity.class);
        intent.putExtra(ProductDetailActivity.BEAN, product);
        this.startActivityForResult(intent, 1);
    }

    private void setProductDetails(Beanclass product) {
        if (product == null) {
            est.setText("Thông tin đang trống");
        } else {
            est.setText(product.getDiscription1());
            itemname.setText(product.getTitle1());
            price.setText(product.getDate1());
        }
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // end the activity
        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


}

