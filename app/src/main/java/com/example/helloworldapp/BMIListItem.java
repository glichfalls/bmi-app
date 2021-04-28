package com.example.helloworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class BMIListItem extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_list_item);
        TextView title = findViewById(R.id.bmi_list_item_details_title);
        TextView description = findViewById(R.id.bmi_list_item_details_description);
        Bundle extras = getIntent().getExtras();
        title.setText(extras.getString("title"));
        description.setText(extras.getString("description"));
    }

}