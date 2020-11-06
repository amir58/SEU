package com.example.seu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DonorDataActivity extends AppCompatActivity {
    public static Activity screen;

    private EditText editTextDonorName, editTextDonorDateOfBirth
            , editTextDonorNationality, editTextDonorIdentity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_data);
        screen = this;

        editTextDonorName = findViewById(R.id.donor_data_et_donor_name);
        editTextDonorDateOfBirth = findViewById(R.id.donor_data_et_donor_date_of_birth);
        editTextDonorNationality = findViewById(R.id.donor_data_et_donor_nationality);
        editTextDonorIdentity = findViewById(R.id.donor_data_et_donor_identity);

    }

    public void pushDonorData(View view) {
        String name = editTextDonorName.getText().toString();
        String dateOfBirth = editTextDonorDateOfBirth.getText().toString();
        String nationality = editTextDonorNationality.getText().toString();
        String identity = editTextDonorIdentity.getText().toString();

        if (name.isEmpty() || dateOfBirth.isEmpty() || nationality.isEmpty() || identity.isEmpty()) {
            Toast.makeText(this, "اكتب البيانات كاملة", Toast.LENGTH_SHORT).show();
            return;
        }

        if (identity.length() < 10) {
            Toast.makeText(screen, "رقم الهوية يجب ان يكون 10 ارقام", Toast.LENGTH_SHORT).show();
            return;
        }

        MainActivity.donorData.setName(name);
        MainActivity.donorData.setDateOfBirth(dateOfBirth);
        MainActivity.donorData.setNationality(nationality);
        MainActivity.donorData.setIdentity(identity);
        MainActivity.donorData.setBloodType("تحت المراجعة");

        startActivity(new Intent(this, QuestionnaireActivity.class));
//        finish();

    }



}
