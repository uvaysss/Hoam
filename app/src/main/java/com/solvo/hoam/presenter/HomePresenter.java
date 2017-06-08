package com.solvo.hoam.presenter;

import android.content.Context;
import android.util.Log;

import com.solvo.hoam.helper.AdHelper;
import com.solvo.hoam.model.DataManager;
import com.solvo.hoam.model.response.Ad;
import com.solvo.hoam.view.HomeView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class HomePresenter {
    private static final String TAG = HomePresenter.class.getSimpleName();
    private CompositeDisposable mCompositeDisposable;
    private DataManager mDataManager;
    private HomeView mView;
    private Context mContext;
    private String mQuery;

    public HomePresenter(HomeView view, Context context) {
        mView = view;
        mContext = context;
        mCompositeDisposable = new CompositeDisposable();
        mDataManager = new DataManager();
    }

    private void fetchAds(final int page) {
        mCompositeDisposable.add(mDataManager.getAds(page, mQuery)
                .subscribe(response -> {
                    List<Ad> adList = response.getAdList();

                    if (page == 0) {
                        mView.setAds(adList, AdHelper.getTotalAds(response.getTotal(), mContext.getResources()));
                        mDataManager.saveAdList(adList);
                    } else {
                        mView.updateAds(adList);
                        mDataManager.updateAdList(adList);
                    }

                    mView.showLoading(false);
                }, throwable -> {
                    Log.i(TAG, throwable.toString());
                    mView.showError();
                    mView.showLoading(false);
                }));
    }

    public void init() {
        mView.showLoading(true);
        fetchAds(0);
    }

    public void onListScroll(int page) {
        mView.showLoading(true);
        fetchAds(page);
    }

    public void onRefresh() {
        mQuery = null;
        fetchAds(0);
    }

    public void onSearch(String query) {
        mView.showLoading(true);
        mQuery = query;
        fetchAds(0);
    }

    public void onStop() {
        mCompositeDisposable.clear();
        mView.showLoading(false);
    }

    public void onFilterClicked() {
        mView.goToFilter();
    }

    public void onAboutAppClicked() {
        mView.goToAboutApp();
    }

    public void onActivityResult() {
        init();
    }
}
