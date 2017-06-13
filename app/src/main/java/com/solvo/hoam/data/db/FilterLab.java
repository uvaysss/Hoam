package com.solvo.hoam.data.db;

public class FilterLab {
    private static FilterLab filterLab = new FilterLab();
    private int locationSpinnerPosition = 0;
    private int parentCategorySpinnerPosition = 0;
    private int subCategorySpinnerPosition = 0;
    private long priceFrom = 0;
    private long priceTo = 0;

    public static FilterLab getInstance() {
        return filterLab;
    }

    private FilterLab() {
    }

    public void setFilters(int locationSpinnerPosition,
                           int parentCategorySpinnerPosition,
                           int subCategorySpinnerPosition,
                           String priceFrom,
                           String priceTo) {

        this.locationSpinnerPosition = locationSpinnerPosition;
        this.parentCategorySpinnerPosition = parentCategorySpinnerPosition;
        this.subCategorySpinnerPosition = subCategorySpinnerPosition;

        if (!priceFrom.equals("")) {
            this.priceFrom = Long.valueOf(priceFrom);
        }
        if (!priceTo.equals("")) {
            this.priceTo = Long.valueOf(priceTo);
        }
    }

    public void resetFilters() {
        locationSpinnerPosition = 0;
        parentCategorySpinnerPosition = 0;
        subCategorySpinnerPosition = 0;
        priceFrom = 0;
        priceTo = 0;
    }

    public int getLocationSpinnerPosition() {
        return locationSpinnerPosition;
    }

    public int getParentCategorySpinnerPosition() {
        return parentCategorySpinnerPosition;
    }

    public int getSubCategorySpinnerPosition() {
        return subCategorySpinnerPosition;
    }

    public long getPriceFrom() {
        return priceFrom;
    }

    public long getPriceTo() {
        return priceTo;
    }

    public void setSubCategorySpinnerPosition(int subCategorySpinnerPosition) {
        this.subCategorySpinnerPosition = subCategorySpinnerPosition;
    }
}
