package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyundai.teli.smartsales.R;

import butterknife.ButterKnife;

/**
 * Created by naveen on 10/2/15.
 */
public class NewDealership extends BaseFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_dealership,null);
        ButterKnife.inject(this, view);
        return view;
    }
}
