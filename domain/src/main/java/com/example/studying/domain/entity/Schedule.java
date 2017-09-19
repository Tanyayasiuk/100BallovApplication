package com.example.studying.domain.entity;


import android.support.annotation.NonNull;

public class Schedule implements DomainModel, Comparable<Schedule> {

    private int day;
    private String subject;
    private String time;
    private int classNumber;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }


    @Override
    public int compareTo(@NonNull Schedule schedule) {
        /*Integer day1 = day;
        Integer day2 = schedule.getDay();
        return day1.compareTo(day2);*/
        return getTime().compareTo(schedule.getTime());
    }
}
