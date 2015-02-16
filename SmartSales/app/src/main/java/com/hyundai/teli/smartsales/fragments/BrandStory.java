package com.hyundai.teli.smartsales.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.PlayVideoActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class BrandStory extends Fragment {

//    @InjectView(R.id.brand_video_icon)
//    ImageButton brandVideo;
//
//    @InjectView(R.id.brand_description_text)
//    com.hyundai.teli.smartsales.views.HTextView brandDescription;

    @InjectView(R.id.brand_rl)
    RelativeLayout playButton;

    public static BrandStory newInstance(){
        BrandStory bBragment=new BrandStory();
        return bBragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.brand_pager_item,null);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.brand_rl)
    public void onVideoBottonClicked(){
        Toast.makeText(getActivity(), "Video Selected", Toast.LENGTH_SHORT).show();
        Intent playVideo=new Intent(getActivity(), PlayVideoActivity.class);
       playVideo.putExtra("video_id","1");
        playVideo.putExtra("video_url","http://d359qmh6f57zs7.cloudfront.net/videos/Hyundai.mp4");
        startActivity(playVideo);

    }
}
