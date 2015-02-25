package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 25/2/15.
 */
public class ConvenienceArray {

    @SerializedName("convenience_image")
    ArrayList<ConvenienceItem> convenienceImages;

    public ArrayList<ConvenienceItem> getConvenienceImages() {
        return convenienceImages;
    }

    public void setConvenienceImages(ArrayList<ConvenienceItem> convenienceImages) {
        this.convenienceImages = convenienceImages;
    }
}
