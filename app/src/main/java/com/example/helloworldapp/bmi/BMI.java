package com.example.helloworldapp.bmi;

public class BMI {

    private final String title;
    private final double min;
    private final double max;

    public BMI(String title, double min, double max) {
        this.title = title;
        this.min = min;
        this.max = max;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        String s = String.format("from %fkg to %fkg", min, max);
        System.out.println(s);
        return s;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }
}
