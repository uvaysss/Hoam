package com.solvo.hoam.presentation.mvp.view;

import com.solvo.hoam.domain.model.AdEntity;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface FavoritesView extends MvpView {
    void setAdList(List<AdEntity> ads);
}
