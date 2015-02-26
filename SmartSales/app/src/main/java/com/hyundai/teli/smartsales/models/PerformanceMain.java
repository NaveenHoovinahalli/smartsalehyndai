package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 26/2/15.
 */
public class PerformanceMain {

    @SerializedName("performance")
    ArrayList<PerformanceArray> performanceArrays;

    public ArrayList<PerformanceArray> getPerformanceArrays() {
        return performanceArrays;
    }

    public void setPerformanceArrays(ArrayList<PerformanceArray> performanceArrays) {
        this.performanceArrays = performanceArrays;
    }
}
