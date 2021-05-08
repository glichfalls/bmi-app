package com.example.helloworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateUserActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        setContentView(R.layout.activity_create_user);
        Button button = findViewById(R.id.confirm_create_user_button);
        EditText input = findViewById(R.id.create_user_edit_text_name);
        TextView username = findViewById(R.id.current_user_value);
        username.setText(prefs.getString("name", ""));
        button.setOnClickListener(view -> {
            String name = input.getText().toString();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("name", name);
            editor.apply();
            username.setText(name);
        });

    }
}