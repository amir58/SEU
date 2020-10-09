package com.amirmohammed.seu;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class DonorDateAndTimeActivity extends AppCompatActivity {
    DatePicker datePicker;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_date_and_time);
        datePicker = findViewById(R.id.donor_date_picker);
        timePicker = findViewById(R.id.donor_time_picker);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void submitDateAndTime(View view) {
        String date = datePicker.getDayOfMonth() + "/" +
                datePicker.getMonth() + "/" + datePicker.getYear();

        String time = timePicker.getHour() + " : " + timePicker.getMinute();

        MainActivity.donorData.setDate(date);
        MainActivity.donorData.setTime(time);

        uploadDonorData();

    }

    private void uploadDonorData() {
        String donorId = String.valueOf(System.currentTimeMillis());
        MainActivity.donorData.setId(donorId);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("donors")
                .document(donorId)
                .set(MainActivity.donorData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(DonorDateAndTimeActivity.this
                                    , "تم التسجيل", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }
                });
    }


}
