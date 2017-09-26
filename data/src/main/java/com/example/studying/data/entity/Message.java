package com.example.studying.data.entity;

import java.util.HashMap;

public class Message implements DataModel {

    //Headers to form push-notifications according to Backendless' rules
    public static final String TICKER = "android-ticker-text";
    public static final String CONTENT_TITLE = "android-content-title";
    public static final String TITLE = "Новости";
    public static final String CONTENT_TEXT= "android-content-text";

    private String message;
    private HashMap<String, String> headers; //{"key1":"value1","key2":"value2"} using the headers above
    //optional:
    private String pushPolicy; // "PUBSUB" | "PUSH" | "BOTH"


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getPushPolicy() {
        return pushPolicy;
    }

    public void setPushPolicy(String pushPolicy) {
        this.pushPolicy = pushPolicy;
    }
}
