package com.solvo.hoam.view;

import com.solvo.hoam.model.response.Category;
import com.solvo.hoam.model.response.LocationResponse;

import java.util.List;

public interface FilterView {
    void setUpViews(List<LocationResponse> locations, List<Category> categories);

    void setViewPositions(int location, int parentCategory);

    void setUpPrices(String priceFrom, String priceTo);

    void showSubCategory(boolean show);

    void updateSubCategories(List<Category> categories, int position);

    void close();
}
