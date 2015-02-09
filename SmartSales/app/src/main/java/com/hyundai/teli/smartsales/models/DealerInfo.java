package com.hyundai.teli.smartsales.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nith on 2/6/15.
 */
public class DealerInfo implements Parcelable {

    String fullName;

    String email;

    String dealerId;

    String branch;

    String branchPhoneNo;

    String mobileNo;

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
    }

    public DealerInfo() {
    }

    private DealerInfo(Parcel in) {
        this.fullName = in.readString();
        this.email = in.readString();
        this.dealerId = in.readString();
        this.branch = in.readString();
        this.branchPhoneNo = in.readString();
        this.mobileNo = in.readString();
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
