package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 26/2/15.
 */
public class PerformanceItem {

    @SerializedName("title")
    String title;

    @SerializedName("performance_image_file")
    String perfromanceImage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerfromanceImage() {
        return perfromanceImage;
    }

    public void setPerfromanceImage(String perfromanceImage) {
        this.perfromanceImage = perfromanceImage;
    }
}
