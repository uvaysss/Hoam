package com.solvo.hoam.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ad {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("price")
    @Expose
    private int price;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("views")
    @Expose
    private int views;

    @SerializedName("author_id")
    @Expose
    private String authorId;

    @SerializedName("author_name")
    @Expose
    private String authorName;

    @SerializedName("category_id")
    @Expose
    private String categoryId;

    @SerializedName("is_premium")
    @Expose
    private int isPremium;

    @SerializedName("city_id")
    @Expose
    private String cityId;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("is_free")
    @Expose
    private int isFree;

    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public Ad(String id, String title, String text, int price, String phone, int views, String authorId, String authorName, String categoryId, int isPremium, String cityId, String createdAt, String updatedAt, int isFree, List<Image> images) {
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
        this.images = images;
    }

    public Ad() {
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

}
