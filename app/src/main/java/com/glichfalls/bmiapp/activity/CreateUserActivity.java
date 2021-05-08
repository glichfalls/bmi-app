package com.glichfalls.bmiapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.glichfalls.bmiapp.R;
import com.glichfalls.bmiapp.model.user.User;
import com.glichfalls.bmiapp.persistence.DatabaseAdapter;

import java.util.List;

public class CreateUserActivity extends MenuActivity {

    private final DatabaseAdapter database = new DatabaseAdapter(this);

    private Spinner spinner;
    private List<User> employees;
    private ArrayAdapter<User> adapter;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        Button button = findViewById(R.id.confirm_create_user_button);
        EditText input = findViewById(R.id.create_user_edit_text_name);
        spinner = findViewById(R.id.employee_spinner);
        database.open();

        Log.i("bmi_log", "your id: " + prefs.getLong("id", 0));

        loadEmployees(prefs.getLong("id", 0));


        button.setOnClickListener(view -> {
            String name = input.getText().toString();
            User user = database.insertUser(name);
            employees.add(user);
            safeSelectedUserToPrefs(user);
            spinner.setSelection(adapter.getPosition(user));
        });
    }

    private void loadEmployees(long selected) {
        employees = database.selectAllUser();
        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, employees);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                User user = (User) parent.getSelectedItem();
                safeSelectedUserToPrefs(user);
            }

            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
        if(selected != 0) {
            User selectedUser = database.selectUser(selected);
            if(selectedUser != null) {
                int pos = adapter.getPosition(selectedUser);
                if (pos != -1) {
                    spinner.setSelection(pos);
                }
            }
        }
    }

    private void safeSelectedUserToPrefs(User user) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", user.getName());
        editor.putLong("id", user.getId());
        editor.apply();
    }

}