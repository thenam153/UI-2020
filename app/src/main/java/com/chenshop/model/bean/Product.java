
package com.chenshop.model.bean;

import com.chenshop.model.db.Bean;

import java.io.Serializable;
import java.util.Date;

public final class Product extends Bean<Product> implements Serializable {
    private static final long serialVersionUID = 1L;

    static {
        beanInitialize(Product.class);
    }

    @BeanField
    private String name;
    @BeanField
    private String details;
    @BeanField
    private Double rating;
    @BeanField
    private Long quantity;
    @BeanField
    private Long price;
    @BeanField
    private Integer shopId;
    @BeanField
    private Date createdDate;
    @BeanField
    private Date modifiedDate;

    public Product(String name, String details, Double rating, Long quantity, Long price, Integer shopId, Date createdDate, Date modifiedDate) {
        this.name = name;
        this.details = details;
        this.rating = rating;
        this.quantity = quantity;
        this.price = price;
        this.shopId = shopId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Product() {
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
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