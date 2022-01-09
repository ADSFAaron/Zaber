package com.example.zaber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class OrderActivity extends AppCompatActivity{

    Button order_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_option);

        order_btn = findViewById(R.id.shopping_cart_btn);

        order_btn.setOnClickListener(order_this);
    }

    private View.OnClickListener order_this = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Do something
            Intent intent = new Intent(OrderActivity.this, StoreItemActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
