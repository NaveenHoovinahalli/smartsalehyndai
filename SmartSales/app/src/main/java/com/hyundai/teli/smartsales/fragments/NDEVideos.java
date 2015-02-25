package com.hyundai.teli.smartsales.fragments;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.NDE;
import com.hyundai.teli.smartsales.activities.PlayVideoActivity;
import com.hyundai.teli.smartsales.models.NDEMain;
import com.hyundai.teli.smartsales.utils.AndroidUtils;
import com.hyundai.teli.smartsales.utils.Constants;
import com.hyundai.teli.smartsales.utils.HyDataManager;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class NDEVideos extends Fragment {

    @InjectView(R.id.nde_pager_image)
    ImageView imageView;

    @InjectView(R.id.nde_pager_video)
    ImageView videoViewIcon;


    ProgressDialog mPbar;
    MediaController controller;
    public DownloadManager downloadManager;
    public long myDownloadReference;
    public BroadcastReceiver rceiverDownloadComplete;
    public BroadcastReceiver receiverDownloadClicked;
    public String fileUrl;
    public String fileName;
    public Boolean isDownloadig = false;
    File file;
    String type;


    String isVideo;
    String imagepath;
    String videoPath;
    String id;


    @SuppressLint("ValidFragment")
    public NDEVideos(NDEMain ndeDetail) {

        isVideo = ndeDetail.getIsVideo();
        imagepath = ndeDetail.getVideoThumbnail();
        id = ndeDetail.getId();
        videoPath=ndeDetail.getVideoFile();

    }

    public NDEVideos() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ndevideos_pager_item, null);
        ButterKnife.inject(this, view);


        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);

        mPbar = new ProgressDialog(getActivity());
        mPbar.setTitle("Downloading  please wait...");
        mPbar.setCancelable(false);


        setValues();

        return view;
    }

    private void setValues() {

        if(isVideo.toLowerCase().equals("true"))
            videoViewIcon.setVisibility(View.VISIBLE);

        String imageServerPath= Constants.BASE_URL+imagepath;

        String[] separated = imagepath.split("/");
        Log.d("SEPERATER",separated[separated.length-1]);
        String imageName=separated[separated.length-1];



        file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ NDE.BASE_PATH);
        boolean value;
        if(!file.exists()) {
            value = file.mkdirs();
            Log.d("BrandStory","created directory"+file);
        }

        File imagefile=new File(file+"/"+imageName);
        if(imagefile.exists() && imageName.equals(HyDataManager.init(getActivity()).getNdeImageName(id+"image"))) {
           imageView.setImageURI(Uri.parse(imagefile.toString()));
        }else {
            if(imagefile.exists())
                imagefile.delete();
            Log.d("BrandStory","filenotexists");
            if(AndroidUtils.isNetworkOnline(getActivity()))
                downloadVideo(imageName, imageServerPath,"image");
        }


    }





    @OnClick({R.id.nde_pager_image,R.id.nde_pager_video})
    public void onImageClicked() {

        if(isVideo.toLowerCase().equals("true")) {

            Toast.makeText(getActivity(), "video", Toast.LENGTH_SHORT).show();

            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + NDE.BASE_PATH);

            String[] separated = videoPath.split("/");
            Log.d("SEPERATER", separated[separated.length - 1]);
            String videoName = separated[separated.length - 1];

            File videofile = new File(file + "/" + videoName);
            if (videofile.exists() && videoName.equals(HyDataManager.init(getActivity()).getNdeVideoName(id+"video"))) {
                Log.d("BrandStory", "fileexists");

                Intent intent=new Intent(getActivity(), PlayVideoActivity.class);
                intent.putExtra(PlayVideoActivity.VIDEO_URL,videofile.toString());
                startActivity(intent);

            } else {
                if(videofile.exists())
                    videofile.delete();
                String videoServerUrl = Constants.BASE_URL + videoPath;
                Log.d("BrandStory", "filenotexists");
                if(AndroidUtils.isNetworkOnline(getActivity()))
                    downloadVideo(videoName, videoServerUrl, "video");

            }
        }

    }


    public void downloadVideo(String name, String Url,String type) {

        fileUrl = Url;
        fileName = name;
        this.type=type;
        Log.d("VIDEOId,VideoURL", "" + fileName + " " + fileUrl);

        mPbar.show();

        Uri uri = Uri.parse(fileUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDescription("Download").
                setTitle("downloading");


        Uri downloadLocatin=Uri.fromFile(new File(Environment.getExternalStorageDirectory()+NDE.BASE_PATH+"/",fileName));
        Log.d("NDE","location"+downloadLocatin);

        request.setDestinationUri(downloadLocatin);


        request.setVisibleInDownloadsUi(true);

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                DownloadManager.Request.NETWORK_MOBILE);

        myDownloadReference = downloadManager.enqueue(request);



    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("VideoURL", "" + fileUrl);

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

        getActivity().registerReceiver(receiverDownloadClicked, intentFilter);

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

//                            mPbar.setVisibility(View.GONE);
                            mPbar.dismiss();
                            isDownloadig = false;
                            if(type.equals("image")) {
                                HyDataManager.init(getActivity()).saveNdeImageName(id+"image",fileName);

                                imageView.setImageURI(Uri.parse(savedFilePath));
                            }else {

                                HyDataManager.init(getActivity()).saveNdeVideoName(id+"video",fileName);
                                Intent intentplay=new Intent(getActivity(), PlayVideoActivity.class);
                                intentplay.putExtra(PlayVideoActivity.VIDEO_URL,savedFilePath);
                                startActivity(intentplay);
                            }
                            if (mPbar.isShowing())
                                mPbar.dismiss();

                            break;
                    }
                    cursor.close();
//                    mPbar.setVisibility(View.GONE);
                    mPbar.dismiss();

                }
            }
        };
        getActivity().registerReceiver(rceiverDownloadComplete, intentFilter1);

    }

    @Override
    public void onPause() {
        super.onPause();

        getActivity().unregisterReceiver(rceiverDownloadComplete);
        getActivity().unregisterReceiver(receiverDownloadClicked);
    }

}
