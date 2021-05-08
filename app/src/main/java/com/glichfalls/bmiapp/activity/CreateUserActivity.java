package com.glichfalls.bmiapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.glichfalls.bmiapp.R;
import com.glichfalls.bmiapp.persistence.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class CreateUserActivity extends MenuActivity {

    private final DatabaseAdapter database = new DatabaseAdapter(this);

    private Spinner spinner;
    private List<String> employees;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        setContentView(R.layout.activity_create_user);
        Button button = findViewById(R.id.confirm_create_user_button);
        EditText input = findViewById(R.id.create_user_edit_text_name);
        spinner =  findViewById(R.id.employee_spinner);
        database.open();
        loadEmployees(prefs.getString("name", null));
        System.out.println(":::" + prefs.getString("name", "none"));
        button.setOnClickListener(view -> {
            String name = input.getText().toString();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("name", name);
            editor.apply();
            database.insertEmployee(name);
            employees.add(name);
            spinner.setSelection(adapter.getPosition(name));
        });
    }

    private void loadEmployees(String selected) {
        employees = database.selectAll();
        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, employees);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + name, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
        if(selected != null) {
            System.out.println(selected);
            spinner.setSelection(adapter.getPosition(selected));
        }
    }

}