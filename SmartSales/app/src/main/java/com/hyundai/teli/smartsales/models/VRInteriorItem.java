package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 25/2/15.
 */
public class VRInteriorItem {

    @SerializedName("title")
    String interiorTitle;

    public String getInteriorTitle() {
        return interiorTitle;
    }

    public void setInteriorTitle(String interiorTitle) {
        this.interiorTitle = interiorTitle;
    }

    public String getInteriorImage() {
        return interiorImage;
    }

    public void setInteriorImage(String interiorImage) {
        this.interiorImage = interiorImage;
    }

    @SerializedName("vr_interior_image_file")
    String interiorImage;
}
