package com.example.studying.a100ballovapplication.news;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewsItemViewModel {

    public String title;
    public String date;
    public String newsText;

    public NewsItemViewModel(String title, String dateString, String newsText) throws ParseException {
        this.title = title;
        this.date = formatDate(dateString);
        this.newsText = newsText;
    }

    private String formatDate(String dateString){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.valueOf(dateString));
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return String.valueOf(format.format(calendar.getTime()));
    }
}
