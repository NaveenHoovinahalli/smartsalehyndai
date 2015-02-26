package com.hyundai.teli.smartsales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nith on 2/26/15.
 */
public class StyleExteriorHotspotDetails implements Parcelable {

    @SerializedName("hotspotdetails_id")
    String hotSpotDetailsId;

    String header;

    String description;

    @SerializedName("hotspotdetails_image_file")
    String hotSpotDetailsImage;

    public String getHotSpotDetailsId() {
        return hotSpotDetailsId;
    }

    public void setHotSpotDetailsId(String hotSpotDetailsId) {
        this.hotSpotDetailsId = hotSpotDetailsId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHotSpotDetailsImage() {
        return hotSpotDetailsImage;
    }

    public void setHotSpotDetailsImage(String hotSpotDetailsImage) {
        this.hotSpotDetailsImage = hotSpotDetailsImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.hotSpotDetailsId);
        dest.writeString(this.header);
        dest.writeString(this.description);
        dest.writeString(this.hotSpotDetailsImage);
    }

    public StyleExteriorHotspotDetails() {
    }

    private StyleExteriorHotspotDetails(Parcel in) {
        this.hotSpotDetailsId = in.readString();
        this.header = in.readString();
        this.description = in.readString();
        this.hotSpotDetailsImage = in.readString();
    }

    public static final Creator<StyleExteriorHotspotDetails> CREATOR = new Creator<StyleExteriorHotspotDetails>() {
        public StyleExteriorHotspotDetails createFromParcel(Parcel source) {
            return new StyleExteriorHotspotDetails(source);
        }

        public StyleExteriorHotspotDetails[] newArray(int size) {
            return new StyleExteriorHotspotDetails[size];
        }
    };
}
