package com.solvo.hoam.data.mapper;

import com.solvo.hoam.data.db.model.AdModel;
import com.solvo.hoam.data.network.response.Ad;
import com.solvo.hoam.data.network.response.Image;

import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.*;

public class AdResponseModelMapperTest {

    @Mock
    private List<Image> imageList;

    @Test
    public void map() {
        // prepare
        AdResponseModelMapper adResponseModelMapper = new AdResponseModelMapper();
        Ad ad = new Ad(
                "test_id",
                "test_title",
                "test_text",
                777,
                "test_phone",
                114,
                "test_author_id",
                "test_author_name",
                "test_category_id",
                0,
                "test_city_id",
                "test_created_at",
                "test_updated_at",
                1,
                imageList
        );

        // test
        AdModel adModel = adResponseModelMapper.map(ad);

        // assertions
        assertEquals("test_id", adModel.getId());
        assertEquals("test_title", adModel.getTitle());
        assertEquals("test_text", adModel.getText());
        assertEquals(777, adModel.getPrice());
        assertEquals("test_phone", adModel.getPhone());
        assertEquals(114, adModel.getViews());
        assertEquals("test_author_id", adModel.getAuthorId());
        assertEquals("test_author_name", adModel.getAuthorName());
        assertEquals("test_category_id", adModel.getCategoryId());
        assertEquals(0, adModel.isPremium() ? 1 : 0);
        assertEquals("test_city_id", adModel.getCityId());
        assertEquals("test_created_at", adModel.getCreatedAt());
        assertEquals("test_updated_at", adModel.getUpdatedAt());
        assertEquals(1, adModel.isFree() ? 1 : 0);
    }

}