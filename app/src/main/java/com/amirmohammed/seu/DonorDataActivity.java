package com.amirmohammed.seu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DonorDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_data);
    }

    public void pushDonorData(View view) {
        startActivity(new Intent(DonorDataActivity.this, QuestionnaireActivity.class));
        finish();
    }
}
