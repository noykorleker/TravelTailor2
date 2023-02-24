package com.example.traveltailor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    private static final String TAG = "SignIn";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mRegisterButton;
    private Button mAnonymousButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        // Initialize UI elements
        mEmailEditText = findViewById(R.id.email_edit_text);
        mPasswordEditText = findViewById(R.id.password_edit_text);
        mRegisterButton = findViewById(R.id.register_button);
        mAnonymousButton = findViewById(R.id.anonymous_button);
        textView = findViewById(R.id.move_to_login);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //user is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    //user is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out:");
                }
            }
        };

        // Set click listeners for buttons
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUserWithEmailAndPassword();
            }
        });
        mAnonymousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAnonymously();
            }
        });
        textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent( SignIn.this, LogIn.class);
                startActivity(intent);
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
                            Intent intent = new Intent(SignIn.this, UserProfileActivity.class);
                            startActivity(intent);
                            // TODO: Implement what should happen after successful sign-in
                        } else {
                            // Anonymous sign-in failed
                            Toast.makeText(SignIn.this,
                                    "Anonymous sign-in failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void createUserWithEmailAndPassword() {
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignIn.this,
                                    "Registration successful", Toast.LENGTH_SHORT).show();
                            // User registration successful
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(SignIn.this, LogIn.class);
                            startActivity(intent);
                        } else {
                            // User registration failed
                            Toast.makeText(SignIn.this,
                                    "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

