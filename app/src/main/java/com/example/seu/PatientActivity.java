package com.example.seu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PatientActivity extends AppCompatActivity {
    EditText editTextPatientId;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        editTextPatientId = findViewById(R.id.patient_et_id);

        Patient patient
                = new Patient("1", "Nora", "O", "none", "10", "2");
        firestore.collection("patients").document("1")
                .set(patient);
    }

    public void showPatientData(View view) {
        String patientId = editTextPatientId.getText().toString();

        if (patientId.isEmpty()) {
            Toast.makeText(this, "برجاء كتابة رقم الملف", Toast.LENGTH_LONG).show();
            return;
        }

        getPatientData(patientId);

    }

    private void getPatientData(final String patientId) {
        firestore.collection("patients")
                .document(patientId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        Patient patient = task.getResult().toObject(Patient.class);

                        if (patient == null) {
                            Toast.makeText(PatientActivity.this, "لم نجد الملف", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent intent = new Intent(PatientActivity.this, PatientDetailsActivity.class);
                        intent.putExtra("patientData", patient);
                        startActivity(intent);
                        finish();

                    }
                });
    }

}
