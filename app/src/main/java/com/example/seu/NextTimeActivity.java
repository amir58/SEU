package com.example.seu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class NextTimeActivity extends AppCompatActivity {
    TextView textViewNextTime;
    TextView textViewNextTimeType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_time);

        textViewNextTime = findViewById(R.id.next_time_tv);
        textViewNextTimeType = findViewById(R.id.next_time_type_tv);

        long donorMillis = getIntent().getLongExtra("donorDate", System.currentTimeMillis());

        if (MainActivity.donorData.getType().equals("blood")) {

            long yourDateMillis = donorMillis + (42 * 24 * 60 * 60 * 1000L);
            Time yourDate = new Time();
            yourDate.set(yourDateMillis);
            String result = yourDate.format("%Y-%m-%d");

            textViewNextTime.setText(result);

            textViewNextTimeType.setText("تاريخ تبرعك القادم بالدم");

        } else if (MainActivity.donorData.getType().equals("platelets")) {

            long yourDateMillis = donorMillis + (14 * 24 * 60 * 60 * 1000L);
            Time yourDate = new Time();
            yourDate.set(yourDateMillis);
            String result = yourDate.format("%Y-%m-%d");

            textViewNextTime.setText(result);

            textViewNextTimeType.setText("تاريخ تبرعك القادم بالصفائح");

        }
    }

    public void back(View view) {
        finish();
    }

}