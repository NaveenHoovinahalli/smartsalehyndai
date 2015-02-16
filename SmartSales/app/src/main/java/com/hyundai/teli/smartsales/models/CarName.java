package com.hyundai.teli.smartsales.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class CarName implements Parcelable {

    String carName;

    String baseLine;

    String description;

    String thumbnail;


    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getBaseLine() {
        return baseLine;
    }

    public void setBaseLine(String baseLine) {
        this.baseLine = baseLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.carName);
        dest.writeString(this.baseLine);
        dest.writeString(this.description);
        dest.writeString(this.thumbnail);
    }

    public CarName() {
    }

    private CarName(Parcel in) {
        this.carName = in.readString();
        this.baseLine = in.readString();
        this.description = in.readString();
        this.thumbnail = in.readString();
    }

    public static final Creator<CarName> CREATOR = new Creator<CarName>() {
        public CarName createFromParcel(Parcel source) {
            return new CarName(source);
        }

        public CarName[] newArray(int size) {
            return new CarName[size];
        }
    };
}
