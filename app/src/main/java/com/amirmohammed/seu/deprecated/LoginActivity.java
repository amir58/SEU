package com.amirmohammed.seu.deprecated;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.amirmohammed.seu.R;
import com.amirmohammed.seu.SignUpActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        startActivity(new Intent(this, LoginCodeActivity.class));
        finish();
    }

    public void signUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
        finish();
    }

}
