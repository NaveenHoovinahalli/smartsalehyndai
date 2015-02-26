package com.hyundai.teli.smartsales.fragments;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 26/2/15.
 */
public class FeatureItem {

    @SerializedName("feature_type")
    String featureType;

    @SerializedName("key")
    String key;

    @SerializedName("value")
    String values;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getFeatureType() {

        return featureType;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
    }
}
