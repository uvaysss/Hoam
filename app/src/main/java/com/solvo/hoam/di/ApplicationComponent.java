package com.solvo.hoam.di;

import android.content.Context;

import com.solvo.hoam.di.module.ContextModule;
import com.solvo.hoam.di.module.DataModule;
import com.solvo.hoam.di.module.NetworkModule;
import com.solvo.hoam.presentation.AppActivity;
import com.solvo.hoam.presentation.launch.LaunchFragment;
import com.solvo.hoam.presentation.main.MainFlowFragment;
import com.solvo.hoam.presentation.mvp.presenter.AdListPresenter;
import com.solvo.hoam.presentation.mvp.presenter.AdPresenter;
import com.solvo.hoam.presentation.mvp.presenter.FavoritesPresenter;
import com.solvo.hoam.presentation.mvp.presenter.FilterPresenter;
import com.solvo.hoam.presentation.mvp.presenter.SplashPresenter;
import com.solvo.hoam.presentation.product.ProductFragment;
import com.solvo.hoam.presentation.productlist.ProductListFragment;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, DataModule.class, NetworkModule.class})
public interface

ApplicationComponent {
    void inject(@NotNull AppActivity appActivity);
    void inject(@NotNull MainFlowFragment mainFlowFragment);

    Context getContext();

    void inject(AdPresenter presenter);
    void inject(FilterPresenter presenter);
    void inject(AdListPresenter presenter);
    void inject(SplashPresenter presenter);
    void inject(FavoritesPresenter presenter);

    void inject(LaunchFragment launchFragment);
    void inject(ProductListFragment fragment);
    void inject(ProductFragment productFragment);
}
