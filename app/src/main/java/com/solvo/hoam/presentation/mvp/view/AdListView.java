package com.solvo.hoam.presentation.mvp.view;

import com.solvo.hoam.domain.model.AdEntity;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface AdListView extends MvpView {

    void showLoading(boolean show);
//    void setAds(List<Ad> adList, String total);
    void setAds(List<AdEntity> adList);
    void updateAds(List<AdEntity> adList);
    void showError();
    void goToFilter();
}
