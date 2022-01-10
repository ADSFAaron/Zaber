package com.example.zaber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class StoreItemActivity extends AppCompatActivity {
    Button shopping_cart_btn;

    ListView lv;
    Context context;
    Bundle bundle;
    TextView store_name;
    //ArrayList proglist;

    public Integer[] progImages = {
            R.drawable.store_image, R.drawable.store_image, R.drawable.store_image, R.drawable.store_image, R.drawable.store_image
    };

    public String[] progNames = {
            "雞排拉麵", "燒烤雞排飯", "燒烤雞腿飯", "香酥雞排飯", "香酥雞腿飯"
    };

    public String[] progmoney = {
            "60", "70", "70", "70", "70"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_store);
        bundle=getIntent().getBundleExtra("bundle");
        setCardInfo();
        StoreItemListAdapter adapter = new StoreItemListAdapter(this,progNames,progImages,progmoney);

        store_name=findViewById(R.id.store_name);
        store_name.setText(bundle.get("orderstatus").toString());

        shopping_cart_btn = findViewById(R.id.shopping_cart_btn);
        lv = (ListView) findViewById(R.id.item_list);

        lv.setAdapter(adapter);
        shopping_cart_btn.setOnClickListener(shopping_cart);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                ArrayList<String> req=bundle.getStringArrayList("singleItemALL");
                req.add(progmoney[index]);
                //Do something
                Intent intent = new Intent(StoreItemActivity.this, OrderActivity.class);
                bundle.putString("item",progNames[index]);
                bundle.putString("singleMoney",progmoney[index]);
                bundle.putStringArrayList("singleItemALL",req);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
                finish();
            }
        });
    }

    public void setCardInfo(){
        String stores=bundle.get("orderstatus").toString();
        if(stores.equals("陽光麵食")){
            progNames= new String[]{"滷肉飯", "牛肉燴飯", "羊肉炒飯", "擔仔麵", "酸辣意麵"};
            progmoney= new String[]{"45", "65", "65", "55", "50"};
        }
        else if(stores.equals("冰窖水果部")){
            progNames= new String[]{"紅茶", "檸檬愛玉", "抹茶奶茶", "烏龍奶茶", "青蛙下蛋"};
            progmoney= new String[]{"20", "25", "40", "35", "25"};
        }
        else if(stores.equals("山口壽司")){

        }
        else if(stores.equals("八方雲集")){

        }
    }

    private View.OnClickListener shopping_cart = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Do something
            Intent intent = new Intent(StoreItemActivity.this, OrderCheckActivity.class);
            intent.putExtra("bundle",bundle);
            startActivity(intent);
            finish();
        }
    };
}
