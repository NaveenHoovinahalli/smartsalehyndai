package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 25/2/15.
 */
public class VrExteriorCars {

    @SerializedName("color_name")
    String colrName;

    @SerializedName("color_pallet_selected_file")
    String colorPalletSelected;

    @SerializedName("color_pallet_notselected_file")
    String colorPalletNotSelected;

    @SerializedName("vr_exterior_image")
    ArrayList<VRExteriorImages> vrExteriorImageArray;

    public String getColorPalletSelected() {
        return colorPalletSelected;
    }

    public void setColorPalletSelected(String colorPalletSelected) {
        this.colorPalletSelected = colorPalletSelected;
    }

    public String getColorPalletNotSelected() {
        return colorPalletNotSelected;
    }

    public void setColorPalletNotSelected(String colorPalletNotSelected) {
        this.colorPalletNotSelected = colorPalletNotSelected;
    }

    public ArrayList<VRExteriorImages> getVrExteriorImageArray() {
        return vrExteriorImageArray;
    }

    public void setVrExteriorImageArray(ArrayList<VRExteriorImages> vrExteriorImageArray) {
        this.vrExteriorImageArray = vrExteriorImageArray;
    }

    public String getColrName() {

        return colrName;
    }

    public void setColrName(String colrName) {
        this.colrName = colrName;
    }
}
