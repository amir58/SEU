package com.example.seu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RateAppActivity extends AppCompatActivity {
    EditText editTextName;
    RatingBar ratingBar;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_app);

        editTextName = findViewById(R.id.rating_name);
        ratingBar = findViewById(R.id.rating_bar);
    }

    public void rateApp(View view) {
        String name = editTextName.getText().toString();
        float rating = ratingBar.getRating();

        if (name.isEmpty()) {
            Toast.makeText(this, " اكتب الاسم", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rating <= 0) {
            Toast.makeText(this, "قيم التطبيق", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> ratingApp = new HashMap<>();
        ratingApp.put("name", name);
        ratingApp.put("rating", rating);

        firestore.collection("ratings").document().set(ratingApp)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RateAppActivity.this, "تم التقييم بنجاح", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                });
    }
}