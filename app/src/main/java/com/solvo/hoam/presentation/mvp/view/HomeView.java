package com.solvo.hoam.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.solvo.hoam.data.network.response.Ad;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface HomeView extends MvpView {

    void showLoading(boolean show);
    void setAds(List<Ad> adList, String total);
    void updateAds(List<Ad> adList);
    void showError();
    void goToFilter();
}
