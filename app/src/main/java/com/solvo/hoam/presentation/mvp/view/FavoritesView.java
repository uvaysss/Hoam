package com.solvo.hoam.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.solvo.hoam.domain.model.AdEntity;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface FavoritesView extends MvpView {
    void setAdList(List<AdEntity> ads);
}
