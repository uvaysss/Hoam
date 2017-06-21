package com.solvo.hoam.data.repository;

import com.solvo.hoam.data.mapper.AdResponseEntityMapper;
import com.solvo.hoam.data.mapper.AdResponseModelMapper;
import com.solvo.hoam.data.network.request.AdRequest;
import com.solvo.hoam.data.repository.datasource.AdDataSource;
import com.solvo.hoam.data.repository.datasource.ApiAdDataSource;
import com.solvo.hoam.data.repository.datasource.CategoryDataSource;
import com.solvo.hoam.data.repository.datasource.LocationDataSource;
import com.solvo.hoam.domain.model.AdEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class AdRepository {

    private ApiAdDataSource apiAdDataSource;
    private AdDataSource adDataSource;
    private LocationDataSource locationDataSource;
    private CategoryDataSource categoryDataSource;
    private AdResponseModelMapper adResponseModelMapper;
    private AdResponseEntityMapper adResponseEntityMapper;

    @Inject
    public AdRepository(ApiAdDataSource apiAdDataSource,
                        AdDataSource adDataSource,
                        LocationDataSource locationDataSource,
                        CategoryDataSource categoryDataSource,
                        AdResponseModelMapper adResponseModelMapper,
                        AdResponseEntityMapper adResponseEntityMapper) {
        this.apiAdDataSource = apiAdDataSource;
        this.adDataSource = adDataSource;
        this.locationDataSource = locationDataSource;
        this.categoryDataSource = categoryDataSource;
        this.adResponseModelMapper = adResponseModelMapper;
        this.adResponseEntityMapper = adResponseEntityMapper;
    }

    public Single<List<AdEntity>> getAds(AdRequest request) {
        return apiAdDataSource.getAds(request)
                .map(adResponse -> adResponse.getAdList())
                .toObservable()
                .flatMap(ads -> Observable.fromIterable(ads))
                .map(ad -> {
                    adDataSource.saveAd(adResponseModelMapper.map(ad));

                    AdEntity adEntity = adResponseEntityMapper.map(ad);
                    adEntity.setLocation(locationDataSource.getLocation(ad.getCityId()).getName());
                    adEntity.setCategory(categoryDataSource.getCategory(ad.getCategoryId()).getName());

                    return adEntity;
                })
                .toList();

    }

    public Single<AdEntity> getAd(String adId) {
        return apiAdDataSource.getAd(adId)
                .map(ad -> {
                    AdEntity adEntity = adResponseEntityMapper.map(ad);
                    adEntity.setLocation(locationDataSource.getLocation(ad.getCityId()).getName());
                    adEntity.setCategory(categoryDataSource.getCategory(ad.getCategoryId()).getName());

                    return adEntity;
                });
    }
}
