package com.example.zaber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

public class OrderStatusActivity extends AppCompatActivity {
    Button call_store_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_status);

        call_store_btn = findViewById(R.id.call_store_btn);
        call_store_btn.setOnClickListener(call_store);
    }

    private View.OnClickListener call_store = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Do something

        }
    };
}
