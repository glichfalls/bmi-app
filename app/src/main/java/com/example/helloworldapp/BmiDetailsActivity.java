package com.example.helloworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class BmiDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_details);
        Bundle extras = getIntent().getExtras();
        float weight = extras.getFloat(BMIActivity.WEIGHT);
        float height = extras.getFloat(BMIActivity.HEIGHT);
        TextView weightLabel = findViewById(R.id.weight_value);
        TextView heightLabel = findViewById(R.id.height_value);
        TextView bmiLabel = findViewById(R.id.bmi_value);
        weightLabel.setText(String.format("%s kg", weight));
        heightLabel.setText(String.format("%s m", height));
        bmiLabel.setText(String.valueOf(Math.round(weight / (height * height))));
    }

}