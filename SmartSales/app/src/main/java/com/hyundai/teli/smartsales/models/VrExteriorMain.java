package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 25/2/15.
 */
public class VrExteriorMain {

    @SerializedName("vr_exterior")
    ArrayList<VrExteriorCars> vrExteriorCars;

    public ArrayList<VrExteriorCars> getVrExteriorCars() {
        return vrExteriorCars;
    }

    public void setVrExteriorCars(ArrayList<VrExteriorCars> vrExteriorCars) {
        this.vrExteriorCars = vrExteriorCars;
    }
}
