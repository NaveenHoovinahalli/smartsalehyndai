package com.hyundai.teli.smartsales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nith on 2/26/15.
 */
public class CarInfo implements Parcelable {

    @SerializedName("car_id")
    String carId;

    @SerializedName("car_name")
    String carName;

    @SerializedName("car_thumbnail_image_file")
    String carThumbnail;

    @SerializedName("style_exterior")
    ArrayList<StyleExterior> styleExterior;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarThumbnail() {
        return carThumbnail;
    }

    public void setCarThumbnail(String carThumbnail) {
        this.carThumbnail = carThumbnail;
    }

    public ArrayList<StyleExterior> getStyleExterior() {
        return styleExterior;
    }

    public void setStyleExterior(ArrayList<StyleExterior> styleExterior) {
        this.styleExterior = styleExterior;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.carId);
        dest.writeString(this.carName);
        dest.writeString(this.carThumbnail);
        dest.writeSerializable(this.styleExterior);
    }

    public CarInfo() {
    }

    private CarInfo(Parcel in) {
        this.carId = in.readString();
        this.carName = in.readString();
        this.carThumbnail = in.readString();
        this.styleExterior = (ArrayList<StyleExterior>) in.readSerializable();
    }

    public static final Creator<CarInfo> CREATOR = new Creator<CarInfo>() {
        public CarInfo createFromParcel(Parcel source) {
            return new CarInfo(source);
        }

        public CarInfo[] newArray(int size) {
            return new CarInfo[size];
        }
    };
}
