package com.solvo.hoam.di;

import android.content.Context;

import com.solvo.hoam.di.module.ContextModule;
import com.solvo.hoam.di.module.ApplicationModule;
import com.solvo.hoam.presentation.mvp.presenter.AdPresenter;
import com.solvo.hoam.presentation.mvp.presenter.FilterPresenter;
import com.solvo.hoam.presentation.mvp.presenter.HomePresenter;
import com.solvo.hoam.presentation.mvp.presenter.SplashPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, ApplicationModule.class})
public interface ApplicationComponent {

    Context getContext();

    void inject(AdPresenter presenter);
    void inject(FilterPresenter presenter);
    void inject(HomePresenter presenter);
    void inject(SplashPresenter presenter);
}
