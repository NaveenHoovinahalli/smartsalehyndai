package com.hyundai.teli.smartsales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class CarName implements Parcelable {

    @SerializedName("car_id")
    String id;

    @SerializedName("car_name")
    String carName;

    @SerializedName("car_logo_image_file")
    String carLogo;

    @SerializedName("car_thumbnail_image_file")
    String carThumbnail;

    @SerializedName("car_description_image_file")
    String carDescription;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarLogo() {
        return carLogo;
    }

    public void setCarLogo(String carLogo) {
        this.carLogo = carLogo;
    }

    public String getCarThumbnail() {
        return carThumbnail;
    }

    public void setCarThumbnail(String carThumbnail) {
        this.carThumbnail = carThumbnail;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.carName);
        dest.writeString(this.carLogo);
        dest.writeString(this.carThumbnail);
        dest.writeString(this.carDescription);
    }

    public CarName() {
    }

    private CarName(Parcel in) {
        this.id = in.readString();
        this.carName = in.readString();
        this.carLogo = in.readString();
        this.carThumbnail = in.readString();
        this.carDescription = in.readString();
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
