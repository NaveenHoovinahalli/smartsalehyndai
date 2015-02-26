package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 26/2/15.
 */
public class SpecificationMain {

    @SerializedName("specification")
    ArrayList<SpecificationArray> specificationArrays=new ArrayList<SpecificationArray>();

    public ArrayList<SpecificationArray> getSpecificationArrays() {
        return specificationArrays;
    }

    public void setSpecificationArrays(ArrayList<SpecificationArray> specificationArrays) {
        this.specificationArrays = specificationArrays;
    }
}
