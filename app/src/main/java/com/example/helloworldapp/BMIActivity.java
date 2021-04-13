package com.example.helloworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class BMIActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i);
        Button button = findViewById(R.id.button4);
        button.setOnClickListener(view -> {
            EditText weight = findViewById(R.id.editTextNumberDecimal);
            EditText height = findViewById(R.id.editTextNumberDecimal2);
            float w = Float.parseFloat(weight.getText().toString());
            float h = Float.parseFloat(height.getText().toString());
            float bmi = w / (h * h);
            System.out.println("your bmi is: " + bmi);
        });
    }



}