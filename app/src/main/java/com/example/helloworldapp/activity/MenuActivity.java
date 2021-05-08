package com.example.helloworldapp.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworldapp.R;

public abstract class MenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.menu_item_calculate:
                startActivity(new Intent(this, BMIActivity.class));
                return true;
            case R.id.menu_item_bmi_list:
                startActivity(new Intent(this, BMIListActivity.class));
                return true;
            case R.id.menu_item_settings:
                startActivity(new Intent(this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
