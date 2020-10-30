package com.example.seu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
            startActivity(new Intent(MainActivity.this, SignUpActivity.class));
        } else if (item.getItemId() == R.id.menu_card) {
            startActivity(new Intent(MainActivity.this, CardActivity.class));


        } else if (item.getItemId() == R.id.menu_rate_app) {
            startActivity(new Intent(MainActivity.this, RateAppActivity.class));


        } else if (item.getItemId() == R.id.menu_share_app) {
            shareApp();
        } else if (item.getItemId() == R.id.menu_about_us){
            startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
        }
        else if (item.getItemId() == R.id.menu_blood_type){
            startActivity(new Intent(MainActivity.this, BloodTypeInfoActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }


    private void shareApp() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
        String shareMessage= "\nانصحك بتجربة تطبيق قطرة امل الذى يساعدك على التبرع بالدم بسهولة\n\n";
        shareMessage = shareMessage + "https://drive.google.com/drive/folders/1DyRbwnE6IqX2uMoZ7jyYBN9Lbmqb-Owg?usp=sharing" +"\n\n";
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(shareIntent, "choose one"));
    }
}


