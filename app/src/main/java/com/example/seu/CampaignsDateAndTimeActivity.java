package com.example.seu;

import android.content.Intent;
import android.icu.util.LocaleData;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CampaignsDateAndTimeActivity extends AppCompatActivity {
    DatePicker datePicker;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_date_and_time);

        datePicker = findViewById(R.id.donor_date_picker);
        timePicker = findViewById(R.id.donor_time_picker);

        datePicker.setMinDate(System.currentTimeMillis());

        timeChange();
        dateChange();
    }

    private void timeChange() {
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int i1) {
                if (hourOfDay < 8 || hourOfDay > 23) {
                    timePicker.setHour(8);
                    Toast.makeText(CampaignsDateAndTimeActivity.this,
                            "مواعيد العمل : 8 صباحاً حتى 11 مساءاً", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void dateChange() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                    Date date = new Date(year, month, (day - 1));

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);

                    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                    switch (dayOfWeek) {
                        case Calendar.FRIDAY:
                        case Calendar.SATURDAY:
                            Toast.makeText(CampaignsDateAndTimeActivity.this, "غير متاح", Toast.LENGTH_SHORT).show();
                            datePicker.init(year, month, calendar.getFirstDayOfWeek(), this);
                            break;
                    }

                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void submitDateAndTime(View view) {
        String date = datePicker.getDayOfMonth() + "/" +
                datePicker.getMonth() + "/" + datePicker.getYear();

        String time = timePicker.getHour() + " : " + timePicker.getMinute();

        Intent intent = new Intent(CampaignsDateAndTimeActivity.this, CampaignsActivity.class);
        intent.putExtra("date", date);
        intent.putExtra("time", time);

        startActivity(intent);
        finish();

    }

}
