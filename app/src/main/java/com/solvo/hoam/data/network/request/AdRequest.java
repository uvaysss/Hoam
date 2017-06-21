package com.solvo.hoam.data.network.request;

public class AdRequest {

    private int page;
    private String query;
    private String locationId;
    private String categoryId;
    private String priceFrom;
    private String priceTo;

    public AdRequest(int page, String query, String locationId, String categoryId, String priceFrom, String priceTo) {
        this.page = page;
        this.query = query;
        this.locationId = locationId;
        this.categoryId = categoryId;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
    }

    public String getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(String priceTo) {
        this.priceTo = priceTo;
    }
}
