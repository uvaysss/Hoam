package com.solvo.hoam.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.solvo.hoam.data.network.response.Category;
import com.solvo.hoam.data.db.CategoryLab;
import com.solvo.hoam.data.db.FilterLab;
import com.solvo.hoam.data.db.LocationLab;
import com.solvo.hoam.di.ApplicationComponent;
import com.solvo.hoam.presentation.mvp.view.FilterView;

import java.util.List;

@InjectViewState
public class FilterPresenter extends MvpPresenter<FilterView> {

    private FilterLab filterLab;
    private CategoryLab categoryLab;
    private List<Category> parentCategoryList;

    public FilterPresenter(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);

        filterLab = FilterLab.getInstance();
        categoryLab = CategoryLab.getInstance();
        parentCategoryList = CategoryLab.getInstance().getParentCategories();
    }

    public void init() {
        getViewState().setUpViews(LocationLab.getInstance().getLocations(), parentCategoryList);

        int parentCategoryPosition = filterLab.getParentCategorySpinnerPosition();
        getViewState().setViewPositions(filterLab.getLocationSpinnerPosition(), parentCategoryPosition);

        onParentCategoryItemClicked(parentCategoryPosition);

        String priceFrom = "";
        long price = filterLab.getPriceFrom();
        if (price != 0) {
            priceFrom = String.valueOf(price);
        }

        String priceTo = "";
        price = filterLab.getPriceTo();
        if (price != 0) {
            priceTo = String.valueOf(price);
        }

        getViewState().setUpPrices(priceFrom, priceTo);
    }

    public void onParentCategoryItemClicked(int position) {
        if (position != 0) {
            String parentId = parentCategoryList.get(position).getId();
            getViewState().updateSubCategories(categoryLab.getSubCategories(parentId), filterLab.getSubCategorySpinnerPosition());
            getViewState().showSubCategory(true);
        } else {
            getViewState().showSubCategory(false);
            filterLab.setSubCategorySpinnerPosition(0);
        }
    }

    public void onSaveClicked(int location, int parentCategory, int subCategory, String priceFrom, String priceTo) {
        filterLab.setFilters(location, parentCategory, subCategory, priceFrom, priceTo);
        getViewState().close();
    }

    public void onResetClicked() {
        filterLab.resetFilters();
        getViewState().setViewPositions(0, 0);
        getViewState().setUpPrices("", "");
    }
}
