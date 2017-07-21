package com.solvo.hoam.data.mapper;

import com.solvo.hoam.data.db.model.ImageModel;
import com.solvo.hoam.data.network.response.Ad;
import com.solvo.hoam.data.network.response.Image;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AdResponseImageModelMapperTest {

    @Test
    public void map() {
        // prepare
        AdResponseImageModelMapper adResponseImageModelMapper = new AdResponseImageModelMapper();
        List<Image> imageList = new ArrayList<>();
        Image image = new Image(
                "test_id",
                "test_ad_id",
                0,
                "test_big",
                "test_small",
                "test_created_at",
                "test_updated_at"
        );

        imageList.add(image);

        Ad ad = new Ad();
        ad.setImages(imageList);

        // test
        List<ImageModel> imageModelList = adResponseImageModelMapper.map(ad);

        // assertions
        for (ImageModel imageModel : imageModelList) {
            assertEquals("test_id", imageModel.getId());
            assertEquals("test_ad_id", imageModel.getAdId());
            assertEquals(0, imageModel.getIsMain() ? 1 : 0);
            assertEquals("test_big", imageModel.getBig());
            assertEquals("test_small", imageModel.getSmall());
            assertEquals("test_created_at", imageModel.getCreatedAt());
            assertEquals("test_updated_at", imageModel.getUpdatedAt());
        }
    }
}