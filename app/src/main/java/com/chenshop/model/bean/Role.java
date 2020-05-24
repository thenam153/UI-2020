package com.chenshop.model.bean;

import com.chenshop.model.db.Bean;

import java.io.Serializable;

public final class Role extends Bean<Role> implements Serializable {
    private static final long serialVersionUID = 1L;

    static {
        beanInitialize(Role.class);
    }

    @BeanField
    private String name;
    @BeanField
    private String details;
    @BeanField
    private Integer level;

    public Role(String name, String details, Integer level) {
        this.name = name;
        this.details = details;
        this.level = level;
    }

    public Role() {
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
