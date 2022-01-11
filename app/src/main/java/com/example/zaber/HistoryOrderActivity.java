package com.example.zaber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class HistoryOrderActivity extends AppCompatActivity {

    RecyclerView historyRecyclerView;
    Button store_online_close;
    ImageButton change_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);

        // Get Layout ID
        store_online_close = findViewById(R.id.store_online_close);
        change_page = findViewById(R.id.change_page);
        historyRecyclerView = findViewById(R.id.order_history_recyclerview);

        store_online_close.setOnClickListener(view -> finish());

        ArrayList<Order> orders = new ArrayList<>();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
        Date currentLocalTime = cal.getTime();
        String[] tmp1 = {"餐點一", "餐點二"};
        String[] tmp2 = {"無", "無"};
        orders.add(new Order(1, "我是誰", tmp1, tmp2, 200,
                currentLocalTime, OrderStatus.Finish));
        orders.add(new Order(2, "測試人員", new String[]{"餐點一", "餐點二", "餐點三"}, new String[]{"", "", ""}, 300,
                currentLocalTime, OrderStatus.Decline));

        System.out.println(orders);

        HistoryOrderAdapter historyOrderAdapter = new HistoryOrderAdapter(this, orders);
        historyRecyclerView.setAdapter(historyOrderAdapter);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}