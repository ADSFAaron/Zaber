package com.example.zaber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class HistoryOrderActivity extends AppCompatActivity {

    RecyclerView historyRecyclerView;
    Button store_online_close;
    ImageButton change_page;

    ArrayList<Order> orders;
    HistoryOrderAdapter historyOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);

        // Get Layout ID
        store_online_close = findViewById(R.id.store_online_close);
        change_page = findViewById(R.id.change_page);
        historyRecyclerView = findViewById(R.id.order_history_recyclerview);

        orders = new ArrayList<>();

        store_online_close.setOnClickListener(view -> finish());


        // Get user's order
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("store");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Log.v("DATABASE",ds.getValue().toString());
                    Order customer_data;
                    customer_data = ds.getValue(Order.class);
                    orders.add(customer_data);
                }
                historyOrderAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
            }
        };
        root.addValueEventListener(postListener);


        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
        Date currentLocalTime = cal.getTime();
        String[] tmp1 = {"餐點一", "餐點二"};
        String[] tmp2 = {"無", "無"};
        /*
        orders.add(new Order(1, "我是誰", tmp1, tmp2, 200,
                currentLocalTime, OrderStatus.Finish));
        orders.add(new Order(2, "測試人員", new String[]{"餐點一", "餐點二", "餐點三"}, new String[]{"", "", ""}, 300,
                currentLocalTime, OrderStatus.Decline));*/

        System.out.println(orders);

        historyOrderAdapter = new HistoryOrderAdapter(this, orders);
        historyRecyclerView.setAdapter(historyOrderAdapter);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}