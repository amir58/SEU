package com.example.seu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class CardActivity extends AppCompatActivity {
    EditText editTextDonorIdentity;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        editTextDonorIdentity = findViewById(R.id.card_et_id);

    }


    public void showCardData(View view) {
        String donorIdentity = editTextDonorIdentity.getText().toString();

        if (donorIdentity.isEmpty() || donorIdentity.length() < 10) {
            Toast.makeText(this, "رقم الهوية يجب ان يكون 10 ارقام", Toast.LENGTH_LONG).show();
            return;
        }

        getDonorData(donorIdentity);
    }

    private void getDonorData(String donorIdentity) {
        firestore.collection("donors")
                .whereEqualTo("identity", donorIdentity)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (queryDocumentSnapshots.isEmpty()) {
                            Toast.makeText(CardActivity.this, "ﻻ يوجد بيانات لهذا المتبرع", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        DonorData donorData = queryDocumentSnapshots.getDocuments().get(0).toObject(DonorData.class);
                        int donorCount = 0;

                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                            DonorData donor = documentSnapshot.toObject(DonorData.class);
                            if (donor.isDonationStatus()) donorCount++;
                        }

                        Intent intent = new Intent(CardActivity.this, CardDetailsActivity.class);
                        intent.putExtra("donorData", donorData);
                        intent.putExtra("donorCount", donorCount);
                        startActivity(intent);
                        finish();

                    }
                });

    }

}
