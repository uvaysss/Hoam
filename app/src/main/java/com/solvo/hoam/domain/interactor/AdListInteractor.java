package com.solvo.hoam.domain.interactor;

import com.solvo.hoam.data.db.Filter;
import com.solvo.hoam.data.network.request.AdRequest;
import com.solvo.hoam.data.repository.AdRepository;
import com.solvo.hoam.domain.model.AdEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AdListInteractor {

    private Filter filter;
    private AdRepository adRepository;

    @Inject
    public AdListInteractor(AdRepository adRepository, Filter filter) {
        this.adRepository = adRepository;
        this.filter = filter;
    }

    public Single<List<AdEntity>> getAds(int page, String query) {
        AdRequest request = new AdRequest(
                page,
                query,
                filter.getLocationId(),
                filter.getCategoryId(),
                filter.getPriceFromString(),
                filter.getPriceToString()
        );

        return adRepository.getAds(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
