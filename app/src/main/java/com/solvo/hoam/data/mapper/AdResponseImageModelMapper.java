package com.solvo.hoam.data.mapper;

import com.solvo.hoam.data.db.model.ImageModel;
import com.solvo.hoam.data.network.response.Ad;
import com.solvo.hoam.data.network.response.Image;
import com.solvo.hoam.domain.common.Mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AdResponseImageModelMapper extends Mapper<Ad, List<ImageModel>> {

    @Inject
    public AdResponseImageModelMapper() {
    }

    @Override
    public List<ImageModel> map(Ad entity) {
        List<ImageModel> imageList = new ArrayList<>();
        for (Image image : entity.getImages()) {
            imageList.add(new ImageModel(
                    image.getId(),
                    image.getAdId(),
                    image.getIsMain() != 0,
                    image.getBig(),
                    image.getSmall(),
                    image.getCreatedAt(),
                    image.getUpdatedAt()
            ));
        }
        return imageList;
    }
}
