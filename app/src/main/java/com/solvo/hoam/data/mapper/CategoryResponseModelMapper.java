package com.solvo.hoam.data.mapper;

import com.solvo.hoam.data.db.model.CategoryModel;
import com.solvo.hoam.data.network.response.Category;
import com.solvo.hoam.data.network.response.CategoryResponse;
import com.solvo.hoam.domain.common.Mapper;

import javax.inject.Inject;

public class CategoryResponseModelMapper extends Mapper<Category, CategoryModel> {

    @Inject
    public CategoryResponseModelMapper() {
    }

    @Override
    public CategoryModel map(Category entity) {
        return new CategoryModel(
                entity.getId(),
                entity.getName(),
                entity.getSlug(),
                entity.getParentId(),
                entity.getPriority(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public CategoryModel map(CategoryResponse entity) {
        return new CategoryModel(
                entity.getId(),
                entity.getName(),
                entity.getSlug(),
                entity.getParentId(),
                entity.getPriority(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
