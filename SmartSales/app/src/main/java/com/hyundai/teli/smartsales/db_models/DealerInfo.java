package com.hyundai.teli.smartsales.db_models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class DealerInfo extends SugarRecord<DealerInfo> implements Parcelable {

    String fullName;

    String email;

    String dealerId;

    String branch;

    String branchPhoneNo;

    String mobileNo;

    String profileImage;

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranchPhoneNo() {
        return branchPhoneNo;
    }

    public void setBranchPhoneNo(String branchPhoneNo) {
        this.branchPhoneNo = branchPhoneNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public DealerInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fullName);
        dest.writeString(this.email);
        dest.writeString(this.dealerId);
        dest.writeString(this.branch);
        dest.writeString(this.branchPhoneNo);
        dest.writeString(this.mobileNo);
        dest.writeString(this.profileImage);
        dest.writeValue(this.id);
    }

    private DealerInfo(Parcel in) {
        this.fullName = in.readString();
        this.email = in.readString();
        this.dealerId = in.readString();
        this.branch = in.readString();
        this.branchPhoneNo = in.readString();
        this.mobileNo = in.readString();
        this.profileImage = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<DealerInfo> CREATOR = new Creator<DealerInfo>() {
        public DealerInfo createFromParcel(Parcel source) {
            return new DealerInfo(source);
        }

        public DealerInfo[] newArray(int size) {
            return new DealerInfo[size];
        }
    };
}
