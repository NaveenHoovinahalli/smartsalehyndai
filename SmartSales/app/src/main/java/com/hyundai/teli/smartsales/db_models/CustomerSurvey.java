package com.hyundai.teli.smartsales.db_models;

import com.orm.SugarRecord;

/**
 * Created by naveen on 4/3/15.
 */
public class CustomerSurvey extends SugarRecord<CustomerSurvey> {

    String dealer;
    String customerName;
    String mobileNo;
    String vinNo;
    String surveyQuestion;
    String surveyOptions;

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public String getSurveyQuestion() {
        return surveyQuestion;
    }

    public void setSurveyQuestion(String surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
    }

    public String getSurveyOptions() {
        return surveyOptions;
    }

    public void setSurveyOptions(String surveyOptions) {
        this.surveyOptions = surveyOptions;
    }




}
