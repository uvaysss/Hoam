package com.solvo.hoam.presenter;

import android.content.Context;

import com.solvo.hoam.helper.AdHelper;
import com.solvo.hoam.model.DataManager;
import com.solvo.hoam.model.data.CategoryLab;
import com.solvo.hoam.model.data.LocationLab;
import com.solvo.hoam.model.response.Ad;
import com.solvo.hoam.view.AdView;

import io.reactivex.disposables.CompositeDisposable;

public class AdPresenter {
    private CompositeDisposable mCompositeDisposable;
    private DataManager mDataManager;
    private String mAdId;
    private AdView mView;

    public AdPresenter(AdView view) {
        mView = view;
        mCompositeDisposable = new CompositeDisposable();
        mDataManager = new DataManager();
    }

    public void init(String adId) {
        mAdId = adId;
        mView.showLoading(true);
        fetchAd();
    }

    public void fetchAd() {
        mCompositeDisposable.add(mDataManager.getAd(mAdId)
                .subscribe(ad -> {
                    initViews(ad);
                    mView.showLoading(false);
                }, throwable -> {
                    mView.showError();
                    mView.showLoading(false);
                }));
    }

    private void initViews(Ad ad) {
        if (ad.getImages().isEmpty()) {
            mView.hideViewPager();
        } else if (ad.getImages().size() == 1) {
            mView.hidePageIndicator();
        }

        mView.setUpViews(ad.getTitle(),
                AdHelper.getPrice(ad.getPrice()),
                ad.getAuthorName(),
                CategoryLab.getInstance().getCategory(ad.getCategoryId()),
                LocationLab.getInstance().getLocation(ad.getCityId()),
                AdHelper.getAdCreatedDate(ad.getCreatedAt(), false),
                ad.getText(),
                AdHelper.getViews(ad.getViews(), ((Context) mView).getResources()),
                ad.getPhone(),
                ad.getId());

        mView.showContent();
    }

    public void onStop() {
        mCompositeDisposable.clear();
        mView.showLoading(false);
    }
}
