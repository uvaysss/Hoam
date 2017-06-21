package com.solvo.hoam.domain.interactor;

import com.solvo.hoam.data.repository.AdRepository;
import com.solvo.hoam.data.repository.CategoryRepository;
import com.solvo.hoam.data.repository.LocationRepository;
import com.solvo.hoam.domain.model.AdEntity;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AdInteractor {

    private AdRepository adRepository;
    private LocationRepository locationRepository;
    private CategoryRepository categoryRepository;

    @Inject
    public AdInteractor(AdRepository adRepository,
                        LocationRepository locationRepository,
                        CategoryRepository categoryRepository) {
        this.adRepository = adRepository;
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
    }

    public Single<AdEntity> getAd(String adId) {
        return adRepository.getAd(adId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
