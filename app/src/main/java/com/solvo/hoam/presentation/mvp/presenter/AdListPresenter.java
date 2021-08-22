package com.solvo.hoam.presentation.mvp.presenter;

import android.util.Log;

import com.solvo.hoam.di.ApplicationComponent;
import com.solvo.hoam.domain.interactor.AdListInteractor;
import com.solvo.hoam.domain.model.AdEntity;
import com.solvo.hoam.presentation.mvp.view.AdListView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class AdListPresenter extends MvpPresenter<AdListView> {

    private static final String TAG = AdListPresenter.class.getSimpleName();

    private CompositeDisposable compositeDisposable;
    private String query;

    @Inject
    AdListInteractor interactor;

    public AdListPresenter(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);

        compositeDisposable = new CompositeDisposable();
    }

    private void fetchData(final int page) {
        compositeDisposable.add(interactor.getAds(page, query)
                .subscribe(adList -> {
                    handleSuccess(page, adList);
                }, throwable -> {
                    handleError(throwable);
                }));
    }

    private void handleSuccess(int page, List<AdEntity> adList) {
        if (page == 0) {
            getViewState().setAds(adList);
        } else {
            getViewState().updateAds(adList);
        }
        getViewState().showLoading(false);
    }

    private void handleError(Throwable throwable) {
        Log.e(TAG, throwable.toString());
        getViewState().showError();
        getViewState().showLoading(false);
    }

    public void init() {
        getViewState().showLoading(true);
        fetchData(0);
    }

    public void onListScroll(int page) {
        getViewState().showLoading(true);
        fetchData(page);
    }

    public void onRefresh() {
        query = null;
        fetchData(0);
    }

    public void onSearch(String query) {
        getViewState().showLoading(true);
        this.query = query;
        fetchData(0);
    }

    public void onFilterClicked() {
        getViewState().goToFilter();
    }

    public void onActivityResult() {
        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
        getViewState().showLoading(false);
    }
}
