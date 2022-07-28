package com.example.smokingarealist;

public class SmokingData {
    int count;
    int month;
    int day;
    String time;

    public SmokingData(int month, int day, String time, int count) {
        this.month = month;
        this.day = day;
        this.time = time;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public int getMonth() {
        return month;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }
}
