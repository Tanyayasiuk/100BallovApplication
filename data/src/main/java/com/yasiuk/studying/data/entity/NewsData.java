package com.yasiuk.studying.data.entity;

import com.google.gson.annotations.SerializedName;

public class NewsData implements DataModel {

    @SerializedName("newstitle")
    private String newstitle;

    @SerializedName("newsdate")
    private String newsdate;

    @SerializedName("newstext")
    private String newstext;

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getNewsdate() {
        return newsdate;
    }

    public void setNewsdate(String newsdate) {
        this.newsdate = newsdate;
    }

    public String getNewstext() {
        return newstext;
    }

    public void setNewstext(String newstext) {
        this.newstext = newstext;
    }
}
