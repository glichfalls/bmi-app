package com.example.helloworldapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class BMIActivity extends Activity {

    public static final String WEIGHT = "weight";
    public static final String HEIGHT = "height";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i);
        Button button = findViewById(R.id.button4);
        button.setOnClickListener(view -> {
            EditText weight = findViewById(R.id.editTextNumberDecimal);
            EditText height = findViewById(R.id.editTextNumberDecimal2);
            Intent intent = new Intent(this, BmiDetailsActivity.class);
            intent.putExtra(WEIGHT, Float.parseFloat(weight.getText().toString()));
            intent.putExtra(HEIGHT, Float.parseFloat(height.getText().toString()));
            startActivity(intent);
        });
    }

}