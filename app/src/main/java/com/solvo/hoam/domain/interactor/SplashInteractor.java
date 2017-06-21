package com.solvo.hoam.domain.interactor;

import com.solvo.hoam.data.repository.CategoryRepository;
import com.solvo.hoam.data.repository.LocationRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashInteractor {

    private LocationRepository locationRepository;
    private CategoryRepository categoryRepository;

    @Inject
    public SplashInteractor(LocationRepository locationRepository, CategoryRepository categoryRepository) {
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
    }

    public Completable fetchData() {
        return locationRepository.fetchLocations().mergeWith(categoryRepository.fetchCategories())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
