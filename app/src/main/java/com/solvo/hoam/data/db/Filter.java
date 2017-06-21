package com.solvo.hoam.data.db;

public class Filter {
    private String locationId;
    private String categoryId;
    private int locationPosition;
    private int categoryPosition;
    private long priceFrom;
    private long priceTo;

    public Filter() {
    }

    public void setFilters(String locationId, String categoryId,
                           int locationPosition, int categoryPosition,
                           String priceFrom, String priceTo) {
        this.locationId = locationId;
        this.categoryId = categoryId;
        this.locationPosition = locationPosition;
        this.categoryPosition = categoryPosition;
        this.priceFrom = priceFrom.equals("") ? 0 : Long.valueOf(priceFrom);
        this.priceTo = priceTo.equals("") ? 0 : Long.valueOf(priceTo);
    }

    public void resetFilters() {
        this.locationId = null;
        this.categoryId = null;
        this.locationPosition = 0;
        this.categoryPosition = 0;
        this.priceFrom = 0;
        this.priceTo = 0;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public int getLocationPosition() {
        return locationPosition;
    }

    public int getCategoryPosition() {
        return categoryPosition;
    }

    public long getPriceFrom() {
        return priceFrom;
    }

    public String getPriceFromString() {
        return priceFrom != 0 ? String.valueOf(priceFrom) : null;
    }

    public long getPriceTo() {
        return priceTo;
    }

    public String getPriceToString() {
        return priceTo != 0 ? String.valueOf(priceTo) : null;
    }
}
