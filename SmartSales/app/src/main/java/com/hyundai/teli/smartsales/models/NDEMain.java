package com.hyundai.teli.smartsales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class NDEMain implements Parcelable {

    @SerializedName("contents")
    ArrayList<NDEDetail> contents = new ArrayList<NDEDetail>();

    public ArrayList<NDEDetail> getContents() {
        return contents;
    }

    public void setContents(ArrayList<NDEDetail> contents) {
        this.contents = contents;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.contents);
    }

    public NDEMain() {
    }

    private NDEMain(Parcel in) {
        this.contents = (ArrayList<NDEDetail>) in.readSerializable();
    }

    public static final Parcelable.Creator<NDEMain> CREATOR = new Parcelable.Creator<NDEMain>() {
        public NDEMain createFromParcel(Parcel source) {
            return new NDEMain(source);
        }

        public NDEMain[] newArray(int size) {
            return new NDEMain[size];
        }
    };
}
