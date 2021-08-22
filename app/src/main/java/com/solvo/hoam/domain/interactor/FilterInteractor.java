package com.solvo.hoam.domain.interactor;

import com.solvo.hoam.data.db.Filter;
import com.solvo.hoam.data.db.model.CategoryModel;
import com.solvo.hoam.data.db.model.LocationModel;
import com.solvo.hoam.data.repository.CategoryRepositoryLegacy;
import com.solvo.hoam.data.repository.LocationRepositoryLegacy;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FilterInteractor {

    private Filter filter;
    private LocationRepositoryLegacy locationRepositoryLegacy;
    private CategoryRepositoryLegacy categoryRepositoryLegacy;

    @Inject
    public FilterInteractor(Filter filter,
                            LocationRepositoryLegacy locationRepositoryLegacy,
                            CategoryRepositoryLegacy categoryRepositoryLegacy) {
        this.filter = filter;
        this.locationRepositoryLegacy = locationRepositoryLegacy;
        this.categoryRepositoryLegacy = categoryRepositoryLegacy;
    }

    public Single<List<LocationModel>> getLocations() {
        return locationRepositoryLegacy.getLocations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<CategoryModel>> getCategories() {
        return categoryRepositoryLegacy.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
