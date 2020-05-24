package com.chenshop.model.bean;

import com.chenshop.model.db.Bean;

import java.io.Serializable;

public final class Image extends Bean<Image> implements Serializable {
    private static final long serialVersionUID = 1L;

    static {
        beanInitialize(Image.class);
    }

    @BeanField
    private String name;
    @BeanField
    private String details;
    @BeanField
    private String url;
    @BeanField
    private Integer productId;

    public Image(String name, String details, String url, Integer productId) {
        this.name = name;
        this.details = details;
        this.url = url;
        this.productId = productId;
    }

    public Image() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
