package com.solvo.hoam.data.repository.datasource;

import com.solvo.hoam.data.network.RestService;
import com.solvo.hoam.data.network.response.CategoryResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ApiCategoryDataSource {

    private RestService restService;

    @Inject
    public ApiCategoryDataSource(RestService restService) {
        this.restService = restService;
    }

    public Single<List<CategoryResponse>> getCategories() {
        return restService.getCategories();
    }
}
