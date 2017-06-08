package com.solvo.hoam.presenter;

import com.solvo.hoam.model.DataManager;
import com.solvo.hoam.view.SplashView;

import io.reactivex.disposables.CompositeDisposable;

public class SplashPresenter {
    private static final String TAG = SplashPresenter.class.getSimpleName();
    private CompositeDisposable mCompositeDisposable;
    private DataManager mDataManager;
    private SplashView mView;

    public SplashPresenter(SplashView view) {
        mCompositeDisposable = new CompositeDisposable();
        mDataManager = new DataManager();
        mView = view;
    }

    public void init() {
        mView.showLoading(true);
        fetchCategories();
    }

    public void fetchCategories() {
        mCompositeDisposable.add(mDataManager.getCategories()
                .subscribe(response -> {
                    mDataManager.saveCategoryList(response);
                    fetchLocations();
                }, throwable -> {
                    mView.showError();
                    mView.showLoading(false);
                }));
    }

    private void fetchLocations() {
        mCompositeDisposable.add(mDataManager.getLocations()
                .subscribe(response -> {
                    mDataManager.saveLocationList(response);
                    mView.goToMainScreen();
                }, throwable -> {
                    mView.showError();
                    mView.showLoading(false);
                })
        );
    }

    public void onStop() {
        mCompositeDisposable.clear();
    }
}
