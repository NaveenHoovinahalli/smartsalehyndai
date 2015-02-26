package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 25/2/15.
 */
public class StyleExteriorArray {

    @SerializedName("style_exterior_image")
    ArrayList<StyleExteriorItem> styleImageArray;

    public ArrayList<StyleExteriorItem> getStyleImageArray() {
        return styleImageArray;
    }

    public void setStyleImageArray(ArrayList<StyleExteriorItem> styleImageArray) {
        this.styleImageArray = styleImageArray;
    }
}
