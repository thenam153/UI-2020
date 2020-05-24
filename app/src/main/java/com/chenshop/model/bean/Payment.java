package com.chenshop.model.bean;

import com.chenshop.model.db.Bean;

import java.io.Serializable;

public final class Payment extends Bean<Payment> implements Serializable {
    private static final long serialVersionUID = 1L;

    static {
        beanInitialize(Payment.class);
    }

    @BeanField
    private String name;
    @BeanField
    private String details;

    public Payment(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public Payment() {
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
