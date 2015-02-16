package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.Consultation;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class CustomerManagement extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_management,null);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick(R.id.registerButton)
    public void registerClick(View view){
        Toast.makeText(getActivity(), "Register", Toast.LENGTH_SHORT).show();
        ((Consultation)getActivity()).showRegistration();
    }
}
