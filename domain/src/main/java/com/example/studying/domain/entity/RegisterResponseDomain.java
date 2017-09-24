package com.example.studying.domain.entity;

import com.example.studying.data.entity.DataModel;

public class RegisterResponseDomain implements DataModel {

    private String registrationId;
    private String message;
    private String code;

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
