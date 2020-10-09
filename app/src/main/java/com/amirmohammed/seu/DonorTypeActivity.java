package com.amirmohammed.seu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DonorTypeActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_type);
        intent = new Intent(DonorTypeActivity.this, DonorDateAndTimeActivity.class);
    }

    public void blood(View view) {
//        intent.putExtra("type", "blood");
        MainActivity.donorData.setType("blood");
        startActivity(intent);
        finish();
    }

    public void platelets(View view) {
//        intent.putExtra("type", "platelets");
        MainActivity.donorData.setType("platelets");
        startActivity(intent);
        finish();
    }

}
