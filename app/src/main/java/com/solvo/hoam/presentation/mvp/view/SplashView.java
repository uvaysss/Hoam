package com.solvo.hoam.presentation.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SplashView extends MvpView {
    void showError(boolean show);
    void navigateToMainScreen();
}
