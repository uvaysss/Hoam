package com.solvo.hoam.model.data;

import com.solvo.hoam.model.response.Ad;

import java.util.ArrayList;
import java.util.List;

public class AdLab {
    private static AdLab mAdLab = new AdLab();
    private List<Ad> mAdList;

    public static AdLab getInstance() {
        return mAdLab;
    }

    private AdLab() {
        mAdList = new ArrayList<>();
    }

    public List<Ad> getAdList() {
        return mAdList;
    }

    public void setAdList(List<Ad> adList) {
        mAdList = adList;
    }

    public void updateAdList(List<Ad> ads) {
        mAdList.addAll(ads);
    }

    public Ad getAd(String id) {
        for (Ad ad : mAdList) {
            if (ad.getId().equals(id)) {
                return ad;
            }
        }

        return null;
    }
}
