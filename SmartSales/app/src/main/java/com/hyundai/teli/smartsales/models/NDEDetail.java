package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class NDEDetail {

    @SerializedName("_id")
    String id;

    @SerializedName("category")
    String category;

    @SerializedName("path")
    String path;

    @SerializedName("is_video")
    String isVideo;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(String isVideo) {
        this.isVideo = isVideo;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
