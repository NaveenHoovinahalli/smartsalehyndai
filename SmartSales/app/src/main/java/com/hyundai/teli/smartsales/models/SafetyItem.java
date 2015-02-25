package com.hyundai.teli.smartsales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 25/2/15.
 */
public class SafetyItem implements Parcelable {

    @SerializedName("title")
    String safetyTitle;

    @SerializedName("safety_image_file")
    String safetyImage;

    public String getSafetyTitle() {
        return safetyTitle;
    }

    public void setSafetyTitle(String safetyTitle) {
        this.safetyTitle = safetyTitle;
    }

    public String getSafetyImage() {
        return safetyImage;
    }

    public void setSafetyImage(String safetyImage) {
        this.safetyImage = safetyImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.safetyTitle);
        dest.writeString(this.safetyImage);
    }

    public SafetyItem() {
    }

    private SafetyItem(Parcel in) {
        this.safetyTitle = in.readString();
        this.safetyImage = in.readString();
    }

    public static final Parcelable.Creator<SafetyItem> CREATOR = new Parcelable.Creator<SafetyItem>() {
        public SafetyItem createFromParcel(Parcel source) {
            return new SafetyItem(source);
        }

        public SafetyItem[] newArray(int size) {
            return new SafetyItem[size];
        }
    };
}
