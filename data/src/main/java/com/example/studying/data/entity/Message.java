package com.example.studying.data.entity;

import java.util.HashMap;

public class Message implements DataModel {

    private String message;

    //All of the following are optional
    private HashMap<String, String> headers; //{"key1":"value1","key2":"value2"}
    private String pushPolicy; // "PUBSUB" | "PUSH" | "BOTH"
    private String publishAt; //timestamp // the value in milliseconds
    private String repeatEvery;//frequency-in-seconds
    private String repeatExpiresAt; //expiration-timestamp

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
