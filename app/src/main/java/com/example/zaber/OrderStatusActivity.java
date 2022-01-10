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
    TextView number,money;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_status);
        bundle=getIntent().getBundleExtra("bundle");
        call_store_btn = findViewById(R.id.call_store_btn);
        call_store_btn.setOnClickListener(call_store);
        number=findViewById(R.id.number);
        number.setText(bundle.get("number").toString());
        money=findViewById(R.id.money);
        money.setText("$ "+bundle.get("money").toString());

    }

    private View.OnClickListener call_store = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Do something

        }
    };
}
