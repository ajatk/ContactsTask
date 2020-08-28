package com.app.contactstask.view.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OnUserIDResponse {
    @SerializedName("fetchData")
    @Expose
    private List<FetchDatum> fetchData = null;

    public List<FetchDatum> getFetchData() {
        return fetchData;
    }

    public void setFetchData(List<FetchDatum> fetchData) {
        this.fetchData = fetchData;
    }
}
