package com.example.seu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PatientDetailsActivity extends AppCompatActivity {
    TextView textViewPatientName, textViewBloodType, textViewUnitType,
            textViewUnitCount, textViewDonorsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        textViewPatientName = findViewById(R.id.patient_details_tv_patient_name);
        textViewBloodType = findViewById(R.id.patient_details_tv_blood_type);
        textViewUnitType = findViewById(R.id.patient_details_tv_unit_type);
        textViewUnitCount = findViewById(R.id.patient_details_tv_unit_count);
        textViewDonorsCount = findViewById(R.id.patient_details_donors_count);

        Patient patient = (Patient) getIntent().getSerializableExtra("patientData");

        if (patient == null) {
            return;
        }

        textViewPatientName.append(patient.getName());
        textViewBloodType.append(patient.getBloodType());
        textViewUnitType.append(patient.getUnitType());
        textViewUnitCount.append(patient.getUnitCount());
        textViewDonorsCount.append(patient.getDonorsType());

    }

    public void exit(View view) {
        finish();
    }

}



