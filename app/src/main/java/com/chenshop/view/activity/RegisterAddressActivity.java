package com.chenshop.view.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.chenshop.R;
import com.chenshop.model.bean.Beanclass;
import com.chenshop.model.bean.PaymentAddress;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class RegisterAddressActivity extends AppCompatActivity {
    public static final int ADDRESS =1;

    String[] CITYLIST = {"Hà Nội", "TP Hồ Chí Minh", "Đà Nẵng", "Hải Phòng"};
    String[] DISTRICTLIST = {"Cầu Giấy", "Hoàn Kiếm", "Ba Đình", "Quận 1", "Quận 7", "Phú Nhuận", "Cẩm Lệ", "Hải Châu"};
    String[] WARDLIST = {"Mai Dịch", "Dịch Vọng", "Nghĩa Đô", "Yên Hòa", "Trung Hòa", "Quan Hoa"};
    private EditText edtName, edtPhone, edtAddress;
    private Spinner spinCity, spinDistrict, spinWard;
    private Button btnNext;

    private PaymentAddress paymentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_address);

        // Add back button
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Drawable newdrawable= getResources().getDrawable(R.drawable.backarrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(newdrawable);

        edtName = findViewById(R.id.edt_name);
        edtPhone = findViewById(R.id.edt_phone);
        edtAddress = findViewById(R.id.edt_address);



        ArrayAdapter<String> arrayAdapterCity = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, CITYLIST);
        MaterialBetterSpinner materialDesignSpinner1 = (MaterialBetterSpinner)
                findViewById(R.id.spin_city);
        materialDesignSpinner1.setAdapter(arrayAdapterCity);

        ArrayAdapter<String> arrayAdapterDistrict = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DISTRICTLIST);
        MaterialBetterSpinner materialDesignSpinner2 = (MaterialBetterSpinner)
                findViewById(R.id.spin_district);
        materialDesignSpinner2.setAdapter(arrayAdapterDistrict);

        ArrayAdapter<String> arrayAdapterWard = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, WARDLIST);
        MaterialBetterSpinner materialDesignSpinner3 = (MaterialBetterSpinner)
                findViewById(R.id.spin_ward);
        materialDesignSpinner3.setAdapter(arrayAdapterWard);



        Beanclass product = (Beanclass) getIntent().getSerializableExtra(ProductDetailActivity.BEAN);

        btnNext = findViewById(R.id.btnAddAddress);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentAddress = new PaymentAddress(
                        edtName.getText().toString(),
                        edtPhone.getText().toString(),
                        edtAddress.getText().toString(),
                        materialDesignSpinner1.getText().toString(),
                        materialDesignSpinner2.getText().toString(),
                        materialDesignSpinner3.getText().toString()
                );
                Intent intent = new Intent(v.getContext(), PaymentActivity.class);
                intent.putExtra("Info", paymentAddress);
                intent.putExtra(ProductDetailActivity.BEAN, product);
                startActivityForResult(intent, ADDRESS);
            }
        });

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
