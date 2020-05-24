package com.chenshop.model.bean;

public final class Feedback {
    private static final long serialVersionUID = 1L;


    private String fullName;
    private String content;
    private float rating;
    private String dateComment;

    public Feedback(String fullName, String content, float rating, String dateComment) {
        this.fullName = fullName;
        this.content = content;
        this.rating = rating;
        this.dateComment = dateComment;
    }

    public String getDateComment() {
        return dateComment;
    }

    public void setDateComment(String dateComment) {
        this.dateComment = dateComment;
    }

    public Feedback() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

}
