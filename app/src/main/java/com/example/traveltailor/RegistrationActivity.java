package com.example.traveltailor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "RegistrationActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mRegisterButton;
    private Button mAnonymousButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    //user is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    //user is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out:");
                }
            }
        };

        // Initialize UI elements
        mEmailEditText = (EditText) findViewById(R.id.email_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.password_edit_text);
        mRegisterButton = (Button) findViewById(R.id.register_button);
        mAnonymousButton = (Button) findViewById(R.id.anonymous_button);

        // Set click listeners for buttons
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerWithEmailAndPassword();
            }
        });

        mAnonymousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAnonymously();
            }
        });
    }

    private void registerAnonymously() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Anonymous sign-in successful
                            FirebaseUser user = mAuth.getCurrentUser();
                            // TODO: Implement what should happen after successful sign-in
                        } else {
                            // Anonymous sign-in failed
                            Toast.makeText(RegistrationActivity.this,
                                    "Anonymous sign-in failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void registerWithEmailAndPassword() {
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // User registration successful
                            FirebaseUser user = mAuth.getCurrentUser();
                            // TODO: Implement what should happen after successful registration
                        } else {
                            // User registration failed
                            Toast.makeText(RegistrationActivity.this,
                                    "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}


