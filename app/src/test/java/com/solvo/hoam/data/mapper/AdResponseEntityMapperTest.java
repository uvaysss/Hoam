package com.solvo.hoam.data.mapper;

import com.solvo.hoam.data.network.response.Ad;
import com.solvo.hoam.data.network.response.Image;
import com.solvo.hoam.domain.model.AdEntity;

import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class AdResponseEntityMapperTest {

    @Mock
    private List<Image> imageList;

    @Test
    public void map() {
        // prepare
        AdResponseEntityMapper adResponseEntityMapper = new AdResponseEntityMapper();
        Ad ad = new Ad(
                "1",
                "Ad",
                "Some text",
                777,
                "911",
                114,
                "11",
                "Gabriel",
                "8",
                0,
                "4",
                "13.04.2017",
                "13.06.2017",
                1,
                imageList
        );

        // test
        AdEntity adEntity = adResponseEntityMapper.map(ad);

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

}