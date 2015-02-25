package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 25/2/15.
 */
public class VRInteriorMain {
    @SerializedName("vr_interior")
    ArrayList<VRInteriorArray> vrInteriorMain;

    public ArrayList<VRInteriorArray> getVrInteriorMain() {
        return vrInteriorMain;
    }

    public void setVrInteriorMain(ArrayList<VRInteriorArray> vrInteriorMain) {
        this.vrInteriorMain = vrInteriorMain;
    }
}
