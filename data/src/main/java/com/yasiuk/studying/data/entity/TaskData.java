package com.yasiuk.studying.data.entity;


public class TaskData implements DataModel {

    private int classNumber;
    private String task;

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
