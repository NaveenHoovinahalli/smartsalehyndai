package com.hyundai.teli.smartsales.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.hyundai.teli.smartsales.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Home extends Activity {

    @InjectView(R.id.movie)
    VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        playVideo();
    }

    private void playVideo() {
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/raw/video");
        mVideoView.setVideoURI(videoUri);
        DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mVideoView.getLayoutParams();
        params.width =  metrics.widthPixels;
        params.height = metrics.heightPixels;
        mVideoView.setLayoutParams(params);
        mVideoView.requestFocus();
        mVideoView.seekTo(0);
        mVideoView.start();
    }

    @OnClick({R.id.brand_story, R.id.consultation, R.id.nde, R.id.message_board,
            R.id.myInfo, R.id.customer_management, R.id.survey})
    public void onClickListener(View view){
                Intent openConsultation = new Intent(Home.this, Consultation.class);
        switch(view.getId()){
            case R.id.brand_story:
                Intent openBrandStory = new Intent(Home.this, BrandStory.class);
                startActivity(openBrandStory);
                break;

            case R.id.consultation:
                openConsultation.putExtra("SCREEN", "CONSULTATION");
                startActivity(openConsultation);
                break;

            case R.id.nde:
                Intent openNDE = new Intent(Home.this, NDE.class);
                startActivity(openNDE);
                break;

            case R.id.message_board:
                Intent openMessageBoard = new Intent(Home.this, MessageBoard.class);
                startActivity(openMessageBoard);
                break;

            case R.id.myInfo:
                openConsultation.putExtra("SCREEN", "MY_INFO");
                startActivity(openConsultation);
                break;

            case R.id.customer_management:
                openConsultation.putExtra("SCREEN", "CUSTOMER_MANAGEMENT");
                startActivity(openConsultation);
                break;

            case R.id.survey:
                openConsultation.putExtra("SCREEN", "SURVEY");
                startActivity(openConsultation);
                break;
        }
    }
}