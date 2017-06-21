package com.solvo.hoam.presentation.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.solvo.hoam.data.db.Filter;
import com.solvo.hoam.data.db.model.CategoryModel;
import com.solvo.hoam.data.db.model.LocationModel;
import com.solvo.hoam.di.ApplicationComponent;
import com.solvo.hoam.domain.interactor.FilterInteractor;
import com.solvo.hoam.presentation.mvp.view.FilterView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class FilterPresenter extends MvpPresenter<FilterView> {

    private static final String TAG = FilterPresenter.class.getSimpleName();

    private CompositeDisposable compositeDisposable;

    @Inject
    FilterInteractor interactor;

    @Inject
    Filter filter;

    public FilterPresenter(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);

        this.compositeDisposable = new CompositeDisposable();
    }

    public void init() {
        compositeDisposable.add(interactor.getLocations()
                .subscribe(locations -> {
                    getViewState().setLocations(locations, filter.getLocationPosition());
                }, throwable -> {
                    Log.e(TAG, throwable.toString());
                }));

        compositeDisposable.add(interactor.getCategories()
                .subscribe(categories -> {
                    getViewState().setCategories(categories, filter.getCategoryPosition());
                }, throwable -> {
                    Log.e(TAG, throwable.toString());
                }));

        String priceFrom = "";
        long price = filter.getPriceFrom();
        if (price != 0) {
            priceFrom = String.valueOf(price);
        }

        String priceTo = "";
        price = filter.getPriceTo();
        if (price != 0) {
            priceTo = String.valueOf(price);
        }

        getViewState().setUpPrices(priceFrom, priceTo);
    }

    public void saveFilterInfo(LocationModel location, CategoryModel category,
                               int locationPosition, int categoryPosition,
                               String priceFrom, String priceTo) {
        filter.setFilters(
                location != null ? location.getId() : null,
                category != null ? category.getId() : null,
                locationPosition,
                categoryPosition,
                priceFrom,
                priceTo);
        getViewState().close();
    }

    public void resetFilterInfo() {
        filter.resetFilters();
        getViewState().setViewPositions(0, 0);
        getViewState().setUpPrices("", "");
    }
}
