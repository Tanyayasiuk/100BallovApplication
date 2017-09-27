package com.yasiuk.studying.domain.entity;

import java.util.HashMap;


public class EnrollDomain implements DomainModel{

    private String subject;
    private HashMap<String, String> bodyparts;
    private String[] to;
    private String[] attachment;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getAttachment() {
        return attachment;
    }

    public void setAttachment(String[] attachment) {
        this.attachment = attachment;
    }

    public HashMap<String, String> getBodyparts() {
        return bodyparts;
    }

    public void setBodyparts(HashMap<String, String> bodyparts) {
        this.bodyparts = bodyparts;
    }
}
