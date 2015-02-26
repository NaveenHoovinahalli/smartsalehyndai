package com.hyundai.teli.smartsales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 25/2/15.
 */
public class StyleExterior implements Parcelable {

    @SerializedName("style_exterior_image")
    ArrayList<StyleExteriorImage> styleExteriorImages;

    public ArrayList<StyleExteriorImage> getStyleExteriorImages() {
        return styleExteriorImages;
    }

    public void setStyleExteriorImages(ArrayList<StyleExteriorImage> styleExteriorImages) {
        this.styleExteriorImages = styleExteriorImages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
