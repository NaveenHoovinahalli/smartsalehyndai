package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 25/2/15.
 */
public class StyleInteriorArray {

    @SerializedName("style_interior_image_file")
    String interiorImage;

    @SerializedName("style_interior_hotspot")
    ArrayList<String> hotSpots;
}
