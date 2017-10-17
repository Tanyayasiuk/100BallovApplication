package com.yasiuk.studying.domain.entity;

/**
 * Created by ОК on 10.10.2017.
 */

public class Book implements DomainModel {

    private String name;
    private String publicUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }
}
