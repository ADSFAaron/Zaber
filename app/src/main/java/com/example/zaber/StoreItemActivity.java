package com.example.zaber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class StoreItemActivity extends AppCompatActivity{
    ListView lv;
    Context context;
    //ArrayList proglist;

    public static Integer[] progImages = {
            R.drawable.store_image,R.drawable.store_image,R.drawable.store_image,R.drawable.store_image,R.drawable.store_image
    };

    public static String[] progNames = {
            "StoreItem1","StoreItem2","StoreItem3","StoreItem4","StoreItem5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_store);

        StoreItemListAdapter adapter = new StoreItemListAdapter(this,progNames,progImages);

        lv = (ListView) findViewById(R.id.item_list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                //Do something
                Intent intent = new Intent(StoreItemActivity.this, OrderActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
