package com.chenshop.model.bean;

import com.chenshop.model.db.Bean;

import java.io.Serializable;
import java.util.Date;

public final class User extends Bean<User> implements Serializable {
    private static final long serialVersionUID = 1L;

    static {
        beanInitialize(User.class);
    }

    @BeanField(isUnique = true)
    private String username;
    @BeanField
    private String password;
    @BeanField
    private String fullName;
    @BeanField
    private Gender gender;
    @BeanField
    private Date dateOfBirth;
    @BeanField
    private String email;
    @BeanField
    private String phone;
    @BeanField
    private String address;
    @BeanField
    private String avatarUrl;
    @BeanField
    private String note;
    @BeanField
    private Date createdDate;
    @BeanField
    private Date modifiedDate;

    public User(String username, String password, String fullName, Gender gender, Date dateOfBirth, String email, String phone, String address, String avatarUrl, String note, Date createdDate, Date modifiedDate) {

        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.avatarUrl = avatarUrl;
        this.note = note;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
