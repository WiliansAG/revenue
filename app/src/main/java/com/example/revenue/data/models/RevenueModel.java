package com.example.revenue.data.models;

import com.google.gson.annotations.SerializedName;

public class RevenueModel {
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("category")
    private String category;
    @SerializedName("title")
    private String title;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
