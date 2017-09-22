package com.example.studying.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ОК on 19.09.2017.
 */

public class AccessTokenData implements DataModel{

    @SerializedName("access-token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
