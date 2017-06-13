package com.solvo.hoam.domain;

import android.support.annotation.Nullable;

import com.solvo.hoam.data.db.AdLab;
import com.solvo.hoam.data.db.CategoryLab;
import com.solvo.hoam.data.db.FilterLab;
import com.solvo.hoam.data.db.LocationLab;
import com.solvo.hoam.data.network.RestClient;
import com.solvo.hoam.data.network.RestService;
import com.solvo.hoam.data.network.response.Ad;
import com.solvo.hoam.data.network.response.AdResponse;
import com.solvo.hoam.data.network.response.Category;
import com.solvo.hoam.data.network.response.CategoryResponse;
import com.solvo.hoam.data.network.response.LocationResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DataManager {
    private RestService restService;
    private FilterLab filterLab;
    private AdLab adLab;
    private LocationLab locationLab;
    private CategoryLab categoryLab;

    public DataManager() {
        restService = new RestClient().getRestService();
        filterLab = FilterLab.getInstance();
        adLab = AdLab.getInstance();
        locationLab = LocationLab.getInstance();
        categoryLab = CategoryLab.getInstance();
    }

    public Observable<AdResponse> getAds(int page, String query) {
        return restService.getAds(page, query, getLocationId(), getCategoryId(), getPriceFrom(), getPriceTo())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Ad> getAd(String adId) {
        return restService.getAd(adId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<LocationResponse>> getLocations() {
        return restService.getLocations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<CategoryResponse>> getCategories() {
        return restService.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Nullable
    private String getCategoryId() {
        int parentPosition = filterLab.getParentCategorySpinnerPosition();
        if (parentPosition == 0) {
            return null;
        }

        int subPosition = filterLab.getSubCategorySpinnerPosition();
        String parentCategoryId = categoryLab.getParentCategoryId(parentPosition);
        if (subPosition == 0 || subPosition == -1) {
            return parentCategoryId;
        }

        return categoryLab.getSubCategoryId(subPosition, parentCategoryId);
    }

    @Nullable
    private String getLocationId() {
        int position = filterLab.getLocationSpinnerPosition();

        return position != 0 ? locationLab.getLocationId(position) : null;
    }

    private String getPriceFrom() {
        long from = filterLab.getPriceFrom();
        return from != 0 ? String.valueOf(from) : null;
    }

    private String getPriceTo() {
        long to = filterLab.getPriceTo();
        return to != 0 ? String.valueOf(to) : null;
    }

    public void saveAdList(List<Ad> ads) {
        adLab.setAdList(ads);
    }

    public void updateAdList(List<Ad> ads) {
        adLab.updateAdList(ads);
    }

    public void saveLocationList(List<LocationResponse> locations) {
        LocationResponse location = new LocationResponse();
        location.setId("0");
        location.setName("Любой город");
        location.setRegionId("0");
        locations.add(0, location);

        locationLab.setLocations(locations);
    }

    public void saveCategoryList(List<CategoryResponse> categories) {
        List<Category> parentCategories = new ArrayList<>();
        List<Category> subCategories = new ArrayList<>();

        Category category = new Category();
        category.setId("0");
        category.setName("Любая категория");
        category.setParentId("0");

        parentCategories.add(category);
        subCategories.add(category);

        for (CategoryResponse parentCategory : categories) {
            Category c1 = new Category();
            c1.setId(parentCategory.getId());
            c1.setName(parentCategory.getName());
            c1.setSlug(parentCategory.getSlug());
            c1.setParentId(parentCategory.getParentId());
            c1.setPriority(parentCategory.getPriority());
            c1.setCreatedAt(parentCategory.getCreatedAt());
            c1.setUpdatedAt(parentCategory.getUpdatedAt());

            parentCategories.add(c1);

            for (Category subCategory : parentCategory.getCategories()) {
                Category c2 = new Category();
                c2.setId(subCategory.getId());
                c2.setName(subCategory.getName());
                c2.setSlug(subCategory.getSlug());
                c2.setParentId(subCategory.getParentId());
                c2.setPriority(subCategory.getPriority());
                c2.setCreatedAt(subCategory.getCreatedAt());
                c2.setUpdatedAt(subCategory.getUpdatedAt());

                subCategories.add(c2);
            }
        }

        categoryLab.setCategories(parentCategories, subCategories);
    }
}
