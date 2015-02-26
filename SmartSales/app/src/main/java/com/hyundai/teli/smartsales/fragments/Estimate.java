package com.hyundai.teli.smartsales.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyundai.teli.smartsales.R;

import butterknife.ButterKnife;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Estimate extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estimate, null);
        ButterKnife.inject(this, view);
        removeCar();
        return view;
    }

    private void removeCar() {
        getActivity().getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE)
                .edit()
                .putString("CAR", "")
                .commit();
    }
}
