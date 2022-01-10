package com.example.zaber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {
    ListView lv;
    Context context;
    //ArrayList proglist;
    TextView welcome;
    Bundle bundle;

    public static Integer[] progImages = {
            R.drawable.store_image, R.drawable.store_image, R.drawable.store_image, R.drawable.store_image, R.drawable.store_image
    };

    public static String[] progNames = {
            "仙桃總鋪", "陽光麵食", "冰窖水果部", "山口壽司", "八方雲集"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_menu);

        bundle=getIntent().getBundleExtra("bundle");
        welcome=(TextView)findViewById(R.id.welcome_msg);
        String emailName=bundle.get("email").toString();
        if(emailName.indexOf("@")!=-1)
            emailName=emailName.substring(0,emailName.indexOf("@"));
        welcome.setText("    歡迎回來, "+emailName);

        StoreListAdapter adapter = new StoreListAdapter(this,progNames,progImages);

        lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                //Do something
                Intent intent = new Intent(CustomerActivity.this, StoreItemActivity.class);
                updateBundle(progNames[index]);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
//                finish();
            }
        });
    }

    public void updateBundle(String store){
        bundle.putString("orderstatus",store);
        bundle.putStringArrayList("singleItemALL",new ArrayList<String>());
    }
}
