package com.hyundai.teli.smartsales.db_models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by naveen on 3/3/15.
 */
public class CustomerWalkin extends SugarRecord<CustomerWalkin> implements Parcelable {

    String dealerId;

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public static Creator<CustomerWalkin> getCreator() {
        return CREATOR;
    }

    @SerializedName("mobile_number")
    String mobileNumber;

    @SerializedName("enquire_source")
    String enquireSource;

    @SerializedName("customer_type")
    String custometType;

    @SerializedName("enquire_date")
    String enquireDate;

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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mobileNumber);
        dest.writeString(this.enquireSource);
        dest.writeString(this.custometType);
        dest.writeString(this.enquireDate);
        dest.writeString(this.customerName);
        dest.writeString(this.gender);
        dest.writeString(this.nextFollowUpDate);
        dest.writeString(this.email);
        dest.writeString(this.model);
        dest.writeString(this.fuelType);
        dest.writeString(this.enquireType);
        dest.writeString(this.testDrive);
        dest.writeString(this.presentCar);
    }

    public CustomerWalkin() {
    }

    public CustomerWalkin(Context context,String mobileNumber, String enquireSource, String custometType, String enquireDate, String customerName, String gender, String nextFollowUpDate, String email, String model, String fuelType, String enquireType, String testDrive, String presentCar) {

        this.mobileNumber = mobileNumber;
        this.enquireSource = enquireSource;
        this.custometType = custometType;
        this.enquireDate = enquireDate;
        this.customerName = customerName;
        this.gender = gender;
        this.nextFollowUpDate = nextFollowUpDate;
        this.email = email;
        this.model = model;
        this.fuelType = fuelType;
        this.enquireType = enquireType;
        this.testDrive = testDrive;
        this.presentCar = presentCar;
    }

    private CustomerWalkin(Parcel in) {
        this.mobileNumber = in.readString();
        this.enquireSource = in.readString();
        this.custometType = in.readString();
        this.enquireDate = in.readString();
        this.customerName = in.readString();
        this.gender = in.readString();
        this.nextFollowUpDate = in.readString();
        this.email = in.readString();
        this.model = in.readString();
        this.fuelType = in.readString();
        this.enquireType = in.readString();
        this.testDrive = in.readString();
        this.presentCar = in.readString();
    }

    public static final Parcelable.Creator<CustomerWalkin> CREATOR = new Parcelable.Creator<CustomerWalkin>() {
        public CustomerWalkin createFromParcel(Parcel source) {
            return new CustomerWalkin(source);
        }

        public CustomerWalkin[] newArray(int size) {
            return new CustomerWalkin[size];
        }
    };
}
