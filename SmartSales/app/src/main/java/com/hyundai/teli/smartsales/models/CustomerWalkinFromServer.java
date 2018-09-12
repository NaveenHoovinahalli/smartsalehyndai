package com.hyundai.teli.smartsales.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 3/3/15.
 */
public class CustomerWalkinFromServer implements Parcelable {


    @SerializedName("id")
    String id;

    @SerializedName("tablet_id")
    String tabletId;

    @SerializedName("mobile_number")
    String mobileNumber;

    @SerializedName("vin_number")
    String vinNumber;


    @SerializedName("enquire_source")
    String enquireSource;

    @SerializedName("customer_type")
    String custometType;

    @SerializedName("enquire_date")
    String enquireDate;

    @SerializedName("enquire_time")
    String enquireTime;

     @SerializedName("customer_name")
    String customerName;

    @SerializedName("gender")
    String gender;

    @SerializedName("next_follow_up_date")
    String nextFollowUpDate;

    @SerializedName("email")
    String email;

    @SerializedName("model")
    String model;

    @SerializedName("fuel_type")
    String fuelType;

    @SerializedName("enquire_type")
    String enquireType;

    @SerializedName("test_drive")
    String testDrive;

    @SerializedName("present_car")
    String presentCar;

    @SerializedName("dealer")
    String dealer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTabletId() {
        return tabletId;
    }

    public void setTabletId(String tabletId) {
        this.tabletId = tabletId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getEnquireSource() {
        return enquireSource;
    }

    public void setEnquireSource(String enquireSource) {
        this.enquireSource = enquireSource;
    }

    public String getCustometType() {
        return custometType;
    }

    public void setCustometType(String custometType) {
        this.custometType = custometType;
    }

    public String getEnquireDate() {
        return enquireDate;
    }

    public void setEnquireDate(String enquireDate) {
        this.enquireDate = enquireDate;
    }

    public String getEnquireTime() {
        return enquireTime;
    }

    public void setEnquireTime(String enquireTime) {
        this.enquireTime = enquireTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNextFollowUpDate() {
        return nextFollowUpDate;
    }

    public void setNextFollowUpDate(String nextFollowUpDate) {
        this.nextFollowUpDate = nextFollowUpDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getEnquireType() {
        return enquireType;
    }

    public void setEnquireType(String enquireType) {
        this.enquireType = enquireType;
    }

    public String getTestDrive() {
        return testDrive;
    }

    public void setTestDrive(String testDrive) {
        this.testDrive = testDrive;
    }

    public String getPresentCar() {
        return presentCar;
    }

    public void setPresentCar(String presentCar) {
        this.presentCar = presentCar;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.tabletId);
        dest.writeString(this.mobileNumber);
        dest.writeString(this.vinNumber);
        dest.writeString(this.enquireSource);
        dest.writeString(this.custometType);
        dest.writeString(this.enquireDate);
        dest.writeString(this.enquireTime);
        dest.writeString(this.customerName);
        dest.writeString(this.gender);
        dest.writeString(this.nextFollowUpDate);
        dest.writeString(this.email);
        dest.writeString(this.model);
        dest.writeString(this.fuelType);
        dest.writeString(this.enquireType);
        dest.writeString(this.testDrive);
        dest.writeString(this.presentCar);
        dest.writeString(this.dealer);
    }

    public CustomerWalkinFromServer() {
    }

    private CustomerWalkinFromServer(Parcel in) {
        this.id = in.readString();
        this.tabletId = in.readString();
        this.mobileNumber = in.readString();
        this.vinNumber = in.readString();
        this.enquireSource = in.readString();
        this.custometType = in.readString();
        this.enquireDate = in.readString();
        this.enquireTime = in.readString();
        this.customerName = in.readString();
        this.gender = in.readString();
        this.nextFollowUpDate = in.readString();
        this.email = in.readString();
        this.model = in.readString();
        this.fuelType = in.readString();
        this.enquireType = in.readString();
        this.testDrive = in.readString();
        this.presentCar = in.readString();
        this.dealer = in.readString();
    }

    public static final Parcelable.Creator<CustomerWalkinFromServer> CREATOR = new Parcelable.Creator<CustomerWalkinFromServer>() {
        public CustomerWalkinFromServer createFromParcel(Parcel source) {
            return new CustomerWalkinFromServer(source);
        }

        public CustomerWalkinFromServer[] newArray(int size) {
            return new CustomerWalkinFromServer[size];
        }
    };
}
