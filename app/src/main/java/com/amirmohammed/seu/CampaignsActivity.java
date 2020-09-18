package com.amirmohammed.seu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CampaignsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaigns);
    }

    public void addAttachment(View view) {
        Toast.makeText(this, "قريباً", Toast.LENGTH_SHORT).show();
    }

    public void register(View view) {
        Toast.makeText(this, "تم التسجيل", Toast.LENGTH_SHORT).show();
        finish();
    }

}
