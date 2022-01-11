package com.example.zaber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
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
        _order = new ArrayList<>();

        StoreOrderAdapter storeOrderAdapter = new StoreOrderAdapter(this, testOrder, user);
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
            _order.add(new Order(no, "user", tmp, tmp, 100, currentLocalTime, OrderStatus.New));
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
}