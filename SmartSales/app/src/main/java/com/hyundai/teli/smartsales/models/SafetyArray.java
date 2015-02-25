package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 25/2/15.
 */
public class SafetyArray {

    @SerializedName("safety_image")
    ArrayList<SafetyItem> safetyImages;

    public ArrayList<SafetyItem> getSafetyImages() {
        return safetyImages;
    }

    public void setSafetyImages(ArrayList<SafetyItem> safetyImages) {
        this.safetyImages = safetyImages;
    }
}
