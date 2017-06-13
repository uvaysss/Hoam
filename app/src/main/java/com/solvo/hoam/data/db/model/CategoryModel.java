package com.solvo.hoam.data.db.model;

public class CategoryModel {

    private String id;
    private String name;
    private String slug;
    private String parentId;
    private int priority;
    private String createdAt;
    private String updatedAt;

    public CategoryModel() {
    }

    public CategoryModel(String id, String name, String slug, String parentId, int priority,
                         String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.parentId = parentId;
        this.priority = priority;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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
}
