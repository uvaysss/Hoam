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
    private RestService mRestService;
    private FilterLab mFilterLab;
    private AdLab mAdLab;
    private LocationLab mLocationLab;
    private CategoryLab mCategoryLab;

    public DataManager() {
        mRestService = new RestClient().getRestService();
        mFilterLab = FilterLab.getInstance();
        mAdLab = AdLab.getInstance();
        mLocationLab = LocationLab.getInstance();
        mCategoryLab = CategoryLab.getInstance();
    }

    public Observable<AdResponse> getAds(int page, String query) {
        return mRestService.getAds(page, query, getLocationId(), getCategoryId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Ad> getAd(String adId) {
        return mRestService.getAd(adId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<LocationResponse>> getLocations() {
        return mRestService.getLocations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<CategoryResponse>> getCategories() {
        return mRestService.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Nullable
    private String getCategoryId() {
        int parentPosition = mFilterLab.getParentCategorySpinnerPosition();
        if (parentPosition == 0) {
            return null;
        }

        int subPosition = mFilterLab.getSubCategorySpinnerPosition();
        String parentCategoryId = mCategoryLab.getParentCategoryId(parentPosition);
        if (subPosition == 0 || subPosition == -1) {
            return parentCategoryId;
        }

        return mCategoryLab.getSubCategoryId(subPosition, parentCategoryId);
    }

    @Nullable
    private String getLocationId() {
        int position = mFilterLab.getLocationSpinnerPosition();

        return position != 0 ? mLocationLab.getLocationId(position) : null;
    }

    public void saveAdList(List<Ad> ads) {
        mAdLab.setAdList(ads);
    }

    public void updateAdList(List<Ad> ads) {
        mAdLab.updateAdList(ads);
    }

    public void saveLocationList(List<LocationResponse> locations) {
        LocationResponse location = new LocationResponse();
        location.setId("0");
        location.setName("Любой город");
        location.setRegionId("0");
        locations.add(0, location);

        mLocationLab.setLocations(locations);
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

        mCategoryLab.setCategories(parentCategories, subCategories);
    }
}
