package com.solvo.hoam.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.solvo.hoam.di.ApplicationComponent;
import com.solvo.hoam.domain.DataManager;
import com.solvo.hoam.presentation.mvp.view.SplashView;

import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class SplashPresenter extends MvpPresenter<SplashView> {

    private static final String TAG = SplashPresenter.class.getSimpleName();
    private CompositeDisposable compositeDisposable;
    private DataManager dataManager;

    public SplashPresenter(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);

        compositeDisposable = new CompositeDisposable();
        dataManager = new DataManager();
    }

    public void init() {
        getViewState().showLoading(true);
        fetchCategories();
    }

    public void fetchCategories() {
        compositeDisposable.add(dataManager.getCategories()
                .subscribe(response -> {
                    dataManager.saveCategoryList(response);
                    fetchLocations();
                }, throwable -> {
                    getViewState().showError();
                    getViewState().showLoading(false);
                }));
    }

    private void fetchLocations() {
        compositeDisposable.add(dataManager.getLocations()
                .subscribe(response -> {
                    dataManager.saveLocationList(response);
                    getViewState().goToMainScreen();
                }, throwable -> {
                    getViewState().showError();
                    getViewState().showLoading(false);
                })
        );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
