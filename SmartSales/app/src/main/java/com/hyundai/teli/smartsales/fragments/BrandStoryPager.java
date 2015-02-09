package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.hyundai.teli.smartsales.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by naveen on 9/2/15.
 */
public class BrandStoryPager extends Fragment {

    @InjectView(R.id.brand_video_icon)
    ImageButton brandVideo;

    @InjectView(R.id.brand_description_text)
    com.hyundai.teli.smartsales.views.HTextView brandDescription;

    public static  BrandStoryPager newInstance(){
        BrandStoryPager bBragment=new BrandStoryPager();
        return bBragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.brand_pager_item,null);
        ButterKnife.inject(getActivity());
        return view;
    }

    @OnClick(R.id.brand_video_icon)
    public void onVideoClicked(){

    }
}
