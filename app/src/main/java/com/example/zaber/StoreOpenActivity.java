package com.example.zaber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class StoreOpenActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button store_online_close;
    ImageButton change_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_open);

        // Get Layout ID
        store_online_close = findViewById(R.id.store_online_close);
        change_page = findViewById(R.id.change_page);
        recyclerView = findViewById(R.id.order_recyclerview);

        String[] testOrder1 = {"餐點一", "餐點二", "餐點三"};
        String[] testOrder2 = {"餐點一", "餐點二"};
        String[] testOrder3 = {"餐點一", "餐點二", "餐點三", "餐點四"};
        ArrayList<String[]> testOrder = new ArrayList<>();
        testOrder.add(testOrder1);
        testOrder.add(testOrder2);
        testOrder.add(testOrder3);
        String user = "真滋味";

        StoreOrderAdapter storeOrderAdapter = new StoreOrderAdapter(this, testOrder, user);
        recyclerView.setAdapter(storeOrderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set Button Click
        store_online_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}