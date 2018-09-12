package com.hyundai.teli.smartsales.fragments;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hyundai.teli.smartsales.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by naveen on 2/8/15.
 */
@SuppressLint("ValidFragment")
public class PerformanceFragment extends Fragment {

    @InjectView(R.id.performance_pager_image)
    ImageView imageView;

    String imagepth;

    @SuppressLint("ValidFragment")
    public PerformanceFragment(String imagepath) {
        this.imagepth = imagepath;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.performance_pager_item, null);
        ButterKnife.inject(this, view);
        Log.d("PerformancePager", "ImagePath" + imagepth);

        imageView.setImageURI(Uri.parse(imagepth));

        return view;
    }
}
