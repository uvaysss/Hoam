package com.solvo.hoam.data.db;

public class FilterLab {
    private static FilterLab sFilterLab = new FilterLab();
    private int mLocationSpinnerPosition = 0;
    private int mParentCategorySpinnerPosition = 0;
    private int mSubCategorySpinnerPosition = 0;
    private int mPriceFrom = 0;
    private int mPriceTo = 0;

    public static FilterLab getInstance() {
        return sFilterLab;
    }

    private FilterLab() {

    }

    public void setFilters(int locationSpinnerPosition,
                           int parentCategorySpinnerPosition,
                           int subCategorySpinnerPosition,
                           String priceFrom,
                           String priceTo) {

        mLocationSpinnerPosition = locationSpinnerPosition;
        mParentCategorySpinnerPosition = parentCategorySpinnerPosition;
        mSubCategorySpinnerPosition = subCategorySpinnerPosition;

        if (!priceFrom.equals("")) {
            mPriceFrom = Integer.valueOf(priceFrom);
        }
        if (!priceTo.equals("")) {
            mPriceTo = Integer.valueOf(priceTo);
        }
    }

    public void resetFilters() {
        mLocationSpinnerPosition = 0;
        mParentCategorySpinnerPosition = 0;
        mSubCategorySpinnerPosition = 0;
        mPriceFrom = 0;
        mPriceTo = 0;
    }

    public int getLocationSpinnerPosition() {
        return mLocationSpinnerPosition;
    }

    public int getParentCategorySpinnerPosition() {
        return mParentCategorySpinnerPosition;
    }

    public int getSubCategorySpinnerPosition() {
        return mSubCategorySpinnerPosition;
    }

    public int getPriceFrom() {
        return mPriceFrom;
    }

    public int getPriceTo() {
        return mPriceTo;
    }

    public void setSubCategorySpinnerPosition(int subCategorySpinnerPosition) {
        mSubCategorySpinnerPosition = subCategorySpinnerPosition;
    }
}
