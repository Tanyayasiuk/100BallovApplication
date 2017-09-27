package com.yasiuk.studying.data.entity;

import com.google.gson.annotations.SerializedName;

public class StudentLoginData implements DataModel {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("objectId")
    private String objectId;

    @SerializedName("login")
    private String login; /*login in login-method is email (unique identity)*/

    @SerializedName("user-token")
    private String token;

    @SerializedName("class")
    private int classNum;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }
}
