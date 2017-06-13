package com.solvo.hoam.data.db.model;

public class ImageModel {

    private String id;
    private String adId;
    private int isMain;
    private String big;
    private String small;
    private String createdAt;
    private String updatedAt;

    public ImageModel() {
    }

    public ImageModel(String id, String adId, int isMain, String big, String small, String createdAt,
                      String updatedAt) {
        this.id = id;
        this.adId = adId;
        this.isMain = isMain;
        this.big = big;
        this.small = small;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public int getIsMain() {
        return isMain;
    }

    public void setIsMain(int isMain) {
        this.isMain = isMain;
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
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
