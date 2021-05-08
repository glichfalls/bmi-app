package com.example.helloworldapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.helloworldapp.R;
import com.example.helloworldapp.bmi.BMI;
import com.example.helloworldapp.bmi.BMICategories;

public class BmiDetailsActivity extends MenuActivity {

    private double calculatedBmi;

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
        calculatedBmi = Math.round(weight / (height * height));
        bmiLabel.setText(String.valueOf(calculatedBmi));
        Button button = findViewById(R.id.bmi_list_button);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(this, BMIListItemActivity.class);
            BMI bmi = new BMICategories().get(calculatedBmi);
            intent.putExtra("title", bmi.getTitle());
            intent.putExtra("description", bmi.getDescription());
            startActivity(intent);
        });
    }


}