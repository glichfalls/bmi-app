package com.example.helloworldapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.helloworldapp.R;

public class MainActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button_start);
        button.setOnClickListener(view -> {
            startActivity(new Intent(this, BMIActivity.class));
        });
    }



}