package com.chenshop.model.bean;

import com.chenshop.model.db.Bean;

import java.io.Serializable;

public final class PaymentAddress extends Bean<PaymentAddress> implements Serializable {
    private static final long serialVersionUID = 1L;

    static {
        beanInitialize(PaymentAddress.class);
    }

    @BeanField
    private String name;
    @BeanField
    private String phone;
    @BeanField
    private String detailAddress;
    @BeanField
    private String city;
    @BeanField
    private String district;
    @BeanField
    private String ward;

    public PaymentAddress(String name, String phone, String detailAddress, String city, String district, String ward) {
        this.name = name;
        this.phone = phone;
        this.detailAddress = detailAddress;
        this.city = city;
        this.district = district;
        this.ward = ward;
    }

    public PaymentAddress() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }
}
