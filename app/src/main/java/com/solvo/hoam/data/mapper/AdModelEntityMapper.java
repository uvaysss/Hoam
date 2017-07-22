package com.solvo.hoam.data.mapper;

import com.solvo.hoam.data.db.model.AdModel;
import com.solvo.hoam.data.network.response.Image;
import com.solvo.hoam.domain.model.AdEntity;

import java.util.List;

import javax.inject.Inject;

public class AdModelEntityMapper {

    @Inject
    public AdModelEntityMapper() {
    }

    public AdEntity direct(AdModel entity, List<Image> imageList) {
        return new AdEntity(
                entity.getId(),
                entity.getTitle(),
                entity.getText(),
                entity.getPrice(),
                entity.getPhone(),
                entity.getViews(),
                entity.getAuthorId(),
                entity.getAuthorName(),
                entity.getCategoryId().equals("null") ? null : entity.getCategoryId(),
                entity.getCityId().equals("null") ? null : entity.getCityId(),
                entity.isPremium(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                imageList,
                entity.isFree(),
                entity.isFavorite()
        );
    }

    public AdModel indirect(AdEntity entity) {
        return new AdModel(
                entity.getId(),
                entity.getTitle(),
                entity.getText(),
                entity.getPrice(),
                entity.getPhone(),
                entity.getViews(),
                entity.getAuthorId(),
                entity.getAuthorName(),
                entity.getCategoryId() == null ? "null" : entity.getCategoryId(),
                entity.isPremium(),
                entity.getLocationId() == null ? "null" : entity.getLocationId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isFree(),
                entity.isFavorite()
        );
    }
}
