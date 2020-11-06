package com.example.seu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DonorTypeActivity extends AppCompatActivity {
    public static Activity screen;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_type);
        screen = this;

        intent = new Intent(DonorTypeActivity.this, DonorDateAndTimeActivity.class);
    }

    public void blood(View view) {
        MainActivity.donorData.setType("blood");
        startActivity(intent);
//        finish();
    }

    public void platelets(View view) {
//        intent.putExtra("type", "platelets");
        MainActivity.donorData.setType("platelets");
        startActivity(intent);
//        finish();
    }

}
