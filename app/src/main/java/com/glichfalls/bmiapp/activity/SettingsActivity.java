package com.glichfalls.bmiapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.glichfalls.bmiapp.R;

public class SettingsActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button button = findViewById(R.id.create_user_button);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreateUserActivity.class);
            startActivity(intent);
        });

    }

}