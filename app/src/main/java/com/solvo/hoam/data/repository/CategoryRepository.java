package com.solvo.hoam.data.repository;

import com.solvo.hoam.data.db.model.CategoryModel;
import com.solvo.hoam.data.mapper.CategoryResponseModelMapper;
import com.solvo.hoam.data.repository.datasource.ApiCategoryDataSource;
import com.solvo.hoam.data.repository.datasource.CategoryDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class CategoryRepository {

    private CategoryDataSource categoryDataSource;
    private ApiCategoryDataSource apiCategoryDataSource;
    private CategoryResponseModelMapper categoryResponseModelMapper;

    @Inject
    public CategoryRepository(CategoryDataSource categoryDataSource,
                              ApiCategoryDataSource apiCategoryDataSource,
                              CategoryResponseModelMapper categoryResponseModelMapper) {
        this.categoryDataSource = categoryDataSource;
        this.apiCategoryDataSource = apiCategoryDataSource;
        this.categoryResponseModelMapper = categoryResponseModelMapper;
    }

    public Completable fetchCategories() {
        return apiCategoryDataSource.getCategories()
                .toObservable()
                .flatMap(categories -> Observable.fromIterable(categories))
                .map(category -> {
                    categoryDataSource.saveCategory(categoryResponseModelMapper.map(category));
                    return category.getCategories();
                })
                .flatMap(categories -> Observable.fromIterable(categories))
                .map(category -> {
                    categoryDataSource.saveCategory(categoryResponseModelMapper.map(category));
                    return category;
                })
                .toList()
                .toCompletable();

    }

    public Single<List<CategoryModel>> getCategories() {
        return Single.create(e -> e.onSuccess(categoryDataSource.getCategories()));
    }
}
