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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpWithEmailActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_with_email);

        editTextEmail = findViewById(R.id.register_et_email);
        editTextPassword = findViewById(R.id.register_et_password);
    }

    public void signUp(View view) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "اكتب البريد الالكتروتني و كلمة المر،ر", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpWithEmailActivity.this, "تم التسجيل بنجاح", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    String errorMessage = task.getException().getLocalizedMessage();
                    Toast.makeText(SignUpWithEmailActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}