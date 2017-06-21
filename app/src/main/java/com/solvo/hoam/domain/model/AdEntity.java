package com.solvo.hoam.domain.model;

import com.solvo.hoam.data.network.response.Image;

import java.util.List;

public class AdEntity {

    private String id;
    private String title;
    private String text;
    private long price;
    private String phone;
    private int views;
    private String authorId;
    private String authorName;
    private String category;
    private String location;
    private String createdAt;
    private String updatedAt;
    private List<Image> imageList;

    public AdEntity(String id, String title, String text, long price, String phone, int views,
                    String authorId, String authorName, String category, String location,
                    String createdAt, String updatedAt, List<Image> imageList) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.price = price;
        this.phone = phone;
        this.views = views;
        this.authorId = authorId;
        this.authorName = authorName;
        this.category = category;
        this.location = location;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.imageList = imageList;
    }

    public AdEntity(String id, String title, String text, long price, String phone, int views,
                    String authorId, String authorName, String createdAt, String updatedAt,
                    List<Image> imageList) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.price = price;
        this.phone = phone;
        this.views = views;
        this.authorId = authorId;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.imageList = imageList;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}
