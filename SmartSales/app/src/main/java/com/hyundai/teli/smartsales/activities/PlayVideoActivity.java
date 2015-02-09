package com.hyundai.teli.smartsales.activities;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.hyundai.teli.smartsales.R;

import butterknife.InjectView;

/**
 * Created by naveen on 9/1/15.
 */
public class PlayVideoActivity extends ActionBarActivity {

    @InjectView(R.id.videoPlay)
    VideoView videoView;

//    @InjectView(R.id.progress_bar)
//    ProgressBar mPbar;
    ProgressDialog mPbar;


    MediaController controller;
    public DownloadManager downloadManager;
    public long myDownloadReference ;
    public BroadcastReceiver rceiverDownloadComplete;
    public BroadcastReceiver receiverDownloadClicked;
    public String videoUrl;
    public String videoId;
    public Boolean isDownloadig=false;
    static final String VIDEO_URL="video_url";
    static final String VIDEO_ID="video_id";



    private String videoUrlplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoplay);
        controller=new MediaController(this);
        controller.setAnchorView(videoView);

        downloadManager= (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        mPbar =new ProgressDialog(PlayVideoActivity.this);
        mPbar.setTitle("Downloading video please wait...");
        mPbar.setCancelable(false);


        if(getIntent().hasExtra("URL_PLAY")){
            videoUrlplay=getIntent().getStringExtra("URL_PLAY");
            Log.d("SavedUrl", "" + videoUrlplay);
            videoView.setMediaController(controller);
            videoView.setVideoURI(Uri.parse(videoUrlplay));
            videoView.start();
        }

       }



}
