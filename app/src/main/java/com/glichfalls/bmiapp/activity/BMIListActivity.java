package com.glichfalls.bmiapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.glichfalls.bmiapp.R;
import com.glichfalls.bmiapp.adapter.BMIAdapter;
import com.glichfalls.bmiapp.bmi.BMI;
import com.glichfalls.bmiapp.bmi.BMICategories;

public class BMIListActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_list);
        BMIAdapter adapter = new BMIAdapter(this, android.R.layout.simple_list_item_1, new BMICategories().getAll());
        ListView listView = findViewById(R.id.bmi_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            BMI bmi = (BMI) parent.getItemAtPosition(position);
            Intent intent = new Intent(this, BMIListItemActivity.class);
            intent.putExtra("title", bmi.getTitle());
            intent.putExtra("description", bmi.getDescription());
            startActivity(intent);
        });
    }

}