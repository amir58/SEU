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
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class SignUpCodeActivity extends AppCompatActivity {
    public static String verificationId;

    EditText editTextSmsCode;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_code);

        editTextSmsCode = findViewById(R.id.sign_up_et_sms_code);

    }

    public void signUp(View view) {
        String inputSmsCode = editTextSmsCode.getText().toString();

        if (inputSmsCode.isEmpty()) {
            Toast.makeText(this, "برجاء ادخال كود التسجيل", Toast.LENGTH_SHORT).show();
            return;
        }

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, inputSmsCode);

        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SignUpCodeActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else{
                            String errorMessage = task.getException().getMessage();
                            Toast.makeText(SignUpCodeActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
