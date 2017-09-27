package com.yasiuk.studying.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class Enrollment implements DataModel {

    @SerializedName("subject")
    private String subject;

    @SerializedName("to")
    private String[] to;

    @SerializedName("bodyparts")
    private HashMap<String, String> bodyparts;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public HashMap<String, String> getBodyparts() {
        return bodyparts;
    }

    public void setBodyparts(HashMap<String, String> bodyparts) {
        this.bodyparts = bodyparts;
    }
}
