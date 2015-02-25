package com.hyundai.teli.smartsales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 23/2/15.
 */
public class BrandStoryValues implements Parcelable {

    @SerializedName("id")
    String id;

    @SerializedName("title")
    String title;

    @SerializedName("description")
    String description;

    @SerializedName("image_file")
    String imageFile;

    @SerializedName("brandstory_video_file")
    String videoFile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(String videoFile) {
        this.videoFile = videoFile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.imageFile);
        dest.writeString(this.videoFile);
    }

    public BrandStoryValues() {
    }

    private BrandStoryValues(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.imageFile = in.readString();
        this.videoFile = in.readString();
    }

    public static final Parcelable.Creator<BrandStoryValues> CREATOR = new Parcelable.Creator<BrandStoryValues>() {
        public BrandStoryValues createFromParcel(Parcel source) {
            return new BrandStoryValues(source);
        }

        public BrandStoryValues[] newArray(int size) {
            return new BrandStoryValues[size];
        }
    };
}
