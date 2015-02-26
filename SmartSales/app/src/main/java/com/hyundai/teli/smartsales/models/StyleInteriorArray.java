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

    public String getInteriorImage() {
        return interiorImage;
    }

    public void setInteriorImage(String interiorImage) {
        this.interiorImage = interiorImage;
    }

    public ArrayList<String> getHotSpots() {
        return hotSpots;
    }

    public void setHotSpots(ArrayList<String> hotSpots) {
        this.hotSpots = hotSpots;
    }
}
