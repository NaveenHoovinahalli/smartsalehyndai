package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 25/2/15.
 */
public class VRExteriorImages {

    @SerializedName("vr_exterior_image_file")
    String veExteriorImage;

    public String getVeExteriorImage() {
        return veExteriorImage;
    }

    public void setVeExteriorImage(String veExteriorImage) {
        this.veExteriorImage = veExteriorImage;
    }
}
