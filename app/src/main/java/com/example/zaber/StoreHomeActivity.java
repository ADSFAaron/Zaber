package com.example.zaber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class StoreHomeActivity extends AppCompatActivity {

    TextView store_home_title, open_time;
    ShapeableImageView home_avatar;
    AppCompatButton store_home_takedayoff, store_home_edit, store_home_open;
    TextView order_history_all, store_home_voice_hint;
    ImageButton store_home_history_btn, store_home_voice_hint_btn;
    String storeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_home);

        // Get Layout ID
        store_home_title = findViewById(R.id.store_home_title);
        open_time = findViewById(R.id.open_time);
        home_avatar = findViewById(R.id.home_avatar);
        store_home_takedayoff = findViewById(R.id.store_home_takedayoff);
        store_home_edit = findViewById(R.id.store_home_edit);
        store_home_open = findViewById(R.id.store_home_open);
        order_history_all = findViewById(R.id.order_history_all);
        store_home_voice_hint = findViewById(R.id.store_home_voice_hint);
        store_home_history_btn = findViewById(R.id.store_home_history_btn);
        store_home_voice_hint_btn = findViewById(R.id.store_home_voice_hint_btn);

        // Change Store Home Title
        storeName = "xxx";
        changeGreed(storeName);

        // Button Click
        store_home_open.setOnClickListener(view -> {
            Intent intent = new Intent(StoreHomeActivity.this, StoreOpenActivity.class);
            startActivity(intent);
        });

        store_home_history_btn.setOnClickListener(view -> {
            Intent intent = new Intent(StoreHomeActivity.this, HistoryOrderActivity.class);
            startActivity(intent);
        });

        store_home_voice_hint_btn.setOnClickListener(view -> {
            Intent intent = new Intent(StoreHomeActivity.this, AudioChooseActivity.class);
            startActivity(intent);
        });

    }

    /**
     * 改變商店主業的招呼語
     *
     * @param name 店家名
     */
    public void changeGreed(String name) {

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH");
        date.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String localTime = date.format(currentLocalTime);
        int currentHour = Integer.parseInt(localTime);
        System.out.println("Current Hour: " + localTime);

        String result;
        if (currentHour < 12) {
            result = "早安";
        } else if (currentHour < 18) {
            result = "午安";
        } else {
            result = "晚安";
        }

        result += (", " + name);
        store_home_title.setText(result);
    }
}