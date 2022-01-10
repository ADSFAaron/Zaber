package com.example.zaber;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

public class OrderCheckActivity extends AppCompatActivity {

    Button order_confirm_btn;
    ListView lv;
    Bundle bundle;
    TextView money;
    CustomerInformation CustomerInfo=new CustomerInformation();

    public static String[] progNames = {
            "Item1","Item2","Item3","Item4","Item5","Item1","Item2","Item3","Item4","Item5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_check);
        bundle=getIntent().getBundleExtra("bundle");
        ArrayList<String> itemList=bundle.getStringArrayList("merchandise");
//        Log.d("bundle !!!!!!", bundle.getStringArrayList("merchandise").toString());

        ShoppingListAdapter adapter = new ShoppingListAdapter(this,itemList.toArray(new String[0]));

        money=findViewById(R.id.money);
        money.setText(bundle.get("money").toString());

        order_confirm_btn = findViewById(R.id.order_confirm_btn);
        lv = (ListView) findViewById(R.id.order_list);

        order_confirm_btn.setOnClickListener(order_confirm);

        lv.setAdapter(adapter);
    }

    private View.OnClickListener order_confirm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Random rand = new Random();
            //Do something
            DatabaseReference root = FirebaseDatabase.getInstance().getReference();
            CustomerInfo.setCustomerEmail(bundle.get("email").toString());
            CustomerInfo.setCustomerPassword(bundle.get("pwd").toString());
            CustomerInfo.setorderStatus(bundle.get("orderstatus").toString());
            CustomerInfo.setMerchandise(bundle.getStringArrayList("merchandise"));
            CustomerInfo.setMoney(Integer.parseInt(bundle.get("money").toString()));
            CustomerInfo.setNumber(rand.nextInt(101));
            root.child("users").child(CustomerInfo.getCustomerEmail()).setValue(CustomerInfo);

            Intent intent = new Intent(OrderCheckActivity.this, OrderStatusActivity.class);
            bundle.putString("money",CustomerInfo.getMoney().toString());
            bundle.putString("number",CustomerInfo.getNumber().toString());
            intent.putExtra("bundle",bundle);
            startActivity(intent);
            finish();
        }
    };
}
