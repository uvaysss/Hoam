package com.solvo.hoam.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.solvo.hoam.data.network.response.Category;
import com.solvo.hoam.data.network.response.LocationResponse;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface FilterView extends MvpView {

    void setUpViews(List<LocationResponse> locations, List<Category> categories);
    void setViewPositions(int location, int parentCategory);
    void setUpPrices(String priceFrom, String priceTo);
    void showSubCategory(boolean show);
    void updateSubCategories(List<Category> categories, int position);
    void close();
}
