package com.solvo.hoam.view;

import com.solvo.hoam.model.response.Ad;

import java.util.List;

public interface HomeView {
    void showLoading(boolean show);

    void setAds(List<Ad> adList, String total);

    void updateAds(List<Ad> adList);

    void showError();

    void goToFilter();

    void goToAboutApp();
}
