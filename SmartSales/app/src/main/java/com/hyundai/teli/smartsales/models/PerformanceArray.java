package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 26/2/15.
 */
public class PerformanceArray {

    @SerializedName("performance_image")
    ArrayList<PerformanceItem> performanceItems;

    public ArrayList<PerformanceItem> getPerformanceItems() {
        return performanceItems;
    }

    public void setPerformanceItems(ArrayList<PerformanceItem> performanceItems) {
        this.performanceItems = performanceItems;
    }
}
