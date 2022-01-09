package com.example.zaber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderCheckActivity extends AppCompatActivity {

    Button order_confirm_btn;
    ListView lv;

    public static String[] progNames = {
            "Item1","Item2","Item3","Item4","Item5","Item1","Item2","Item3","Item4","Item5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_check);

        ShoppingListAdapter adapter = new ShoppingListAdapter(this,progNames);

        order_confirm_btn = findViewById(R.id.order_confirm_btn);
        lv = (ListView) findViewById(R.id.order_list);

        order_confirm_btn.setOnClickListener(order_confirm);

        lv.setAdapter(adapter);
    }

    private View.OnClickListener order_confirm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Do something
            Intent intent = new Intent(OrderCheckActivity.this, OrderStatusActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
