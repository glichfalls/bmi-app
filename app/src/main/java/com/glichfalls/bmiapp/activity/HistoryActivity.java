package com.glichfalls.bmiapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.glichfalls.bmiapp.R;
import com.glichfalls.bmiapp.model.bmi.BMI;
import com.glichfalls.bmiapp.persistence.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends MenuActivity {

    private DatabaseAdapter database;
    private SharedPreferences prefs;

    private List<BMI> bmis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        database = new DatabaseAdapter(this);
        database.open();
        bmis = database.selectAllBmiByUser(prefs.getLong("id", 0));

    }

}