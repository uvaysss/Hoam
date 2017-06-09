package com.solvo.hoam.data.db;

import com.solvo.hoam.data.network.response.LocationResponse;

import java.util.ArrayList;
import java.util.List;

public class LocationLab {
    private static LocationLab sLocationLab = new LocationLab();
    private List<LocationResponse> mLocationList;

    public static LocationLab getInstance() {
        return sLocationLab;
    }

    private LocationLab() {
        mLocationList = new ArrayList<>();
    }

    public List<LocationResponse> getLocations() {
        return mLocationList;
    }

    public String getLocation(String locationId) {
        for (LocationResponse location : mLocationList) {
            if (location.getId().equals(locationId)) {
                return location.getName();
            }
        }

        return null;
    }

    public void setLocations(List<LocationResponse> locations) {
        mLocationList = locations;
    }

    public String getLocationId(int position) {
        return mLocationList.get(position).getId();
    }

    public boolean isEmpty() {
        return mLocationList.size() == 0;
    }
}
