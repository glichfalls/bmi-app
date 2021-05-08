package com.glichfalls.bmiapp.model.bmi;

public class BMI {

    private long id;
    private long user;
    private long timestamp;
    private float height;
    private float weight;

    public BMI(long id, long user, long timestamp, float height, float weight) {
        this.id = id;
        this.user = user;
        this.timestamp = timestamp;
        this.height = height;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public long getUser() {
        return user;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }
}
