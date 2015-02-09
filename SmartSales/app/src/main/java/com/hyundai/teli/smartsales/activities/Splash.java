package com.hyundai.teli.smartsales.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.hyundai.teli.smartsales.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class Splash extends Activity implements MediaPlayer.OnCompletionListener {

    @InjectView(R.id.video_view)
    VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/raw/intro");
        mVideoView.setVideoURI(videoUri);

        DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mVideoView.getLayoutParams();
        params.width =  metrics.widthPixels;
        params.height = metrics.heightPixels;
        mVideoView.setLayoutParams(params);

        mVideoView.requestFocus();
        mVideoView.seekTo(0);
        mVideoView.start();
        mVideoView.setOnCompletionListener(this);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Toast.makeText(getApplicationContext(), "Splash Ended", Toast.LENGTH_SHORT).show();
        boolean isRegistered = getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE).getBoolean("SIGN_UP", false);
        if(isRegistered){
            Intent openHome = new Intent(this, Home.class);
            startActivity(openHome);
        }else{
            Intent openSignUp = new Intent(this, SignUp.class);
            startActivity(openSignUp);
        }
    }
}
