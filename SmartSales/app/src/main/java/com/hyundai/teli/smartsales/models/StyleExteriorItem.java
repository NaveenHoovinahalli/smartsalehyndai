package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 25/2/15.
 */
public class StyleExteriorItem {

    @SerializedName("style_exterior_image_id")
    String imageId;

    @SerializedName("style_exterior_image_file")
    String imageFile;

    @SerializedName("style_exterior_hotspot")
    ArrayList<StyleExteriorHotspot> hotspot=new ArrayList<StyleExteriorHotspot>();

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public ArrayList<StyleExteriorHotspot> getHotspot() {
        return hotspot;
    }

    public void setHotspot(ArrayList<StyleExteriorHotspot> hotspot) {
        this.hotspot = hotspot;
    }
}
