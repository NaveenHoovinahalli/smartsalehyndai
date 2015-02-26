package com.hyundai.teli.smartsales.fragments;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 26/2/15.
 */
public class FeatureArray {

    @SerializedName("variant")
    String variant;

    @SerializedName("feature_details")
    ArrayList<FeatureItem> featureItemArrayList;

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public ArrayList<FeatureItem> getFeatureItemArrayList() {
        return featureItemArrayList;
    }

    public void setFeatureItemArrayList(ArrayList<FeatureItem> featureItemArrayList) {
        this.featureItemArrayList = featureItemArrayList;
    }
}
