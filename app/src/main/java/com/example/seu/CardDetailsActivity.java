package com.example.seu;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CardDetailsActivity extends AppCompatActivity {
    TextView textViewDonorName, textViewDonorBloodType, textViewDonorCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        textViewDonorName = findViewById(R.id.card_details_donor_name);
        textViewDonorBloodType = findViewById(R.id.card_details_donor_blood_type);
        textViewDonorCount = findViewById(R.id.card_details_donor_count);

        DonorData donorData = (DonorData) getIntent().getSerializableExtra("donorData");

        int donorCount = getIntent().getIntExtra("donorCount", 0);

        if (donorData == null) {
            return;
        }

        textViewDonorName.append(donorData.getName());
        textViewDonorBloodType.append(donorData.getBloodType());

        textViewDonorCount.append("" + donorCount);

    }

    public void exit(View view) {
        finish();
    }

}


