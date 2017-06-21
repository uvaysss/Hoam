package com.solvo.hoam.data.repository;

import com.solvo.hoam.data.db.model.LocationModel;
import com.solvo.hoam.data.mapper.LocationResponseModelMapper;
import com.solvo.hoam.data.repository.datasource.ApiLocationDataSource;
import com.solvo.hoam.data.repository.datasource.LocationDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class LocationRepository {

    private LocationDataSource locationDataSource;
    private ApiLocationDataSource apiLocationDataSource;
    private LocationResponseModelMapper locationResponseModelMapper;

    @Inject
    public LocationRepository(LocationDataSource locationDataSource,
                              ApiLocationDataSource apiLocationDataSource,
                              LocationResponseModelMapper locationResponseModelMapper) {
        this.locationDataSource = locationDataSource;
        this.apiLocationDataSource = apiLocationDataSource;
        this.locationResponseModelMapper = locationResponseModelMapper;
    }

    public Completable fetchLocations() {
        return apiLocationDataSource.getLocations()
                .toObservable()
                .flatMap(locationResponses -> Observable.fromIterable(locationResponses))
                .map(location -> {
                    locationDataSource.saveLocation(locationResponseModelMapper.map(location));
                    return location;
                })
                .toList()
                .toCompletable();

    }

    public Single<List<LocationModel>> getLocations() {
        return Single.create(e -> e.onSuccess(locationDataSource.getLocations()));
    }
}
