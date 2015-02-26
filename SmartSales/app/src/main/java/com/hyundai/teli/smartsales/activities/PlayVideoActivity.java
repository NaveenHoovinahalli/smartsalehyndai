package com.hyundai.teli.smartsales.activities;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.hyundai.teli.smartsales.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class PlayVideoActivity extends ActionBarActivity {

    @InjectView(R.id.videoPlay)
    VideoView videoView;

    //    @InjectView(R.id.progress_bar)
//    ProgressBar mPbar;
    ProgressDialog mPbar;


    MediaController controller;
    public DownloadManager downloadManager;
    public long myDownloadReference;
    public BroadcastReceiver rceiverDownloadComplete;
    public BroadcastReceiver receiverDownloadClicked;
    public String videoUrl;
    public String videoId;
    public Boolean isDownloadig = false;
    public static final String VIDEO_URL = "video_url";
    public static final String VIDEO_ID = "video_id";


    private String videoUrlplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoplay);
        ButterKnife.inject(this);
        controller = new MediaController(this);
        controller.setAnchorView(videoView);

        Log.d("PlayVideo", "oncreate");


        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        mPbar = new ProgressDialog(PlayVideoActivity.this);
        mPbar.setTitle("Downloading video please wait...");
        mPbar.setCancelable(false);


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) videoView.getLayoutParams();
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;
        videoView.setLayoutParams(params);


        if (getIntent().hasExtra(VIDEO_URL)) {
            String url = getIntent().getStringExtra(VIDEO_URL);
            Log.d("PlayVideo", "url" + url);
            videoView.setMediaController(controller);
            videoView.setVideoURI(Uri.parse(url));
            videoView.start();

        }


//        if (getIntent().hasExtra("video_url")) {
//            Log.d("VIDEOId,VideoURL", getIntent().getStringExtra(VIDEO_URL));
//
//            downloadVideo(getIntent().getStringExtra(VIDEO_ID), getIntent().getStringExtra(VIDEO_URL));
//        }


    }

    public void downloadVideo(String Id, String Url) {

        videoUrl = Url;
        videoId = Id;


        SharedPreferences prefs = getSharedPreferences("HYUNDAI_PREFERENCE", MODE_PRIVATE);
        String getSavedPath = prefs.getString(videoId, null);
        if (getSavedPath == null) {
            Log.d("VIDEO", "Downloading first time");

//            mPbar.setVisibility(View.VISIBLE);
            mPbar.show();

            Uri uri = Uri.parse(videoUrl);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setDescription("Video Download").
                    setTitle("downloading");

            request.setDestinationInExternalFilesDir(PlayVideoActivity.this,
                    Environment.DIRECTORY_DOWNLOADS, "hyundai.mp4");

            request.setVisibleInDownloadsUi(true);

            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                    DownloadManager.Request.NETWORK_MOBILE);

            myDownloadReference = downloadManager.enqueue(request);

        } else {
            Log.d("VIDEO", "Video Already downloaded");
//            mPbar.setVisibility(View.INVISIBLE);
            mPbar.dismiss();
            videoView.setMediaController(controller);
            videoView.setVideoURI(Uri.parse(getSavedPath));
            videoView.start();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("VideoURL", "" + videoUrl);

        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        receiverDownloadClicked = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String extraId = DownloadManager
                        .EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS;
                long[] references = intent.getLongArrayExtra(extraId);
                for (long reference : references) {
                    if (reference == myDownloadReference) {
//                        do something with the download file
                        Log.d("Prefernce", "" + references.length);
                        Log.d("Refrence", "" + reference);

                    }
                }
            }
        };

        registerReceiver(receiverDownloadClicked, intentFilter);

        // filter for download
        IntentFilter intentFilter1 = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        rceiverDownloadComplete = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                long reference = intent.getLongExtra(DownloadManager
                        .EXTRA_DOWNLOAD_ID, -1);
                if (myDownloadReference == reference) {
//                    do something with the download file
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(reference);
                    Cursor cursor = downloadManager.query(query);

                    cursor.moveToFirst();
//                        get the status of the download
                    int columnIndex = cursor.getColumnIndex(DownloadManager
                            .COLUMN_STATUS);
                    int status = cursor.getInt(columnIndex);

                    int fileNameIndex = cursor.getColumnIndex(DownloadManager
                            .COLUMN_LOCAL_FILENAME);
                    String savedFilePath = cursor.getString(fileNameIndex);

//                        get the reason - more detail on the status
                    int columnReason = cursor.getColumnIndex(DownloadManager
                            .COLUMN_REASON);
                    int reason = cursor.getInt(columnReason);

                    switch (status) {
                        case DownloadManager.STATUS_SUCCESSFUL:

                            Log.d("PlayVideo", "savedURLpath =" + savedFilePath);

                            SharedPreferences.Editor editor = getSharedPreferences("HYUNDAI_PREFERENCE", MODE_PRIVATE).edit();
                            editor.putString(videoId, savedFilePath);
                            editor.apply();
//                            mPbar.setVisibility(View.GONE);
                            mPbar.dismiss();
                            isDownloadig = false;

                            videoView.setVideoURI(Uri.parse(savedFilePath));
                            videoView.start();
                            if (mPbar.isShowing())
                                mPbar.dismiss();

                            break;
                        case DownloadManager.STATUS_FAILED:
                            Toast.makeText(PlayVideoActivity.this,
                                    "FAILED: " + reason,
                                    Toast.LENGTH_LONG).show();
                            break;
                        case DownloadManager.STATUS_PAUSED:
                            Toast.makeText(PlayVideoActivity.this,
                                    "PAUSED: " + reason,
                                    Toast.LENGTH_LONG).show();
                            break;
                        case DownloadManager.STATUS_PENDING:
                            Toast.makeText(PlayVideoActivity.this,
                                    "PENDING!",
                                    Toast.LENGTH_LONG).show();
                            break;
                        case DownloadManager.STATUS_RUNNING:
                            Toast.makeText(PlayVideoActivity.this,
                                    "RUNNING!",
                                    Toast.LENGTH_LONG).show();
                            break;
                    }
                    cursor.close();
//                    mPbar.setVisibility(View.GONE);
                    mPbar.dismiss();

                }
            }
        };
        registerReceiver(rceiverDownloadComplete, intentFilter1);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(rceiverDownloadComplete);
        unregisterReceiver(receiverDownloadClicked);
    }


}
