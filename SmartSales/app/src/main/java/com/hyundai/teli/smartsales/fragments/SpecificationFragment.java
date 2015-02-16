package com.hyundai.teli.smartsales.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hyundai.teli.smartsales.R;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
@SuppressLint("ValidFragment")
public class SpecificationFragment  extends Fragment {

    @InjectView(R.id.performance_pager_image)
    ImageView imageView;

    int imagepth;

    @SuppressLint("ValidFragment")
    public SpecificationFragment(int imagepath){
        this.imagepth=imagepath;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.specification_pager_item,null);
        ButterKnife.inject(this, view);
        Log.d("PerformancePager", "ImagePath" + imagepth);
//        Picasso.with(getActivity()).load(imagepth).
//                placeholder(R.drawable.btn_add_plus).into(imageView);
        Picasso.with(getActivity()).load(imagepth).into(imageView);

        return view;
    }
}