package com.example.zaber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

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


        // Redirection customer or store
        login_btn.setOnClickListener(view -> {
            String mail = Objects.requireNonNull(email.getText()).toString(), pw = Objects.requireNonNull(password.getText()).toString();
            System.out.println("Mail: " + mail);
            System.out.println("Password: " + pw);

            // After Onclick
            if (!mail.isEmpty() && !pw.isEmpty()) {
                Intent intent = null;

                // Switch Account
                if (mail.equals("customer@a.com")) {
                    System.out.println("Customer Account");
                    intent = new Intent(LoginActivity.this, CustomerActivity.class);
                } else if (mail.equals("store@a.com")) {
                    System.out.println("Store Account");
                    intent = new Intent(LoginActivity.this, StoreHomeActivity.class);
                } else {
                    System.out.println("Error Account : " + mail);
                }

                startActivity(intent);
                finish();
            }

        });

    }
}