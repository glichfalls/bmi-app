package com.glichfalls.bmiapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.glichfalls.bmiapp.R;
import com.glichfalls.bmiapp.bmi.BMI;
import com.glichfalls.bmiapp.bmi.BMICategories;
import com.glichfalls.bmiapp.persistence.DatabaseAdapter;

public class BmiDetailsActivity extends MenuActivity {

    private double calculatedBmi;

    private DatabaseAdapter database;
    private SharedPreferences prefs;

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
        Button save = findViewById(R.id.save_bmi_button);
        save.setOnClickListener(view -> {
            database = new DatabaseAdapter(this);
            prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            database.open();
            long id = prefs.getLong("id", 0);
            if(id == 0) {
                Toast.makeText(this, "no user selected", Toast.LENGTH_SHORT).show();
                return;
            }
            database.insertBmi(id, height, weight);
            Toast.makeText(this, "bmi was saved successfully", Toast.LENGTH_SHORT).show();
        });
    }


}