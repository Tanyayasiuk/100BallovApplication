package com.example.studying.data.entity;

import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;


public class Profile implements DataModel {

    private String name;

    @SerializedName("surname")
    private String surname;
    private String education;
    private String additional;
    private String lessontype;
    private String classes;
    private String comment;
    private String imageUrl;

    //private ImageView view;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getLessontype() {
        return lessontype;
    }

    public void setLessontype(String lessontype) {
        this.lessontype = lessontype;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
