package com.solvo.hoam.data.mapper;

import com.solvo.hoam.data.db.model.ImageModel;
import com.solvo.hoam.data.network.response.Image;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ImageModelEntityMapperTest {

    @Test
    public void direct() throws Exception {
        // prepare
        ImageModelEntityMapper imageModelEntityMapper = new ImageModelEntityMapper();
        List<ImageModel> imageModelList = new ArrayList<>();
        ImageModel imageModel = new ImageModel(
                "test_id",
                "test_ad_id",
                false,
                "test_big",
                "test_small",
                "test_created_at",
                "test_updated_at"
        );
        imageModelList.add(imageModel);

        // test
        List<Image> imageList = imageModelEntityMapper.direct(imageModelList);

        // assertions
        for (Image image : imageList) {
            assertEquals("test_id", image.getId());
            assertEquals("test_ad_id", image.getAdId());
            assertEquals(false, image.getIsMain() != 0);
            assertEquals("test_big", image.getBig());
            assertEquals("test_small", image.getSmall());
            assertEquals("test_created_at", image.getCreatedAt());
            assertEquals("test_updated_at", image.getUpdatedAt());
        }
    }

    @Test
    public void indirect() throws Exception {
        // prepare
        ImageModelEntityMapper imageModelEntityMapper = new ImageModelEntityMapper();
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

        // test
        List<ImageModel> imageModelList = imageModelEntityMapper.indirect(imageList);

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