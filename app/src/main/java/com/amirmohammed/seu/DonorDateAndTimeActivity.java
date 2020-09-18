package com.amirmohammed.seu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DonorDateAndTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_date_and_time);
    }

    public void submitDateAndTime(View view) {
        Toast.makeText(this, "تم التسجيل", Toast.LENGTH_SHORT).show();
        finish();
    }


}
