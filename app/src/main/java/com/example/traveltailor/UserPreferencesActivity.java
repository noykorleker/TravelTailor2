package com.example.traveltailor;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserPreferencesActivity extends AppCompatActivity {

    private ListView mActivitiesList;
    private Button mSaveButton;

    private String[] mActivityNames = {
            "Wineries",
            "Equestrian Sports",
            "Museums",
            "Historical Sites",
            "Beaches",
            "Nature Trails",
            "Food Tours",
            "Adventure Sports",
            "Art Galleries",
            "Spas"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);

        mActivitiesList = findViewById(R.id.activities_list);
        mSaveButton = findViewById(R.id.save_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, mActivityNames);
        mActivitiesList.setAdapter(adapter);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checked = mActivitiesList.getCheckedItemPositions();
                ArrayList<String> selectedActivities = new ArrayList<>();
                for (int i = 0; i < checked.size(); i++) {
                    if (checked.valueAt(i)) {
                        selectedActivities.add(mActivityNames[checked.keyAt(i)]);
                    }
                }
                // TODO: Save the selected activities to user preferences
                Toast.makeText(UserPreferencesActivity.this, "Selected activities: " + selectedActivities.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
