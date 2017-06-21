package com.solvo.hoam.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.solvo.hoam.domain.model.AdEntity;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface AdView extends MvpView {

    void setUpViews(AdEntity ad);
    void hideImageLayout();
    void hidePageIndicator();
    void showLoading(boolean show);
    void showError();
    void showContent();
}
