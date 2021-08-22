package com.solvo.hoam.domain.interactor;

import com.solvo.hoam.data.repository.CategoryRepositoryLegacy;
import com.solvo.hoam.data.repository.LocationRepositoryLegacy;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashInteractor {

    private LocationRepositoryLegacy locationRepositoryLegacy;
    private CategoryRepositoryLegacy categoryRepositoryLegacy;

    @Inject
    public SplashInteractor(LocationRepositoryLegacy locationRepositoryLegacy, CategoryRepositoryLegacy categoryRepositoryLegacy) {
        this.locationRepositoryLegacy = locationRepositoryLegacy;
        this.categoryRepositoryLegacy = categoryRepositoryLegacy;
    }

    public Completable fetchData() {
        return locationRepositoryLegacy.fetchLocations().mergeWith(categoryRepositoryLegacy.fetchCategories())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
