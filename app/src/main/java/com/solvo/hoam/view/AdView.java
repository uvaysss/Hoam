package com.solvo.hoam.view;

public interface AdView {
    void setUpViews(String title, String price, String username, String category, String location, String createdDate,
                    String description, String views, String phone, String adId);

    void hideViewPager();

    void hidePageIndicator();

    void showLoading(boolean show);

    void showError();

    void showContent();
}
