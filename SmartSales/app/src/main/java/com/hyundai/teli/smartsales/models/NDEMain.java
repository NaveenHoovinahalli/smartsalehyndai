package com.hyundai.teli.smartsales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class NDEMain implements Parcelable {


    @SerializedName("id")
    String id;

    @SerializedName("is_video")
    String isVideo;

    @SerializedName("video_thumbnail_file")
    String videoThumbnail;

    @SerializedName("category")
    String category;

    @SerializedName("title")
    String title;

    @SerializedName("nde_video_file")
    String videoFile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(String isVideo) {
        this.isVideo = isVideo;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        dest.writeString(this.isVideo);
        dest.writeString(this.videoThumbnail);
        dest.writeString(this.category);
        dest.writeString(this.title);
        dest.writeString(this.videoFile);
    }

    public NDEMain() {
    }

    private NDEMain(Parcel in) {
        this.id = in.readString();
        this.isVideo = in.readString();
        this.videoThumbnail = in.readString();
        this.category = in.readString();
        this.title = in.readString();
        this.videoFile = in.readString();
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
