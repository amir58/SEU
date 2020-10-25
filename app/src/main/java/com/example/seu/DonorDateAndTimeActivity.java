package com.example.seu;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

public class DonorDateAndTimeActivity extends AppCompatActivity {
    DatePicker datePicker;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_date_and_time);
        datePicker = findViewById(R.id.donor_date_picker);
        timePicker = findViewById(R.id.donor_time_picker);


        datePicker.setMinDate(System.currentTimeMillis());

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int i1) {
                if(hourOfDay < 8 || hourOfDay > 23) {
                    timePicker.setHour(8);
                    Toast.makeText(DonorDateAndTimeActivity.this,
                            "مواعيد العمل : 8 صباحاً حتى 11 مساءاً", Toast.LENGTH_SHORT).show();
                }

            }
        });

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                Date date = new Date(year, month + 1, day);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                if (dayOfWeek == 3 || dayOfWeek == 4) {
                    Toast.makeText(DonorDateAndTimeActivity.this, "يومى الجمعة و السبت ", Toast.LENGTH_SHORT).show();

                    datePicker.init(year, month, calendar.getFirstDayOfWeek(), this);
                }

            }
        });

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

                            closeAllActivities();

                        }
                    }
                });
    }

    private void closeAllActivities() {
        finish();
        DonorDataActivity.screen.finish();
        QuestionnaireActivity.screen.finish();;
        DonorTypeActivity.screen.finish();
    }


}
