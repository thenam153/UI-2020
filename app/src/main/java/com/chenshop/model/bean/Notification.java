package com.chenshop.model.bean;

import com.chenshop.model.db.Bean;

import java.io.Serializable;
import java.util.Date;

public final class Notification extends Bean<Notification> implements Serializable {
    private static final long serialVersionUID = 1L;

    static {
        beanInitialize(Notification.class);
    }

    @BeanField
    private String name;
    @BeanField
    private String details;
    @BeanField
    private Date createdDate;
    @BeanField
    private Date modifiedDate;

    public Notification(String name, String details, Date createdDate, Date modifiedDate) {
        this.name = name;
        this.details = details;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Notification() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
