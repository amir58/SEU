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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

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
        String donorIdentity = editTextDonationId.getText().toString();

        if (donorIdentity.isEmpty()) {
            Toast.makeText(this, "اكتب رقم الهوية", Toast.LENGTH_LONG).show();
            return;
        }

        getDonationData(donorIdentity);
    }

    private void getDonationData(String donorIdentity) {
        firestore.collection("donors")
                .whereEqualTo("identity" , donorIdentity)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        int size = queryDocumentSnapshots.size();

                        if (size <= 0) {
                            Toast.makeText(CheckRequestStateActivity.this, "ﻻ يوجد طلب تبرع فى الوقت الحالى", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        DonorData donorData = queryDocumentSnapshots.getDocuments().get(size - 1).toObject(DonorData.class);

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