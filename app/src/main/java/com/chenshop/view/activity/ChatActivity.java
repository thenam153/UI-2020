package com.chenshop.view.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chenshop.Handler.HttpDataHandler;
import com.chenshop.R;
import com.chenshop.adapter.CustomAdapter;
import com.chenshop.adapter.MessageAdapter;
import com.chenshop.model.bean.Beanclass;
import com.chenshop.model.bean.ChatModel;
import com.chenshop.model.bean.SimsimiModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private String channelID = "CHANNEL_ID_FROM_YOUR_SCALEDRONE_DASHBOARD";
    private String roomName = "observable-room";
    private EditText editText;
    private ImageView imgProduct;
    private TextView txtProductName, txtProductDetail, txtProductPrice;
    private ImageButton btn_send_message;

    private TextView btnBuyNow;
    List<ChatModel> list_chat = new ArrayList<>();
    private MessageAdapter messageAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        // Add back button
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Drawable newdrawable = getResources().getDrawable(R.drawable.backarrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(newdrawable);

        listView = findViewById(R.id.list_of_message);
        editText = findViewById(R.id.user_message);
        btn_send_message = findViewById(R.id.btn_send);

        imgProduct = findViewById(R.id.img_product);
        txtProductName = findViewById(R.id.txt_product_name);
        txtProductDetail = findViewById(R.id.txt_product_detail);
        txtProductPrice = findViewById(R.id.txt_product_price);

        btnBuyNow = findViewById(R.id.btn_buy_now);
        btn_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                ChatModel model = new ChatModel(text, true); // user send message
                list_chat.add(model);
                new SimsimiAPI().execute(list_chat);

                //remove user message
                editText.setText("");
            }
        });

        Beanclass product = (Beanclass) getIntent().getSerializableExtra(ProductDetailActivity.BEAN);
        disPlayInfoProduct(product);

        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBuyNowActivity(product);
            }
        });

    }

    private void openBuyNowActivity(Beanclass product) {
        Intent intent = new Intent(ChatActivity.this, PaymentActivity.class);
        intent.putExtra(ProductDetailActivity.BEAN, product);
        this.startActivityForResult(intent, 1);
    }

    private void disPlayInfoProduct(Beanclass product) {
        imgProduct.setImageResource(product.getImage1());
        txtProductName.setText(product.getTitle1());
        txtProductDetail.setText(product.getDiscription1());
        txtProductPrice.setText(product.getDate1());
    }

    private class SimsimiAPI extends AsyncTask<List<ChatModel>, Void, String> {
        String stream = null;
        List<ChatModel> models;
        String text = editText.getText().toString();

        @Override
        protected String doInBackground(List<ChatModel>... params) {
            String url = String.format("http://sandbox.api.simsimi.com/request.p?key=%s&lc=en&ft=1.0&text=%s", getString(R.string.simsimi_api), text);
            models = params[0];
            HttpDataHandler httpDataHandler = new HttpDataHandler();
            stream = httpDataHandler.GetHTTPData(url);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            SimsimiModel response = gson.fromJson(s, SimsimiModel.class);

            ChatModel chatModel = new ChatModel(response.getResponse(), false); // get response from simsimi
            models.add(chatModel);
            CustomAdapter adapter = new CustomAdapter(models, getApplicationContext());
            listView.setAdapter(adapter);
        }
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


