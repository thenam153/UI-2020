package com.chenshop.model.bean;

import com.chenshop.model.db.Bean;

import java.io.Serializable;

public final class Bank extends Bean<Bank> implements Serializable {
    private static final long serialVersionUID = 1L;

    static {
        beanInitialize(Bank.class);
    }

    @BeanField
    private String name;
    @BeanField
    private String details;

    public Bank(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public Bank() {
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
}
