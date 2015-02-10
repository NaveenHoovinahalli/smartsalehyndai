package com.hyundai.teli.smartsales.fragments;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.hyundai.teli.smartsales.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by naveen on 10/2/15.
 */
public class NDEVideosPager extends Fragment{

    @InjectView(R.id.nde_videos_pager_item)
    VideoView playVideo;
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

    public static NDEVideosPager newInstance(){
        NDEVideosPager fragment=new NDEVideosPager();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.ndevideos_pager_item,null);
        ButterKnife.inject(this,view);
        Toast.makeText(getActivity(),"Inside Pager",Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        controller=new MediaController(getActivity());
        controller.setAnchorView(playVideo);
        downloadManager= (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);

        mPbar =new ProgressDialog(getActivity());
        mPbar.setTitle("Downloading video please wait...");
        mPbar.setCancelable(false);
    }

    @OnClick(R.id.nde_videos_pager_item)
    public void onVideoButtonClicked(){

        Uri videoUri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/raw/intro");
            playVideo.setVideoURI(videoUri);
            playVideo.start();

    }
}
