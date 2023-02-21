package com.example.traveltailor;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.database.DatabaseError;
import com.squareup.picasso.Picasso;

public class UserActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private StorageReference mStorage;
    private TextView mNameTextView;
    private TextView mEmailTextView;
    private TextView mAgeTextView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Initialize Firebase references
        //mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        //mStorage = FirebaseStorage.getInstance().getReference().child("users").child(userId);

        // Initialize UI elements
        mNameTextView = (TextView) findViewById(R.id.user_name);
        mEmailTextView = (TextView) findViewById(R.id.user_email);
        mAgeTextView = (TextView) findViewById(R.id.user_age);
        mImageView = (ImageView) findViewById(R.id.user_image);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get user information from database
                String name = dataSnapshot.child("name").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                int age = dataSnapshot.child("age").getValue(Integer.class);
                String imageUrl = dataSnapshot.child("profileImage").getValue(String.class);

                // Set user information to UI elements
                mNameTextView.setText(name);
                mEmailTextView.setText(email);
                mAgeTextView.setText(String.valueOf(age));
                // Load profile image using Picasso library
                if (imageUrl != null) {
                    Picasso.get().load(imageUrl).into(mImageView);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.d(Tag, "Database error: " + databaseError.getMessage());
            }
        });
    }
}