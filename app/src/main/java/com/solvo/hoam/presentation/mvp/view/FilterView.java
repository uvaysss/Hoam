package com.solvo.hoam.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.solvo.hoam.data.db.model.CategoryModel;
import com.solvo.hoam.data.db.model.LocationModel;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface FilterView extends MvpView {
    void setLocations(List<LocationModel> locations, int locationPosition);
    void setCategories(List<CategoryModel> categories, int categoryPosition);
    void setUpPrices(String priceFrom, String priceTo);
    void setViewPositions(int location, int category);
    void close();
}
