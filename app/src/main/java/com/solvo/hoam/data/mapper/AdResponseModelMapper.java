package com.solvo.hoam.data.mapper;

import com.solvo.hoam.data.db.model.AdModel;
import com.solvo.hoam.data.network.response.Ad;

import javax.inject.Inject;

public class AdResponseModelMapper{

    @Inject
    public AdResponseModelMapper() {
    }

    public AdModel map(Ad entity) {
        return new AdModel(
                entity.getId(),
                entity.getTitle(),
                entity.getText(),
                entity.getPrice(),
                entity.getPhone(),
                entity.getViews(),
                entity.getAuthorId(),
                entity.getAuthorName(),
                entity.getCategoryId(),
                entity.getIsPremium() != 0,
                entity.getCityId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getIsFree() != 0,
                false
        );
    }
}
