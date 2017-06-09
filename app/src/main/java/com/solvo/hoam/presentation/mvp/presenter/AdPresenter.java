package com.solvo.hoam.presentation.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.solvo.hoam.data.db.CategoryLab;
import com.solvo.hoam.data.db.LocationLab;
import com.solvo.hoam.data.network.response.Ad;
import com.solvo.hoam.di.ApplicationComponent;
import com.solvo.hoam.domain.DataManager;
import com.solvo.hoam.presentation.mvp.view.AdView;
import com.solvo.hoam.presentation.ui.helper.AdHelper;

import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class AdPresenter extends MvpPresenter<AdView> {

    private static final String TAG = AdPresenter.class.getSimpleName();
    private CompositeDisposable compositeDisposable;
    private DataManager dataManager;
    private Context context;
    private String adId;

    public AdPresenter(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);

        context = applicationComponent.getContext();
        compositeDisposable = new CompositeDisposable();
        dataManager = new DataManager();
    }

    public void init(String adId) {
        this.adId = adId;
        getViewState().showLoading(true);
        fetchAd();
    }

    public void fetchAd() {
        compositeDisposable.add(dataManager.getAd(adId)
                .subscribe(ad -> {
                    initViews(ad);
                    getViewState().showLoading(false);
                }, throwable -> {
                    getViewState().showError();
                    getViewState().showLoading(false);
                    Log.e(TAG, throwable.toString());
                }));
    }

    private void initViews(Ad ad) {
        if (ad.getImages().isEmpty()) {
            getViewState().hideViewPager();
        } else if (ad.getImages().size() == 1) {
            getViewState().hidePageIndicator();
        }

        getViewState().setUpViews(ad.getTitle(),
                AdHelper.getPrice(ad.getPrice()),
                ad.getAuthorName(),
                CategoryLab.getInstance().getCategory(ad.getCategoryId()),
                LocationLab.getInstance().getLocation(ad.getCityId()),
                AdHelper.getAdCreatedDate(ad.getCreatedAt(), false),
                ad.getText(),
                AdHelper.getViews(ad.getViews(), context.getResources()),
                ad.getPhone(),
                ad.getId());

        getViewState().showContent();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
        getViewState().showLoading(false);
    }
}
