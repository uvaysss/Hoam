package com.solvo.hoam.presentation.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.solvo.hoam.di.ApplicationComponent;
import com.solvo.hoam.domain.interactor.FavoritesInteractor;
import com.solvo.hoam.presentation.mvp.view.FavoritesView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class FavoritesPresenter extends MvpPresenter<FavoritesView> {

    private static final String TAG = FavoritesPresenter.class.getSimpleName();

    private CompositeDisposable compositeDisposable;

    @Inject
    FavoritesInteractor interactor;

    public FavoritesPresenter(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);

        this.compositeDisposable = new CompositeDisposable();
    }

    public void init() {
        compositeDisposable.add(interactor.getFavoriteAds()
                .subscribe(ads -> {
                    getViewState().setAdList(ads);
                }, throwable -> {
                    Log.e(TAG, throwable.toString());
                }));
    }
}
