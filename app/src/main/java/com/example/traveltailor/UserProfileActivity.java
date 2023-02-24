package com.example.traveltailor;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    private ImageView mUserPhoto;
    private EditText mUserName;
    private EditText mUserEmail;
    private EditText mUserAge;
    private Spinner mPreferencesSpinner;
    private Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        /*mUserPhoto = findViewById(R.id.user_photo);
        mUserName = findViewById(R.id.user_name);
        mUserEmail = findViewById(R.id.user_email);
        mUserAge = findViewById(R.id.user_age);
        mPreferencesSpinner = findViewById(R.id.preferences_spinner);
        mSaveButton = findViewById(R.id.save_button);
*/
     /*   // Set the spinner adapter with preferences list
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.preferences, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPreferencesSpinner.setAdapter(adapter);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserProfile();
            }
        });
    }*/

/*    private void saveUserProfile() {
        // Get the user profile details from UI components
        String userName = mUserName.getText().toString();
        String userEmail = mUserEmail.getText().toString();
        int userAge = Integer.parseInt(mUserAge.getText().toString());
        String userPreference = mPreferencesSpinner.getSelectedItem().toString();

        // TODO: Save user profile to database or shared preferences

        // Show a toast message to indicate the profile has been saved
        Toast.makeText(this, "User profile saved", Toast.LENGTH_SHORT).show();
    }*/
    }
}
