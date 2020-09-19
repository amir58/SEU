package com.amirmohammed.seu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextPhoneNumber;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    private static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editTextPhoneNumber = findViewById(R.id.sign_up_phone_number);
        auth.setLanguageCode("ar");
    }

    public void signUp(View view) {
        String phoneNumber = editTextPhoneNumber.getText().toString();

        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "برجاء كتابة رقم الهاتف", Toast.LENGTH_SHORT).show();
            return;
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            Intent intent = new Intent(SignUpActivity.this, SignUpCodeActivity.class);
            intent.putExtra("credential", credential);
            startActivity(intent);
            finish();
        }
        @Override
        public void onVerificationFailed(FirebaseException e) {
            String errorMessage = e.getMessage();
            Toast.makeText(SignUpActivity.this, errorMessage
                    , Toast.LENGTH_SHORT).show();
        }

    };

}
