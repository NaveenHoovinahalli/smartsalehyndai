package com.hyundai.teli.smartsales.fragments;

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
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.BrandStory;
import com.hyundai.teli.smartsales.activities.PlayVideoActivity;
import com.hyundai.teli.smartsales.models.BrandStoryValues;
import com.hyundai.teli.smartsales.utils.AndroidUtils;
import com.hyundai.teli.smartsales.utils.Constants;
import com.hyundai.teli.smartsales.utils.HyDataManager;
import com.hyundai.teli.smartsales.views.HTextView;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class BrandStoryFragment extends Fragment {


    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_FILE = "image_file";
    private static final String VIDEO_FILE = "video_file";

    //Download
    ProgressDialog mPbar;
    MediaController controller;
    public DownloadManager downloadManager;
    public long myDownloadReference;
    public BroadcastReceiver rceiverDownloadComplete;
    public BroadcastReceiver receiverDownloadClicked;
    public String fileUrl;
    public String fileName;
    public Boolean isDownloadig = false;

    public File file, file1;
    public String videoFile;
    public String type;


    @InjectView(R.id.brand_rl)
    RelativeLayout playButton;

    @InjectView(R.id.brand_head)
    HTextView brandHead;

    @InjectView(R.id.video_thumbnail)
    ImageView videoThumbail;

    @InjectView(R.id.video_description)
    HTextView videoDescription;

    @InjectView(R.id.brand_video)
    VideoView videoView;

    String id;

    public static BrandStoryFragment newInstance(BrandStoryValues brandStoryValues) {
        BrandStoryFragment bBragment = new BrandStoryFragment();
        Bundle args = new Bundle();
        args.putString(ID, brandStoryValues.getId());
        args.putString(TITLE, brandStoryValues.getTitle());
        args.putString(DESCRIPTION, brandStoryValues.getDescription());
        args.putString(IMAGE_FILE, brandStoryValues.getImageFile());
        args.putString(VIDEO_FILE, brandStoryValues.getVideoFile());

        bBragment.setArguments(args);
        return bBragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_brand_pager, null);
        ButterKnife.inject(this, view);

        controller = new MediaController(getActivity());
        controller.setAnchorView(videoView);

        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);

        mPbar = new ProgressDialog(getActivity());
        mPbar.setTitle("Downloading  please wait...");
        mPbar.setCancelable(true);

        Bundle args = getArguments();
        if (args != null) {
            initilizeViews(args);
        }
        return view;
    }

    private void initilizeViews(Bundle args) {

        id = args.getString(ID);

        brandHead.setText(args.getString(TITLE));
        videoDescription.setText(args.getString(DESCRIPTION));
        String imagePath = Constants.BASE_URL + args.getString(IMAGE_FILE);
        String path = args.getString(IMAGE_FILE);

        videoFile = Constants.BASE_URL + args.getString(VIDEO_FILE);

        String[] separated = path.split("/");
        Log.d("SEPERATER", separated[separated.length - 1]);
        String imageName = separated[separated.length - 1];


        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + BrandStory.BASE_PATH);
        boolean value;
        if (!file.exists()) {
            value = file.mkdirs();
            Log.d("BrandStory", "created directory" + file);
        }


        File imagefile = new File(file + "/" + imageName);
        if (imagefile.exists()) {
            Log.d("BrandStory", "fileexists");
            HyDataManager.init(getActivity()).saveBrandImageName(id + "brandimage", fileName);
            videoThumbail.setImageURI(Uri.parse(imagefile.toString()));
        } else {
            if (imagefile.exists())
                imagefile.delete();
            Log.d("BrandStory", "filenotexists");
            if (AndroidUtils.isNetworkOnline(getActivity()))
                downloadVideo(imageName, imagePath, "image");
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick({R.id.brand_rl, R.id.image_button})
    public void onVideoBottonClicked() {
        Toast.makeText(getActivity(), "Video Selected", Toast.LENGTH_SHORT).show();

        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + BrandStory.BASE_PATH);

        String[] separated = videoFile.split("/");
        Log.d("SEPERATER", separated[separated.length - 1]);
        String videoName = separated[separated.length - 1];


        File nvideofile = new File(file + "/" + videoName);
        if (nvideofile.exists()) {
            Log.d("BrandStory", "fileexists" + nvideofile);
            HyDataManager.init(getActivity()).saveBrandVideoName(id + "brandvideo", fileName);
            Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
            intent.putExtra(PlayVideoActivity.VIDEO_URL, nvideofile.toString());
            startActivity(intent);
        } else {
            if (nvideofile.exists())
                nvideofile.delete();
            Log.d("BrandStory", "filenotexists");

            if (AndroidUtils.isNetworkOnline(getActivity()))
                downloadVideo(videoName, videoFile, "video");
        }

    }

    public void downloadVideo(String name, String Url, String type) {

        fileUrl = Url;
        fileName = name;
        this.type = type;
        Log.d("VIDEOId,VideoURL", "" + fileName + " " + fileUrl);

        mPbar.show();

        Uri uri = Uri.parse(fileUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDescription("Video Download").
                setTitle("downloading");

//        request.setDestinationInExternalFilesDir(getActivity(),
//                Environment.DIRECTORY_DOWNLOADS, fileName);

//        String path= String.valueOf(file1);
//
//        Uri downloadLocation = Uri.fromFile(new File(file1, fileName));
//
//            request.setDestinationInExternalPublicDir(
//                    path, fileName);


        Uri downloadLocatin = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + BrandStory.BASE_PATH + "/", fileName));
        Log.d("BrandStory", "location" + downloadLocatin);

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
                            if (type.equals("image")) {
                                HyDataManager.init(getActivity()).saveBrandImageName(id + "brandimage", fileName);

                                videoThumbail.setImageURI(Uri.parse(savedFilePath));
                            } else {
                                HyDataManager.init(getActivity()).saveBrandVideoName(id + "brandvideo", fileName);

                                Log.d("PlayVideo", "savedURLpath in video =" + savedFilePath);
                                Intent intentplay = new Intent(getActivity(), PlayVideoActivity.class);
                                intentplay.putExtra(PlayVideoActivity.VIDEO_URL, savedFilePath);
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
