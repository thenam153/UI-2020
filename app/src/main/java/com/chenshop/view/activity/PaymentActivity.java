package com.chenshop.view.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chenshop.R;
import com.chenshop.customfonts.MyTextView;
import com.chenshop.model.bean.Beanclass;
import com.chenshop.model.bean.PaymentAddress;

import java.text.NumberFormat;
import java.util.Locale;


public class PaymentActivity extends AppCompatActivity {

    private TextView txtAddressPayment, txtPaymentUserInfo, txtProductName, txtProductDetail, txtProductPrice, txtShipFee, txtTotalPrice, txtTotalShipFee, txtTotal;
    private EditText edtDiscount;
    private ImageView imgProduct, imgDeleteItem, imgEdit;
    private MyTextView btnNext;

    private PaymentAddress paymentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Add back button
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Drawable newdrawable = getResources().getDrawable(R.drawable.backarrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(newdrawable);

        txtAddressPayment = findViewById(R.id.txt_address_payment);
        txtPaymentUserInfo = findViewById(R.id.txt_username_payment);
        txtProductName = findViewById(R.id.txt_product_name);
        txtProductDetail = findViewById(R.id.txt_product_detail);
        txtProductPrice = findViewById(R.id.txt_product_price);
        txtShipFee = findViewById(R.id.txt_shipment_fee);
        txtTotalPrice = findViewById(R.id.txt_total_price);
        txtTotalShipFee = findViewById(R.id.txt_total_shipment);
        txtTotal = findViewById(R.id.txt_total);

        edtDiscount = findViewById(R.id.edt_discount);

        imgProduct = findViewById(R.id.img_product);


        // Get Data From Activity
        Beanclass product = (Beanclass) getIntent().getSerializableExtra(ProductDetailActivity.BEAN);
        disPlayInfoProduct(product);


        imgEdit = findViewById(R.id.img_edit);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterAddressActivity.class);
                intent.putExtra(ProductDetailActivity.BEAN, product);
                startActivityForResult(intent, 100);
            }
        });


        Intent intent = getIntent();
        paymentAddress = (PaymentAddress) intent.getSerializableExtra("Info");
        if (paymentAddress != null) {
            txtAddressPayment.setText(getPaymentAddress(paymentAddress));
            txtPaymentUserInfo.setText(getPaymentInfo(paymentAddress));
        }

        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtAddressPayment.getText() != "" || txtPaymentUserInfo.getText() != "") {
                    Intent intent = new Intent(v.getContext(), ChooseAccountingActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(v.getContext(), "Cần thêm địa chỉ giao hàng", Toast.LENGTH_LONG).show();
                }

            }
        });

        txtTotalPrice.setText(txtProductPrice.getText().toString());
        txtTotalShipFee.setText(txtShipFee.getText().toString());
        int shipFee = Integer.parseInt(txtShipFee.getText().toString().replaceAll("[-+.^:,đ]", ""));
        int productPrice = Integer.parseInt(txtProductPrice.getText().toString().replaceAll("[-+.^:,đ]", ""));
        int totalCost = shipFee + productPrice;

        txtTotal.setText(NumberFormat.getNumberInstance(Locale.US).format(totalCost) + "đ");
    }


    private void disPlayInfoProduct(Beanclass product) {
        imgProduct.setImageResource(product.getImage1());
        txtProductName.setText(product.getTitle1());
        txtProductDetail.setText(product.getDiscription1());
        txtProductPrice.setText(product.getDate1());
    }


    public String getPaymentInfo(PaymentAddress paymentAddress) {
        String result = "";
        if (!paymentAddress.getName().isEmpty()) {
            result += paymentAddress.getName() + " ";
        }
        if (!paymentAddress.getPhone().isEmpty()) {
            result += paymentAddress.getPhone();
        }
        return result;
    }

    public String getPaymentAddress(PaymentAddress paymentAddress) {
        String result = "";
        if (!paymentAddress.getDetailAddress().isEmpty()) {
            result += paymentAddress.getDetailAddress() + ", ";
        }
        if (!paymentAddress.getWard().isEmpty()) {
            result += paymentAddress.getWard() + ", ";
        }
        if (!paymentAddress.getDistrict().isEmpty()) {
            result += paymentAddress.getDistrict() + ", ";
        }
        if (!paymentAddress.getCity().isEmpty()) {
            result += paymentAddress.getCity();
        }

        return result;
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
}
