package com.example.zaber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextView loginTab, registerTab;
    TextInputEditText email, password;
    Button login_btn;
    Bundle bundle=new Bundle();
    CustomerInformation CustomerInfo=new CustomerInformation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get View Connection
        loginTab = findViewById(R.id.loginTab);
        registerTab = findViewById(R.id.registerTab);
        email = findViewById(R.id.login_mail);
        password = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_btn);


        // Redirection customer or store
        login_btn.setOnClickListener(view -> {
            String mail = Objects.requireNonNull(email.getText()).toString(), pw = Objects.requireNonNull(password.getText()).toString();
            System.out.println("Mail: " + mail);
            System.out.println("Password: " + pw);

            // After Onclick
            if (!mail.isEmpty() && !pw.isEmpty()) {
                Intent intent = null;

                // Switch Account
                if (mail.indexOf("!")==-1) {
                    System.out.println("Customer Account");
                    CustomerInfo.setCustomerEmail(email.getEditableText().toString());
                    CustomerInfo.setCustomerPassword(password.getEditableText().toString());
                    intent = new Intent(LoginActivity.this, CustomerActivity.class);
                    logins();
                    intent.putExtra("bundle",bundle);
                    startActivity(intent);
//                    finish();
                }
                else{
                    System.out.println("Store Account");
                    intent = new Intent(LoginActivity.this, StoreHomeActivity.class);
                    startActivity(intent);
//                    finish();
                }

            }
            else{
                Toast.makeText(LoginActivity.this, "Can place Empty!!!", Toast.LENGTH_SHORT).show();
            }

        });
    }
//    Log.i("customerEmail.........", dataSnapshot.child("customerEmail").getValue(String.class));
//    if(CustomerInfo.getCustomerEmail().equals(dataSnapshot.child("customerEmail").getValue(String.class)))
//        Toast.makeText(LoginActivity.this, "already exist", Toast.LENGTH_SHORT).show();
//    else
//        transitions();
//    private void addDatatoFirebase(){
//        String emailName=CustomerInfo.getCustomerEmail();
//        if(CustomerInfo.getCustomerEmail().indexOf(".")!=-1)
//            emailName=CustomerInfo.getCustomerEmail().substring(0,CustomerInfo.getCustomerEmail().indexOf("."));
//        System.out.println("Account verify : " + emailName);
//        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference targetRef=root.child("users").child(emailName);
//        targetRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                logins();
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w("TAG", "onCancelled", databaseError.toException());
//            }
//        });
//    }

    public void logins(){
//        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
//        root.child("users").child(CustomerInfo.getCustomerEmail()).setValue(CustomerInfo);
        bundle.putString("email",CustomerInfo.getCustomerEmail());
        bundle.putString("pwd",CustomerInfo.getCustomerPassword());
        bundle.putString("orderstatus",CustomerInfo.getorderStatus());
        bundle.putStringArrayList("merchandise",CustomerInfo.getMerchandise());
        bundle.putString("money",CustomerInfo.getMoney().toString());
        bundle.putStringArrayList("singleItemALL",new ArrayList<String>());
    }


}