package com.example.zaber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextView loginTab, registerTab;
    TextInputEditText email, password;
    Button login_btn;

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

        login_btn.setOnClickListener(customer_menu);

        // Validation

        // Redirection customer or store
    }

    private View.OnClickListener customer_menu = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Do something
            Intent intent = new Intent(LoginActivity.this, CustomerActivity.class);
            startActivity(intent);
            finish();
        }
    };
}