package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 25/2/15.
 */
public class SafetyMain {

    @SerializedName("safety")
    ArrayList<SafetyArray> safetyMainArray;

    public ArrayList<SafetyArray> getSafetyMainArray() {
        return safetyMainArray;
    }

    public void setSafetyMainArray(ArrayList<SafetyArray> safetyMainArray) {
        this.safetyMainArray = safetyMainArray;
    }
}
