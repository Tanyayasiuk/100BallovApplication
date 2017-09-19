package com.example.studying.a100ballovapplication.schedule;

public class ItemViewModel {

    public String day;
    public int dayNum;
    public String element;

    private String[] weekDays = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};

    public ItemViewModel(int day, String element) {
        this.day = weekDays[day-1];
        this.dayNum = day;
        this.element = element;
    }
}
