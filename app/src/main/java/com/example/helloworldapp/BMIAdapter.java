package com.example.helloworldapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.helloworldapp.bmi.BMI;

import java.util.ArrayList;

public class BMIAdapter extends ArrayAdapter<BMI> {

    public BMIAdapter(Activity activity, int resource, ArrayList<BMI> list) {
        super(activity, resource, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        BMI bmi = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bmi_item, parent, false);
        }
        TextView item = convertView.findViewById(R.id.bmi_item_name);
        item.setText(bmi.getTitle());
        return convertView;
    }

}
