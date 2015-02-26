package com.hyundai.teli.smartsales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by naveen on 25/2/15.
 */
public class StyleExteriorImage implements Parcelable{


    @SerializedName("style_exterior_image_id")
    String styleExteriorImageId;

    @SerializedName("style_exterior_image_file")
    String getStyleExteriorImageFile;

    @SerializedName("style_exterior_hotspot")
    ArrayList<StyleExteriorHotspot> styleExteriorHotspots;

    public String getStyleExteriorImageId() {
        return styleExteriorImageId;
    }

    public void setStyleExteriorImageId(String styleExteriorImageId) {
        this.styleExteriorImageId = styleExteriorImageId;
    }

    public String getGetStyleExteriorImageFile() {
        return getStyleExteriorImageFile;
    }

    public void setGetStyleExteriorImageFile(String getStyleExteriorImageFile) {
        this.getStyleExteriorImageFile = getStyleExteriorImageFile;
    }

    public ArrayList<StyleExteriorHotspot> getStyleExteriorHotspots() {
        return styleExteriorHotspots;
    }

    public void setStyleExteriorHotspots(ArrayList<StyleExteriorHotspot> styleExteriorHotspots) {
        this.styleExteriorHotspots = styleExteriorHotspots;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.styleExteriorImageId);
        dest.writeString(this.getStyleExteriorImageFile);
        dest.writeSerializable(this.styleExteriorHotspots);
    }

    public StyleExteriorImage() {
    }

    private StyleExteriorImage(Parcel in) {
        this.styleExteriorImageId = in.readString();
        this.getStyleExteriorImageFile = in.readString();
        this.styleExteriorHotspots = (ArrayList<StyleExteriorHotspot>) in.readSerializable();
    }

    public static final Creator<StyleExteriorImage> CREATOR = new Creator<StyleExteriorImage>() {
        public StyleExteriorImage createFromParcel(Parcel source) {
            return new StyleExteriorImage(source);
        }

        public StyleExteriorImage[] newArray(int size) {
            return new StyleExteriorImage[size];
        }
    };
}
