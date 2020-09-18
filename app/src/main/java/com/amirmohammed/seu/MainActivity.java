package com.amirmohammed.seu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void goToDonorActivity(View view) {
        startActivity(new Intent(MainActivity.this, DonorDataActivity.class));
    }

    public void goToPatient(View view) {
        startActivity(new Intent(MainActivity.this, PatientActivity.class));
    }

    public void goToCampaigns(View view) {
        startActivity(new Intent(MainActivity.this, CampaignsDateAndTimeActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "قريباً", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

}


