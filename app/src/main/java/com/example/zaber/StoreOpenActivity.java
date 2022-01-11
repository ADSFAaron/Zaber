package com.example.zaber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class StoreOpenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button store_online_close;
    private ImageButton change_page;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;
    private AppCompatButton popupAccept, popupDecline;
    private TextView orderNo, orderState, orderItems;
    private ArrayList<Order> _order;        // 有需要再切換ㄅ

    private ArrayList<CustomerInformation> customerInformList;
    private ArrayList<String[]> orderList;
    private ArrayList<Order> order;
    private StoreOrderAdapter storeOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_open);

        // Get Layout ID
        store_online_close = findViewById(R.id.store_online_close);
        change_page = findViewById(R.id.change_page);
        recyclerView = findViewById(R.id.order_recyclerview);

        // Create Oder and Customer List
        orderList = new ArrayList<>();
        order = new ArrayList<>();
        customerInformList = new ArrayList<>();

        // Get user's order
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("users");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                customerInformList.clear();
                orderList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    //Log.v("DATABASE",ds.getValue().toString());
                    CustomerInformation customer_data =ds.getValue(CustomerInformation.class);
                    if(customer_data.getorderStatus().equals("仙桃總鋪"))
                        customerInformList.add(customer_data);
                }
                createOrderList();
                storeOrderAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
            }
        };
        root.addValueEventListener(postListener);
/*
        String[] testOrder1 = {"餐點一", "餐點二", "餐點三"};
        String[] testOrder2 = {"餐點一", "餐點二"};
        String[] testOrder3 = {"餐點一", "餐點二", "餐點三", "餐點四"};
        orderList.add(testOrder1);
        orderList.add(testOrder2);
        orderList.add(testOrder3);*/

        String user = "真滋味";
        _order = new ArrayList<>();

        storeOrderAdapter = new StoreOrderAdapter(this, orderList, user,order);
        recyclerView.setAdapter(storeOrderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set Button Click
        store_online_close.setOnClickListener(view -> createNewOrderDialog());
    }

    public void createNewOrderDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.new_order_row, null);

        // Get POP up Windows ID
        orderNo = popupView.findViewById(R.id.order_no);
        orderState = popupView.findViewById(R.id.order_status);
        orderItems = popupView.findViewById(R.id.order_item);
        popupAccept = popupView.findViewById(R.id.order_accept);
        popupDecline = popupView.findViewById(R.id.order_decline);

        int no = 123;
        String showNo = "No. " + no;
        String[] tmp = {"起司玉米蛋餅", "鬼椒牛肉起司堡"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0, orderItemLength = tmp.length; i < orderItemLength; i++) {
            String s = tmp[i];
            sb.append(i + 1).append(". ").append(s).append("\n");
        }
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
        Date currentLocalTime = cal.getTime();

        // Set dialog content
        orderNo.setText(showNo);
        orderState.setText(currentStatus2String(OrderStatus.New));
        orderItems.setText(sb.toString());

        dialogBuilder.setView(popupView);
        alertDialog = dialogBuilder.create();
        alertDialog.show();

        popupAccept.setOnClickListener(view -> {
            // 按下 Accept 後

            _order.add(new Order(no, "user", new ArrayList<String>(Arrays.asList(tmp)), new ArrayList<String>(Arrays.asList(tmp)), 100, currentLocalTime, OrderStatus.New));
            alertDialog.dismiss();
        });

        popupDecline.setOnClickListener(view -> {
            alertDialog.dismiss();
        });
    }

    public String currentStatus2String(OrderStatus status) {
        String result;
        switch (status) {
            case New:
                result = "新訂單";
                break;
            case Finish:
                result = "已完成";
                break;
            case Decline:
                result = "已拒絕";
                break;
            case Preparing:
                result = "製作中";
                break;
            default:
                result = "無狀態";
                break;
        }

        return result;
    }

    public void createOrderList(){
        for(CustomerInformation ci : customerInformList){
            String[] mStringArray = new String[ci.getMerchandise().size()];
            mStringArray = ci.getMerchandise().toArray(mStringArray);
            orderList.add(mStringArray);
            OrderStatus test = OrderStatus.New;
            Calendar c = Calendar.getInstance();
            Order newOD = new Order(ci.getNumber(),ci.getCustomerEmail(),ci.getMerchandise(),ci.getMerchandise(),ci.getMoney(),c.getTime(),test);

            order.add(newOD);
        }
    }

}