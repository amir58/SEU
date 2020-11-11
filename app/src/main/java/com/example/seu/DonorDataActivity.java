package com.example.seu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class DonorDataActivity extends AppCompatActivity {
    public static Activity screen;
    private static boolean isRunning;

    private EditText editTextDonorName, editTextDonorDateOfBirth, editTextDonorNationality, editTextDonorIdentity;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

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

        checkDonorIdentity();
//        finish();
    }

    private void checkDonorIdentity() {
        firestore.collection("donors")
                .whereEqualTo("identity", MainActivity.donorData.getIdentity())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot shots, @Nullable FirebaseFirestoreException e) {
                        int size = shots.getDocuments().size();
                        if (size == 0 && isRunning) {
                            startActivity(new Intent(DonorDataActivity.this, QuestionnaireActivity.class));
                            return;
                        }

                        DonorData donorData = shots.getDocuments().get(size - 1).toObject(DonorData.class);

                        long donationDate = Long.parseLong(donorData.getId());
                        long currentDate = System.currentTimeMillis();

                        if (donorData.isDonationStatus() && isRunning) {
                            Intent intent = new Intent(DonorDataActivity.this, NextTimeActivity.class);
                            intent.putExtra("donorDate", donationDate);
                            MainActivity.donorData.setType(donorData.getType());
                            MainActivity.donorData.setId(donorData.getId());
                            startActivity(intent);
                            return;
                        }

                        if (donationDate < currentDate && isRunning) {
                            Toast.makeText(DonorDataActivity.this, "لديك حجز بالفعل تابع حالته", Toast.LENGTH_SHORT).show();
                            return;
                        }



                        startActivity(new Intent(DonorDataActivity.this, QuestionnaireActivity.class));
                    }
                });

    }


    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;
    }
}