package com.solvo.hoam.data.mapper;

import com.solvo.hoam.data.db.model.LocationModel;
import com.solvo.hoam.data.network.response.LocationResponse;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationResponseModelMapperTest {

    @Test
    public void map() throws Exception {
        // preparing
        LocationResponseModelMapper locationResponseModelMapper = new LocationResponseModelMapper();
        LocationResponse locationResponse = new LocationResponse("1", "Moscow", "3", "10.03.2017", "10.04.2017");

        // test
        LocationModel locationModel = locationResponseModelMapper.map(locationResponse);

        // assertions
        assertEquals("1", locationModel.getId());
        assertEquals("Moscow", locationModel.getName());
        assertEquals("3", locationModel.getRegionId());
        assertEquals("10.03.2017", locationModel.getCreatedAt());
        assertEquals("10.04.2017", locationModel.getUpdatedAt());
    }

}