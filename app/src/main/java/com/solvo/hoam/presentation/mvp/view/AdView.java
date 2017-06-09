package com.solvo.hoam.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface AdView extends MvpView {

    void setUpViews(String title, String price, String username, String category, String location,
                    String createdDate, String description, String views, String phone, String adId);
    void hideViewPager();
    void hidePageIndicator();
    void showLoading(boolean show);
    void showError();
    void showContent();
}
