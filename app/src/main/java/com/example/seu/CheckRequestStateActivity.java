package com.example.seu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CheckRequestStateActivity extends AppCompatActivity {
    EditText editTextDonationId;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_state);

        editTextDonationId = findViewById(R.id.card_et_id);


    }

    public void showCardData(View view) {
        String donationId = editTextDonationId.getText().toString();

        if (donationId.isEmpty()) {
            Toast.makeText(this, "اكتب رقم الطلب", Toast.LENGTH_LONG).show();
            return;
        }

        getDonationData(donationId);
    }

    private void getDonationData(String donationId) {
        firestore.collection("donors")
                .document(donationId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DonorData donorData = task.getResult().toObject(DonorData.class);

                        if (donorData == null) {
                            Toast.makeText(CheckRequestStateActivity.this, "ﻻ يوجد طلب بهذا الرقم", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Intent intent = new Intent(CheckRequestStateActivity.this, CheckRequestDetailsActivty.class);
                        intent.putExtra("donorData", donorData);
                        startActivity(intent);
                        finish();

                    }
                });
    }

    public void back(View view) {
        finish();
    }
}