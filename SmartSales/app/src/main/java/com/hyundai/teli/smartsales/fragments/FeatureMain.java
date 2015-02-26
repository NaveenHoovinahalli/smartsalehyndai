package com.hyundai.teli.smartsales.fragments;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 26/2/15.
 */
public class FeatureMain {

    @SerializedName("feature")
    ArrayList<FeatureArray> featureArrays;

    public ArrayList<FeatureArray> getFeatureArrays() {
        return featureArrays;
    }

    public void setFeatureArrays(ArrayList<FeatureArray> featureArrays) {
        this.featureArrays = featureArrays;
    }
}
