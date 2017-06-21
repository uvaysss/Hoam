package com.solvo.hoam.domain.interactor;

import com.solvo.hoam.data.db.Filter;
import com.solvo.hoam.data.db.model.CategoryModel;
import com.solvo.hoam.data.db.model.LocationModel;
import com.solvo.hoam.data.repository.CategoryRepository;
import com.solvo.hoam.data.repository.LocationRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FilterInteractor {

    private Filter filter;
    private LocationRepository locationRepository;
    private CategoryRepository categoryRepository;

    @Inject
    public FilterInteractor(Filter filter,
                            LocationRepository locationRepository,
                            CategoryRepository categoryRepository) {
        this.filter = filter;
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
    }

    public Single<List<LocationModel>> getLocations() {
        return locationRepository.getLocations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<CategoryModel>> getCategories() {
        return categoryRepository.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
