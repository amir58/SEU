package com.amirmohammed.seu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_code);
    }

    public void login(View view) {
        startActivity(new Intent(LoginCodeActivity.this, MainActivity.class));
    }
}
