package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Survey extends BaseFragment {

    @InjectView(R.id.radio_group1)
    RadioGroup radioGroup1;

    @InjectView(R.id.radio_group2)
    RadioGroup radioGroup2;

    @InjectView(R.id.radio_group3)
    RadioGroup radioGroup3;

    @InjectView(R.id.radio_group4)
    RadioGroup radioGroup4;

    @InjectView(R.id.radio_group5)
    RadioGroup radioGroup5;

    @InjectView(R.id.radio_group6)
    RadioGroup radioGroup6;

    @InjectView(R.id.radio_group7)
    RadioGroup radioGroup7;

    @InjectView(R.id.radio_group8)
    RadioGroup radioGroup8;

    @InjectView(R.id.radio_group9)
    RadioGroup radioGroup9;

    @InjectView(R.id.registerButton)
    Button registerButton;

    @InjectView(R.id.survey_thanks)
    ImageView imageView;

    int sum=0;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_survey, null);
        ButterKnife.inject(this, view);
        return view;
    }

     @OnClick(R.id.registerButton)
     public void onSubmit(View view){
         int allCkecked=isCheckedRadio();
         if(allCkecked==0) {
             Toast.makeText(getActivity(), "Please fill for all the question", Toast.LENGTH_SHORT).show();
         }else if(Calculate()>72) {
             imageView.setVisibility(View.VISIBLE);
             sum=0;
             Toast.makeText(getActivity(), "Thanks Good", Toast.LENGTH_SHORT).show();
         }else {
             Toast.makeText(getActivity(), "Bad", Toast.LENGTH_SHORT).show();
         }

     }

    private int isCheckedRadio() {

        if(radioGroup1.getCheckedRadioButtonId()==-1)
            return 0;
        else if(radioGroup2.getCheckedRadioButtonId()==-1)
            return 0;
        else if(radioGroup3.getCheckedRadioButtonId()==-1)
            return 0;
        else if(radioGroup4.getCheckedRadioButtonId()==-1)
            return 0;
        else if(radioGroup5.getCheckedRadioButtonId()==-1)
            return 0;
        else if(radioGroup6.getCheckedRadioButtonId()==-1)
            return 0;
        else if(radioGroup7.getCheckedRadioButtonId()==-1)
            return 0;
        else if(radioGroup8.getCheckedRadioButtonId()==-1)
            return 0;
        else if(radioGroup9.getCheckedRadioButtonId()==-1)
            return 0;
        return 1;

    }

    public int Calculate(){
        sum=0;
        if(radioGroup1.getCheckedRadioButtonId()==R.id.radio_button1_1)
            sum=sum+10;
        if(radioGroup1.getCheckedRadioButtonId()==R.id.radio_button1_2)
            sum=sum+9;
        if(radioGroup1.getCheckedRadioButtonId()==R.id.radio_button1_3)
            sum=sum+8;

        if(radioGroup2.getCheckedRadioButtonId()==R.id.radio_button2_1)
            sum=sum+10;
        if(radioGroup2.getCheckedRadioButtonId()==R.id.radio_button2_2)
            sum=sum+9;
        if(radioGroup2.getCheckedRadioButtonId()==R.id.radio_button2_3)
            sum=sum+8;

        if(radioGroup3.getCheckedRadioButtonId()==R.id.radio_button3_1)
            sum=sum+10;
        if(radioGroup3.getCheckedRadioButtonId()==R.id.radio_button3_2)
            sum=sum+9;
        if(radioGroup3.getCheckedRadioButtonId()==R.id.radio_button3_3)
            sum=sum+8;

        if(radioGroup4.getCheckedRadioButtonId()==R.id.radio_button4_1)
            sum=sum+10;
        if(radioGroup4.getCheckedRadioButtonId()==R.id.radio_button4_2)
            sum=sum+9;
        if(radioGroup4.getCheckedRadioButtonId()==R.id.radio_button4_3)
            sum=sum+8;

        if(radioGroup5.getCheckedRadioButtonId()==R.id.radio_button5_1)
            sum=sum+10;
        if(radioGroup5.getCheckedRadioButtonId()==R.id.radio_button5_2)
            sum=sum+9;
        if(radioGroup5.getCheckedRadioButtonId()==R.id.radio_button5_3)
            sum=sum+8;

        if(radioGroup6.getCheckedRadioButtonId()==R.id.radio_button6_1)
            sum=sum+10;
        if(radioGroup6.getCheckedRadioButtonId()==R.id.radio_button6_2)
            sum=sum+9;
        if(radioGroup6.getCheckedRadioButtonId()==R.id.radio_button6_3)
            sum=sum+8;

        if(radioGroup7.getCheckedRadioButtonId()==R.id.radio_button7_1)
            sum=sum+10;
        if(radioGroup7.getCheckedRadioButtonId()==R.id.radio_button7_2)
            sum=sum+9;
        if(radioGroup7.getCheckedRadioButtonId()==R.id.radio_button7_3)
            sum=sum+8;

        if(radioGroup8.getCheckedRadioButtonId()==R.id.radio_button8_1)
            sum=sum+10;
        if(radioGroup8.getCheckedRadioButtonId()==R.id.radio_button8_2)
            sum=sum+9;
        if(radioGroup8.getCheckedRadioButtonId()==R.id.radio_button8_3)
            sum=sum+8;

        if(radioGroup9.getCheckedRadioButtonId()==R.id.radio_button9_1)
            sum=sum+10;
        if(radioGroup9.getCheckedRadioButtonId()==R.id.radio_button9_2)
            sum=sum+9;
        if(radioGroup9.getCheckedRadioButtonId()==R.id.radio_button9_3)
            sum=sum+8;

        Log.d("Sum",""+sum);
        return sum;

    }



}
