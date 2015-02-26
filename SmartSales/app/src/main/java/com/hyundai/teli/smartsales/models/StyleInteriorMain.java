package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 24/2/15.
 */
public class StyleInteriorMain {

    @SerializedName("style_interior")
    ArrayList<StyleInteriorArray> styleInterior;

    public ArrayList<StyleInteriorArray> getStyleInterior() {
        return styleInterior;
    }

    public void setStyleInterior(ArrayList<StyleInteriorArray> styleInterior) {
        this.styleInterior = styleInterior;
    }
}
