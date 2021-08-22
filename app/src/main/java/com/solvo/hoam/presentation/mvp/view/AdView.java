package com.solvo.hoam.presentation.mvp.view;


import com.solvo.hoam.domain.model.AdEntity;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface AdView extends MvpView {

    void inflateMenu();
    void setUpViews(AdEntity ad);
    void hideImageLayout();
    void hidePageIndicator();
    void showLoading(boolean show);
    void showError();
    void showContent();
    void setIsFavorite(boolean isFavorite);
    void showToast(String success);
    void showFavoriteSuccess(boolean isFavorite);
}
