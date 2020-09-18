package com.amirmohammed.seu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CampaignsDateAndTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_date_and_time);
    }

    public void submitDateAndTime(View view) {
        startActivity(new Intent(CampaignsDateAndTimeActivity.this, CampaignsActivity.class));
        finish();
    }

}
