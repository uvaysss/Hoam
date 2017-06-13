package com.solvo.hoam.data.db.model;

public class AdModel {

    private String id;
    private String title;
    private String text;
    private int price;
    private String phone;
    private int views;
    private String authorId;
    private String authorName;
    private String categoryId;
    private int isPremium;
    private String cityId;
    private String createdAt;
    private String updatedAt;
    private int isFree;

    public AdModel() {
    }

    public AdModel(String id, String title, String text, int price, String phone, int views,
                   String authorId, String authorName, String categoryId, int isPremium,
                   String cityId, String createdAt, String updatedAt, int isFree) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.price = price;
        this.phone = phone;
        this.views = views;
        this.authorId = authorId;
        this.authorName = authorName;
        this.categoryId = categoryId;
        this.isPremium = isPremium;
        this.cityId = cityId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isFree = isFree;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getIsPremium() {
        return isPremium;
    }

    public void setIsPremium(int isPremium) {
        this.isPremium = isPremium;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getIsFree() {
        return isFree;
    }

    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }
}
