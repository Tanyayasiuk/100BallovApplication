package com.yasiuk.studying.domain.entity;


import com.google.gson.annotations.SerializedName;

public class Teacher {

    @SerializedName("name")
    private String name;

    @SerializedName("surname")
    private String surname;

    @SerializedName("education")
    private String education;

    @SerializedName("additional")
    private String additional;

    @SerializedName("lessontype")
    private String lessontype;

    @SerializedName("classes")
    private String classes;

    @SerializedName("comment")
    private String comment;

    @SerializedName("imageUrl")
    private String imageUrl;


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
