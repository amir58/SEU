package com.example.seu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class CheckRequestDetailsActivty extends AppCompatActivity {

    TextView textViewDonationState;
    DonorData donorData;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_request_details);

        textViewDonationState = findViewById(R.id.tv_donation_state);

        donorData = (DonorData) getIntent().getSerializableExtra("donorData");

        textViewDonationState.setText("حالة الطلب \n" + donorData.getStateMessage());

    }

    public void deleteRequest(View view) {
        firestore.collection("donors")
                .document(donorData.getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CheckRequestDetailsActivty.this, "تم حذف الطلب", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(CheckRequestDetailsActivty.this, task.getException().getLocalizedMessage()
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void back(View view) {
        finish();
    }
}