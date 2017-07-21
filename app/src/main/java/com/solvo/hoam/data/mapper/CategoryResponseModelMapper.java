package com.solvo.hoam.data.mapper;

import com.solvo.hoam.data.db.model.CategoryModel;
import com.solvo.hoam.data.network.response.Category;
import com.solvo.hoam.data.network.response.CategoryResponse;

import javax.inject.Inject;

public class CategoryResponseModelMapper {

    @Inject
    public CategoryResponseModelMapper() {
    }

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
