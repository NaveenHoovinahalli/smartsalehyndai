package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 25/2/15.
 */
public class VRInteriorArray  {

    @SerializedName("vr_interior_image")
    ArrayList<VRInteriorItem> vrInteriorArray;

    public ArrayList<VRInteriorItem> getVrInteriorArray() {
        return vrInteriorArray;
    }

    public void setVrInteriorArray(ArrayList<VRInteriorItem> vrInteriorArray) {
        this.vrInteriorArray = vrInteriorArray;
    }
}
