package com.app.contactstask.view.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarkerDatum {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("markerId")
    @Expose
    private String markerId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("userdate")
    @Expose
    private String userdate;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("usertime")
    @Expose
    private String usertime;
    @SerializedName("lati_long")
    @Expose
    private String latiLong;
    @SerializedName("marker_text")
    @Expose
    private String markerText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMarkerId() {
        return markerId;
    }

    public void setMarkerId(String markerId) {
        this.markerId = markerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUserdate() {
        return userdate;
    }

    public void setUserdate(String userdate) {
        this.userdate = userdate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUsertime() {
        return usertime;
    }

    public void setUsertime(String usertime) {
        this.usertime = usertime;
    }

    public String getLatiLong() {
        return latiLong;
    }

    public void setLatiLong(String latiLong) {
        this.latiLong = latiLong;
    }

    public String getMarkerText() {
        return markerText;
    }

    public void setMarkerText(String markerText) {
        this.markerText = markerText;
    }

}
