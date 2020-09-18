package com.amirmohammed.seu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class QuestionnaireActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        startActivity(new Intent(QuestionnaireActivity.this, DonorTypeActivity.class));
        finish();
    }
}
