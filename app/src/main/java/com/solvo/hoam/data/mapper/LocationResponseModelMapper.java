package com.solvo.hoam.data.mapper;

import com.solvo.hoam.data.db.model.LocationModel;
import com.solvo.hoam.data.network.response.LocationResponse;
import com.solvo.hoam.domain.common.Mapper;

import javax.inject.Inject;

public class LocationResponseModelMapper extends Mapper<LocationResponse, LocationModel> {

    @Inject
    public LocationResponseModelMapper() {
    }

    @Override
    public LocationModel map(LocationResponse entity) {
        return new LocationModel(
                entity.getId(),
                entity.getName(),
                entity.getRegionId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
