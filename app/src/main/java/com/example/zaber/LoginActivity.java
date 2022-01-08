package com.example.zaber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextView loginTab, registerTab;
    TextInputEditText email, password;
    AppCompatButton loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get View Connection
        loginTab = findViewById(R.id.loginTab);
        registerTab = findViewById(R.id.registerTab);
        email = findViewById(R.id.login_mail);
        password = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_button);

        String mail = email.getText().toString(), pw = password.getText().toString();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // After Onclick

                // Develop use
                Intent intent = new Intent(LoginActivity.this, StoreHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Validation

        // Redirection customer or store
    }
}