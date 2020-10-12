package com.amirmohammed.seu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextPhoneNumber;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editTextPhoneNumber = findViewById(R.id.sign_up_phone_number);
        auth.setLanguageCode("ar");
    }

    public void signUp(View view) {
        String phoneNumberInput = editTextPhoneNumber.getText().toString();

        if (phoneNumberInput.isEmpty()) {
            Toast.makeText(this, "برجاء كتابة رقم الهاتف", Toast.LENGTH_SHORT).show();
            return;
        }

        String phoneNumberWithCode = "+966" + phoneNumberInput;

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumberWithCode,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks
            = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

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
                    , Toast.LENGTH_LONG).show();
        }

    };

}
