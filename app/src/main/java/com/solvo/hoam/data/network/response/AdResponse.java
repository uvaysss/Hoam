package com.solvo.hoam.data.network.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdResponse {
    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("per_page")
    @Expose
    private int perPage;

    @SerializedName("current_page")
    @Expose
    private int currentPage;

    @SerializedName("last_page")
    @Expose
    private int lastPage;

    @SerializedName("next_page_url")
    @Expose
    private String nextPageUrl;

    @SerializedName("prev_page_url")
    @Expose
    private String prevPageUrl;

    @SerializedName("from")
    @Expose
    private int from;

    @SerializedName("to")
    @Expose
    private int to;

    @SerializedName("data")
    @Expose
    private List<Ad> ad = null;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public List<Ad> getAdList() {
        return ad;
    }

    public void setAdList(List<Ad> adList) {
        this.ad = adList;
    }
}
