package com.hyundai.teli.smartsales.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.ProfileFullScreen;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class MyInfo extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_info, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick({R.id.ok_button, R.id.full_screen})
    public void OnClickListener(View view) {
        Intent openFullScreen = new Intent(getActivity(), ProfileFullScreen.class);
        startActivity(openFullScreen);
    }
}
