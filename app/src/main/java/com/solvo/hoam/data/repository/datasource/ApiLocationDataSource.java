package com.solvo.hoam.data.repository.datasource;


import com.solvo.hoam.data.network.RestService;
import com.solvo.hoam.data.network.response.LocationResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ApiLocationDataSource {

    private RestService restService;

    @Inject
    public ApiLocationDataSource(RestService restService) {
        this.restService = restService;
    }

    public Single<List<LocationResponse>> getLocations() {
        return restService.getLocations();
    }
}
