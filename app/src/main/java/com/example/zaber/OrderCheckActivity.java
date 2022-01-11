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
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderCheckActivity extends AppCompatActivity {

    Button order_confirm_btn;
    ListView lv;
    Bundle bundle;
    TextView money,times;
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
        ArrayList<String> moneyList=bundle.getStringArrayList("singleItemALL");

//        Log.d("bundle !!!!!!", bundle.getStringArrayList("merchandise").toString());

        ShoppingListAdapter adapter = new ShoppingListAdapter(this,itemList.toArray(new String[0]),moneyList.toArray(new String[0]));

        money=findViewById(R.id.money);
        money.setText(bundle.get("money").toString()+"元");
        times=findViewById(R.id.time);
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
        times.setText(currentTime.toString()+"~"+left.toString()+":"+right.toString());
        order_confirm_btn = findViewById(R.id.order_confirm_btn);
        lv = (ListView) findViewById(R.id.order_list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrderCheckActivity.this, OrderCheckActivity.class);
                ArrayList<String> items=bundle.getStringArrayList("merchandise");
                ArrayList<String> prices=bundle.getStringArrayList("singleItemALL");
                String money=bundle.get("money").toString();
                String current=prices.get(position);
                prices.remove(position);
                items.remove(position);
                bundle.putStringArrayList("merchandise",items);
                bundle.putStringArrayList("singleItemALL",prices);
                bundle.putString("money",Integer.toString(Integer.parseInt(money)-Integer.parseInt(current)));

                intent.putExtra("bundle",bundle);
                startActivity(intent);
                finish();
            }
        });
        order_confirm_btn.setOnClickListener(order_confirm);

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
            String emailName=CustomerInfo.getCustomerEmail();
            if(CustomerInfo.getCustomerEmail().indexOf(".")!=-1)
                emailName=CustomerInfo.getCustomerEmail().substring(0,CustomerInfo.getCustomerEmail().indexOf("."));

            // add to database
            root.child("users").child(emailName).setValue(CustomerInfo);

            Intent intent = new Intent(OrderCheckActivity.this, OrderStatusActivity.class);
            bundle.putString("money",CustomerInfo.getMoney().toString());
            bundle.putString("number",CustomerInfo.getNumber().toString());
            intent.putExtra("bundle",bundle);
            startActivity(intent);
//            finish();
        }
    };
}
