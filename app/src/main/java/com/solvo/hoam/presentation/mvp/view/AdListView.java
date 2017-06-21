package com.solvo.hoam.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.solvo.hoam.domain.model.AdEntity;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface AdListView extends MvpView {

    void showLoading(boolean show);
//    void setAds(List<Ad> adList, String total);
    void setAds(List<AdEntity> adList);
    void updateAds(List<AdEntity> adList);
    void showError();
    void goToFilter();
}
