package com.glichfalls.bmiapp.bmi;

import java.util.ArrayList;

public class BMICategories {

    private final ArrayList<BMI> list = new ArrayList<>();

    public BMICategories() {
        list.add(new BMI("Very severely underweight", 0, 15));
        list.add(new BMI("Severely underweight", 15, 16));
        list.add(new BMI("Underweight", 16, 18.5));
        list.add(new BMI("Normal (healthy weight)", 18.5, 25));
        list.add(new BMI("Overweight", 25, 30));
        list.add(new BMI("Obese Class I (Moderately obese)", 30, 35));
        list.add(new BMI("Obese Class II (Severely obese)", 35, 40));
        list.add(new BMI("Obese Class III (Very severely obese)", 40, 50));
    }

    public ArrayList<BMI> getAll() {
        return this.list;
    }

    public BMI get(double bmi) {
        for (BMI item : list) {
            if(item.getMin() <= bmi && item.getMax() >= bmi) {
                return item;
            }
        }
        throw new IllegalArgumentException("not found");
    }
    
}
