package com.solvo.hoam.data.mapper;

import com.solvo.hoam.data.network.response.Ad;
import com.solvo.hoam.domain.common.Mapper;
import com.solvo.hoam.domain.model.AdEntity;

import javax.inject.Inject;

public class AdResponseEntityMapper extends Mapper<Ad, AdEntity> {

    @Inject
    public AdResponseEntityMapper() {
    }

    @Override
    public AdEntity map(Ad entity) {
        return new AdEntity(
                entity.getId(),
                entity.getTitle(),
                entity.getText(),
                entity.getPrice(),
                entity.getPhone(),
                entity.getViews(),
                entity.getAuthorId(),
                entity.getAuthorName(),
                entity.getCategoryId(),
                entity.getCityId(),
                entity.getIsPremium() != 0,
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getImages(),
                entity.getIsFree() != 0,
                false
        );
    }
}
