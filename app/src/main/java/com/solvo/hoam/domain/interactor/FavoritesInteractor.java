package com.solvo.hoam.domain.interactor;

import com.solvo.hoam.data.repository.AdRepository;
import com.solvo.hoam.domain.model.AdEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FavoritesInteractor {

    private AdRepository adRepository;

    @Inject
    public FavoritesInteractor(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    public Single<List<AdEntity>> getFavoriteAds() {
        return adRepository.getFavoriteAds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
