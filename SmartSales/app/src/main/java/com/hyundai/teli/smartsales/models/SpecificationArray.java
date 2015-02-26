package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 26/2/15.
 */
public class SpecificationArray {

    @SerializedName("engine_type")
    String engineType;

    @SerializedName("specs")
    ArrayList<SpecificationItem> specsArray = new ArrayList<SpecificationItem>();

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public ArrayList<SpecificationItem> getSpecsArray() {
        return specsArray;
    }

    public void setSpecsArray(ArrayList<SpecificationItem> specsArray) {
        this.specsArray = specsArray;
    }
}
