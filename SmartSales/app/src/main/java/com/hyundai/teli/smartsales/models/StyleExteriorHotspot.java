package com.hyundai.teli.smartsales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 26/2/15.
 */
public class StyleExteriorHotspot implements Parcelable{

    @SerializedName("exterior_hotspot_id")
    String hotSpotId;

    @SerializedName("style_exterior_hotspotdetails")
    StyleExteriorHotspotDetails styleExteriorHotspotDetails;

    @SerializedName("X")
    String xValue;

    @SerializedName("Y")
    String yValue;

    public String getHotSpotId() {
        return hotSpotId;
    }

    public void setHotSpotId(String hotSpotId) {
        this.hotSpotId = hotSpotId;
    }

    public StyleExteriorHotspotDetails getStyleExteriorHotspotDetails() {
        return styleExteriorHotspotDetails;
    }

    public void setStyleExteriorHotspotDetails(StyleExteriorHotspotDetails styleExteriorHotspotDetails) {
        this.styleExteriorHotspotDetails = styleExteriorHotspotDetails;
    }

    public String getxValue() {
        return xValue;
    }

    public void setxValue(String xValue) {
        this.xValue = xValue;
    }

    public String getyValue() {
        return yValue;
    }

    public void setyValue(String yValue) {
        this.yValue = yValue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.hotSpotId);
        dest.writeParcelable(this.styleExteriorHotspotDetails, 0);
        dest.writeString(this.xValue);
        dest.writeString(this.yValue);
    }

    public StyleExteriorHotspot() {
    }

    private StyleExteriorHotspot(Parcel in) {
        this.hotSpotId = in.readString();
        this.styleExteriorHotspotDetails = in.readParcelable(StyleExteriorHotspotDetails.class.getClassLoader());
        this.xValue = in.readString();
        this.yValue = in.readString();
    }

    public static final Creator<StyleExteriorHotspot> CREATOR = new Creator<StyleExteriorHotspot>() {
        public StyleExteriorHotspot createFromParcel(Parcel source) {
            return new StyleExteriorHotspot(source);
        }

        public StyleExteriorHotspot[] newArray(int size) {
            return new StyleExteriorHotspot[size];
        }
    };
}
