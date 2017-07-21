package com.solvo.hoam.data.mapper;

import com.solvo.hoam.data.db.model.AdModel;
import com.solvo.hoam.data.network.response.Image;
import com.solvo.hoam.domain.model.AdEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AdModelEntityMapperTest {

    @Mock
    private List<Image> imageList;

    @Test
    public void direct() {
        // preparing
        AdModelEntityMapper adModelEntityMapper = new AdModelEntityMapper();
        AdModel adModel = new AdModel(
                "1",
                "Ad",
                "Some text",
                777,
                "911",
                114,
                "11",
                "Gabriel",
                "8",
                false,
                "4",
                "13.04.2017",
                "13.06.2017",
                true,
                false
        );

        // test
        AdEntity adEntity = adModelEntityMapper.direct(adModel, imageList);

        // assertions
        assertEquals("1", adEntity.getId());
        assertEquals("Ad", adEntity.getTitle());
        assertEquals("Some text", adEntity.getText());
        assertEquals(777, adEntity.getPrice());
        assertEquals("911", adEntity.getPhone());
        assertEquals(114, adEntity.getViews());
        assertEquals("11", adEntity.getAuthorId());
        assertEquals("Gabriel", adEntity.getAuthorName());
        assertEquals("8", adEntity.getCategoryId());
        assertEquals(false, adEntity.isPremium());
        assertEquals("4", adEntity.getLocationId());
        assertEquals("13.04.2017", adEntity.getCreatedAt());
        assertEquals("13.06.2017", adEntity.getUpdatedAt());
        assertEquals(true, adEntity.isFree());
        assertEquals(false, adEntity.isFavorite());
        assertEquals(imageList, adEntity.getImageList());
    }

    @Test
    public void indirect() {
        // preparing
        AdModelEntityMapper adModelEntityMapper = new AdModelEntityMapper();
        AdEntity adEntity = new AdEntity(
                "1",
                "Ad",
                "Some text",
                777,
                "911",
                114,
                "11",
                "Gabriel",
                "8",
                "Electronics",
                false,
                "4",
                "Moscow",
                "13.04.2017",
                "13.06.2017",
                imageList,
                true,
                false
        );

        // test
        AdModel adModel = adModelEntityMapper.indirect(adEntity);

        // assertions
        assertEquals("1", adModel.getId());
        assertEquals("Ad", adModel.getTitle());
        assertEquals("Some text", adModel.getText());
        assertEquals(777, adModel.getPrice());
        assertEquals("911", adModel.getPhone());
        assertEquals(114, adModel.getViews());
        assertEquals("11", adModel.getAuthorId());
        assertEquals("Gabriel", adModel.getAuthorName());
        assertEquals("8", adModel.getCategoryId());
        assertEquals(false, adModel.isPremium());
        assertEquals("4", adModel.getCityId());
        assertEquals("13.04.2017", adModel.getCreatedAt());
        assertEquals("13.06.2017", adModel.getUpdatedAt());
        assertEquals(true, adModel.isFree());
        assertEquals(false, adModel.isFavorite());
    }

}