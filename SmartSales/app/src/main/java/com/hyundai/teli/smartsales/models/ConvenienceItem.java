package com.hyundai.teli.smartsales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 25/2/15.
 */
public class ConvenienceItem implements Parcelable {

    @SerializedName("title")
    String convenienceyTitle;

    public String getConvenienceyTitle() {
        return convenienceyTitle;
    }

    public void setConvenienceyTitle(String convenienceyTitle) {
        this.convenienceyTitle = convenienceyTitle;
    }

    public String getConvenienceImage() {
        return convenienceImage;
    }

    public void setConvenienceImage(String convenienceImage) {
        this.convenienceImage = convenienceImage;
    }

    @SerializedName("convenience_image_file")
    String convenienceImage;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.convenienceyTitle);
        dest.writeString(this.convenienceImage);
    }

    public ConvenienceItem() {
    }

    private ConvenienceItem(Parcel in) {
        this.convenienceyTitle = in.readString();
        this.convenienceImage = in.readString();
    }

    public static final Parcelable.Creator<ConvenienceItem> CREATOR = new Parcelable.Creator<ConvenienceItem>() {
        public ConvenienceItem createFromParcel(Parcel source) {
            return new ConvenienceItem(source);
        }

        public ConvenienceItem[] newArray(int size) {
            return new ConvenienceItem[size];
        }
    };
}
