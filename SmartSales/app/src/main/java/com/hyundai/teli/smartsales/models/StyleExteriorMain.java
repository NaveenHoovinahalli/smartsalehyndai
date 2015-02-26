package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 25/2/15.
 */
public class StyleExteriorMain {

    @SerializedName("style_exterior")
    ArrayList<StyleExteriorArray> styleExteriorArrays;

    public ArrayList<StyleExteriorArray> getStyleExteriorArrays() {
        return styleExteriorArrays;
    }

    public void setStyleExteriorArrays(ArrayList<StyleExteriorArray> styleExteriorArrays) {
        this.styleExteriorArrays = styleExteriorArrays;
    }
}
