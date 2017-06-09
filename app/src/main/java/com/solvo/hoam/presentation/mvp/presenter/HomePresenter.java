package com.solvo.hoam.presentation.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.solvo.hoam.data.network.response.Ad;
import com.solvo.hoam.di.ApplicationComponent;
import com.solvo.hoam.domain.DataManager;
import com.solvo.hoam.presentation.mvp.view.HomeView;
import com.solvo.hoam.presentation.ui.helper.AdHelper;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> {

    private static final String TAG = HomePresenter.class.getSimpleName();
    private CompositeDisposable compositeDisposable;
    private DataManager dataManager;
    private Context context;
    private String query;

    public HomePresenter(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);

        context = applicationComponent.getContext();
        compositeDisposable = new CompositeDisposable();
        dataManager = new DataManager();
    }

    private void fetchAds(final int page) {
        compositeDisposable.add(dataManager.getAds(page, query)
                .subscribe(response -> {
                    List<Ad> adList = response.getAdList();

                    if (page == 0) {
                        getViewState().setAds(adList, AdHelper.getTotalAds(response.getTotal(), context.getResources()));
                        dataManager.saveAdList(adList);
                    } else {
                        getViewState().updateAds(adList);
                        dataManager.updateAdList(adList);
                    }

                    getViewState().showLoading(false);
                }, throwable -> {
                    Log.i(TAG, throwable.toString());
                    getViewState().showError();
                    getViewState().showLoading(false);
                }));
    }

    public void init() {
        getViewState().showLoading(true);
        fetchAds(0);
    }

    public void onListScroll(int page) {
        getViewState().showLoading(true);
        fetchAds(page);
    }

    public void onRefresh() {
        query = null;
        fetchAds(0);
    }

    public void onSearch(String query) {
        getViewState().showLoading(true);
        this.query = query;
        fetchAds(0);
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
