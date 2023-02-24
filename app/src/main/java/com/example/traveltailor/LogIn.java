package com.example.traveltailor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private TextView textView;

    private FirebaseAuth mAuth;;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameEditText = findViewById(R.id.username_edit_text);
        mPasswordEditText = findViewById(R.id.password_edit_text);
        mLoginButton = findViewById(R.id.login_button);
        textView =  findViewById(R.id.move_to_sign_in);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsernameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                textView.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view) {
                        Intent intent = new Intent( LogIn.this, SignIn.class);
                        startActivity(intent);
                    }
                });
                // Here you can add your authentication logic to check the username and password

                // For now, let's just show a toast message
                Toast.makeText(LogIn.this, "Username: "
                        + username + " Password: " + password, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
