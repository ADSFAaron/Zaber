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

public class StoreItemActivity extends AppCompatActivity {
    Button shopping_cart_btn;

    ListView lv;
    Context context;
    Bundle bundle;
    TextView store_name;
    //ArrayList proglist;

    public static Integer[] progImages = {
            R.drawable.store_image, R.drawable.store_image, R.drawable.store_image, R.drawable.store_image, R.drawable.store_image
    };

    public static String[] progNames = {
            "StoreItem1", "StoreItem2", "StoreItem3", "StoreItem4", "StoreItem5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_store);

        bundle=getIntent().getBundleExtra("bundle");
        Log.d("SSSSSSSSSSSS::::", getIntent().getBundleExtra("bundle").getString("orderstatus"));
        StoreItemListAdapter adapter = new StoreItemListAdapter(this,progNames,progImages);


        store_name=findViewById(R.id.store_name);
        store_name.setText(bundle.get("orderstatus").toString());

        shopping_cart_btn = findViewById(R.id.shopping_cart_btn);
        lv = (ListView) findViewById(R.id.item_list);

        lv.setAdapter(adapter);
        shopping_cart_btn.setOnClickListener(shopping_cart);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                //Do something
                Intent intent = new Intent(StoreItemActivity.this, OrderActivity.class);
                bundle.putString("item",progNames[index]);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
                finish();
            }
        });
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
