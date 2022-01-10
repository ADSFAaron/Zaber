package com.example.zaber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity{

    Button order_btn;
    Bundle bundle;
    TextView item_name;
    String addmore="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_option);
        bundle=getIntent().getBundleExtra("bundle");
        order_btn = findViewById(R.id.shopping_cart_btn);
        item_name=findViewById(R.id.item_name);
        item_name.setText(bundle.get("item").toString());

        order_btn.setOnClickListener(order_this);
    }
    public void itemClicked(View v) {
        CheckBox checkBox=(CheckBox)v;
        if(checkBox.isChecked()){
            addmore=" 加飯";
        }
        else
            addmore="";
    }
    private View.OnClickListener order_this = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Do something
            Intent intent = new Intent(OrderActivity.this, StoreItemActivity.class);
            updateBundle(item_name.getText().toString()+addmore);
            intent.putExtra("bundle",bundle);
            startActivity(intent);
            finish();
        }
    };

    public void updateBundle(String item){
        ArrayList<String> req=bundle.getStringArrayList("merchandise");
        req.add(item);
        bundle.putStringArrayList("merchandise",req);
        bundle.putString("money",Integer.toString(Integer.parseInt(bundle.get("money").toString())+200));
    }
}
