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
    ArrayList<StyleInteriorHotspot> hotSpots=new ArrayList<StyleInteriorHotspot>();

    public String getInteriorImage() {
        return interiorImage;
    }

    public void setInteriorImage(String interiorImage) {
        this.interiorImage = interiorImage;
    }

    public ArrayList<StyleInteriorHotspot> getHotSpots() {
        return hotSpots;
    }

    public void setHotSpots(ArrayList<StyleInteriorHotspot> hotSpots) {
        this.hotSpots = hotSpots;
    }
}
