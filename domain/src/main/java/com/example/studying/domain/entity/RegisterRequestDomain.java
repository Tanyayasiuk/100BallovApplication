package com.example.studying.domain.entity;

import android.app.Activity;

import com.example.studying.data.entity.DataModel;

import java.util.Date;

import javax.inject.Inject;

public class RegisterRequestDomain implements DataModel{

    private String deviceToken;
    private String deviceId;
    private String os;
    private String osVersion;
    private String[] channels;
    private Date expiration;
    private Activity activity;

    public RegisterRequestDomain(Activity activity) {
        this.activity = activity;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String[] getChannels() {
        return channels;
    }

    public void setChannels(String[] channels) {
        this.channels = channels;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
