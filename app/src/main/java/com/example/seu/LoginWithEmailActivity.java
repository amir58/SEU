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

public class LoginWithEmailActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_email);

        editTextEmail = findViewById(R.id.login_et_email);
        editTextPassword = findViewById(R.id.login_et_password);
    }

    public void login(View view) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "اكتب البريد الالكتروتني و كلمة المر،ر", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(LoginWithEmailActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    String errorMessage = task.getException().getLocalizedMessage();
                    Toast.makeText(LoginWithEmailActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpWithEmailActivity.class);
        startActivity(intent);
    }


}