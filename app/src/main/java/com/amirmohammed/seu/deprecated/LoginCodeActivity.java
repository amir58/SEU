package com.amirmohammed.seu.deprecated;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.amirmohammed.seu.MainActivity;
import com.amirmohammed.seu.R;

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
