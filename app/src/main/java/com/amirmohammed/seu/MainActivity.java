package com.amirmohammed.seu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public static DonorData donorData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        donorData = new DonorData();
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
        if (item.getItemId() == R.id.menu_log_out) {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(MainActivity.this, SignUpCodeActivity.class));
        } else {
            Toast.makeText(this, "قريباً", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}


