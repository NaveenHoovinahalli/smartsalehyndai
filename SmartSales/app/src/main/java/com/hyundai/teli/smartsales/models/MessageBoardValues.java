package com.hyundai.teli.smartsales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 23/2/15.
 */
public class MessageBoardValues implements Parcelable {

    @SerializedName("message_title")
    String messageTitle;

    @SerializedName("message_details")
    String messageDetails;

    @SerializedName("created_date")
    String createdDate;

    @SerializedName("created_time")
    String createdTime;

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageDetails() {
        return messageDetails;
    }

    public void setMessageDetails(String messageDetails) {
        this.messageDetails = messageDetails;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.messageTitle);
        dest.writeString(this.messageDetails);
        dest.writeString(this.createdDate);
        dest.writeString(this.createdTime);
    }

    public MessageBoardValues() {
    }

    private MessageBoardValues(Parcel in) {
        this.messageTitle = in.readString();
        this.messageDetails = in.readString();
        this.createdDate = in.readString();
        this.createdTime = in.readString();
    }

    public static final Parcelable.Creator<MessageBoardValues> CREATOR = new Parcelable.Creator<MessageBoardValues>() {
        public MessageBoardValues createFromParcel(Parcel source) {
            return new MessageBoardValues(source);
        }

        public MessageBoardValues[] newArray(int size) {
            return new MessageBoardValues[size];
        }
    };
}
