package com.yasiuk.studying.a100ballovapplication.books;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BooksItemViewModel {

    public String title;
    public String url;

    public BooksItemViewModel(String title, String url) throws ParseException {
        StringBuilder builder = new StringBuilder();
        builder.append("Параграф № ");
        String bookTitle = title.replaceAll("\\D+\\d+\\D+0?", "").replaceAll("\\.\\D+", "");
        builder.append(bookTitle);
        this.title = builder.toString();
        this.url = url;
    }
}
