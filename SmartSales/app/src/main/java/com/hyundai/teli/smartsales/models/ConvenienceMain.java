package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 25/2/15.
 */
public class ConvenienceMain {

    @SerializedName("convenience")
    ArrayList<ConvenienceArray> convenienceMainArray;

    public ArrayList<ConvenienceArray> getConvenienceMainArray() {
        return convenienceMainArray;
    }

    public void setConvenienceMainArray(ArrayList<ConvenienceArray> convenienceMainArray) {
        this.convenienceMainArray = convenienceMainArray;
    }
}
