package com.example.seu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class CampaignsActivity extends AppCompatActivity {

    StorageReference storage = FirebaseStorage.getInstance().getReference();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    String letterUrl = "";
    EditText editTextLocation;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaigns);
        editTextLocation = findViewById(R.id.campaigns_et_location);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("جارى ارفاق الخطاب");
        progressDialog.setCancelable(false);
    }

    public void addAttachment(View view) {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent, 1);
    }

    public void register(View view) {
        String location = editTextLocation.getText().toString();

        if (location.isEmpty()) {
            Toast.makeText(this, "حدد الموقع", Toast.LENGTH_SHORT).show();
            return;
        }

        if (letterUrl.isEmpty()) {
            Toast.makeText(this, "ارفق الخطاب", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> campaignData = new HashMap<>();
        campaignData.put("letterUrl", letterUrl);
        campaignData.put("location", location);
        campaignData.put("date", getIntent().getStringExtra("date"));
        campaignData.put("time", getIntent().getStringExtra("time"));

        firestore.collection("letters").document(auth.getUid())
                .set(campaignData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CampaignsActivity.this, "تم التسجيل", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri letterPath = data.getData();
            uploadFile(letterPath);
        }
    }

    private void uploadFile(Uri filePath) {
        progressDialog.show();
        storage.child("letters").child(auth.getUid())
                .putFile(filePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    getLetterUrl();
                } else {
                    Toast.makeText(CampaignsActivity.this, "فشل ارفاق الخطاب", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getLetterUrl() {
        storage.child("letters").child(auth.getUid())
                .getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CampaignsActivity.this, "تم ارفاق الخطاب بنجاح", Toast.LENGTH_LONG).show();
                    letterUrl = task.getResult().toString();
                    progressDialog.dismiss();
                }
            }
        });
    }


}
