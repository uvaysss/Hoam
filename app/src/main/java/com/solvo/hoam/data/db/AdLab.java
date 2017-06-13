package com.solvo.hoam.data.db;

import com.solvo.hoam.data.network.response.Ad;

import java.util.ArrayList;
import java.util.List;

public class AdLab {
    private static AdLab adLab = new AdLab();
    private List<Ad> adList;

    public static AdLab getInstance() {
        return adLab;
    }

    private AdLab() {
        adList = new ArrayList<>();
    }

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }

    public void updateAdList(List<Ad> ads) {
        adList.addAll(ads);
    }

    public Ad getAd(String id) {
        for (Ad ad : adList) {
            if (ad.getId().equals(id)) {
                return ad;
            }
        }

        return null;
    }
}
