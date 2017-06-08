package com.solvo.hoam.presenter;

import com.solvo.hoam.model.response.Category;
import com.solvo.hoam.model.data.CategoryLab;
import com.solvo.hoam.model.data.FilterLab;
import com.solvo.hoam.model.data.LocationLab;
import com.solvo.hoam.view.FilterView;

import java.util.List;

public class FilterPresenter {
    private FilterView mView;
    private FilterLab mFilterLab;
    private CategoryLab mCategoryLab;
    private List<Category> mParentCategoryList;

    public FilterPresenter(FilterView view) {
        mView = view;
        mFilterLab = FilterLab.getInstance();
        mCategoryLab = CategoryLab.getInstance();
        mParentCategoryList = CategoryLab.getInstance().getParentCategories();
    }

    public void init() {
        mView.setUpViews(LocationLab.getInstance().getLocations(), mParentCategoryList);

        int parentCategoryPosition = mFilterLab.getParentCategorySpinnerPosition();
        mView.setViewPositions(mFilterLab.getLocationSpinnerPosition(), parentCategoryPosition);

        onParentCategoryItemClicked(parentCategoryPosition);

        String priceFrom = "";
        int price = mFilterLab.getPriceFrom();
        if (price != 0) {
            priceFrom = String.valueOf(price);
        }

        String priceTo = "";
        price = mFilterLab.getPriceTo();
        if (price != 0) {
            priceTo = String.valueOf(price);
        }

        mView.setUpPrices(priceFrom, priceTo);
    }

    public void onParentCategoryItemClicked(int position) {
        if (position != 0) {
            String parentId = mParentCategoryList.get(position).getId();
            mView.updateSubCategories(mCategoryLab.getSubCategories(parentId), mFilterLab.getSubCategorySpinnerPosition());
            mView.showSubCategory(true);
        } else {
            mView.showSubCategory(false);
            mFilterLab.setSubCategorySpinnerPosition(0);
        }
    }

    public void onSaveClicked(int location, int parentCategory, int subCategory, String priceFrom, String priceTo) {
        mFilterLab.setFilters(location, parentCategory, subCategory, priceFrom, priceTo);
        mView.close();
    }

    public void onResetClicked() {
        mFilterLab.resetFilters();
        mView.setViewPositions(0, 0);
        mView.setUpPrices("", "");
    }
}
