package com.solvo.hoam.data.mapper;

import com.solvo.hoam.data.db.model.ImageModel;
import com.solvo.hoam.data.network.response.Image;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ImageModelEntityMapper {

    @Inject
    public ImageModelEntityMapper() {
    }

    public List<Image> direct(List<ImageModel> entity) {
        List<Image> images = new ArrayList<>();

        for (ImageModel image : entity) {
            images.add(new Image(
                    image.getId(),
                    image.getAdId(),
                    image.getIsMain() ? 1 : 0,
                    image.getBig(),
                    image.getSmall(),
                    image.getCreatedAt(),
                    image.getUpdatedAt()
            ));
        }

        return images;
    }

    public List<ImageModel> indirect(List<Image> entity) {
        List<ImageModel> images = new ArrayList<>();

        for (Image image : entity) {
            images.add(new ImageModel(
                    image.getId(),
                    image.getAdId(),
                    image.getIsMain() != 0,
                    image.getBig(),
                    image.getSmall(),
                    image.getCreatedAt(),
                    image.getUpdatedAt()
            ));
        }

        return images;
    }
}
