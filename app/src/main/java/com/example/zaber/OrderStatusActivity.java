package com.example.zaber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderStatusActivity extends AppCompatActivity {
    Button call_store_btn;
    TextView number,money;
    Bundle bundle;
    TextView times;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_status);
        bundle=getIntent().getBundleExtra("bundle");
        times=findViewById(R.id.time);
        call_store_btn = findViewById(R.id.call_store_btn);
        call_store_btn.setOnClickListener(call_store);
        number=findViewById(R.id.number);
        number.setText(bundle.get("number").toString());
        money=findViewById(R.id.money);
        money.setText("$ "+bundle.get("money").toString());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String currentTime=formatter.format(date).toString();
        Integer left=Integer.parseInt(currentTime.substring(0,currentTime.indexOf(":")));
        Integer right=Integer.parseInt(currentTime.substring(currentTime.indexOf(":")+1));
        right+=15;
        if(right/60==1){
            left+=1;
            right%=60;
        }
        if(left/24==1)
            left%=24;
        String left_s=left.toString();
        if(left<10)
            left_s="0"+left_s;
        String right_s=right.toString();
        if(right<10)
            right_s="0"+right_s;
        times.setText(currentTime.toString()+"~"+left_s+":"+right_s);

    }

    private View.OnClickListener call_store = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Do something
            Intent intent = null;
            intent = new Intent(OrderStatusActivity.this, CustomerActivity.class);
            bundle.putString("money","0");
            bundle.putStringArrayList("merchandise", new ArrayList<String>());
            intent.putExtra("bundle",bundle);
            startActivity(intent);
            finish();
        }
    };
}
