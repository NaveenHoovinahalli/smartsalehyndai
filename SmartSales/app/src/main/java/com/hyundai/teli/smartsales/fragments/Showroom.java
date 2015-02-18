package com.hyundai.teli.smartsales.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.CarDetails;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Showroom extends BaseFragment {

    @InjectView(R.id.imageView0)
    ImageView mImageView;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_showroom, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick(R.id.imageView0)
    public void onClick(View view) {
        Intent openCarDetails = new Intent(getActivity(), CarDetails.class);
        startActivity(openCarDetails);
    }


}
