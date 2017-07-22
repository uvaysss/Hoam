package com.solvo.hoam.domain.interactor;

import com.solvo.hoam.data.repository.AdRepository;
import com.solvo.hoam.domain.model.AdEntity;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AdInteractor {

    private AdRepository adRepository;

    @Inject
    public AdInteractor(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    public Single<AdEntity> getAd(String adId) {
        return adRepository.getAd(adId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable setAdFavorite(AdEntity ad, boolean isFavorite) {
        return adRepository.setAdFavorite(ad, isFavorite);
    }
}
