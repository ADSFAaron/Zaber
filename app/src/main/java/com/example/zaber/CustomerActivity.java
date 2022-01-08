package com.example.zaber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {
    ListView lv;
    Context context;
    //ArrayList proglist;

    public static Integer[] progImages = {
            R.drawable.store_image,R.drawable.store_image,R.drawable.store_image,R.drawable.store_image,R.drawable.store_image
    };

    public static String[] progNames = {
            "Store1","Store2","Store3","Store4","Store5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_menu);

        StoreListAdapter adapter = new StoreListAdapter(this,progNames,progImages);

        lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(adapter);
    }
}
