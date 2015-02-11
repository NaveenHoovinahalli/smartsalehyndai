package com.hyundai.teli.smartsales.activities;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.fragments.NDESales;
import com.hyundai.teli.smartsales.fragments.NDEVideos;
import com.hyundai.teli.smartsales.fragments.NewDealership;
import com.hyundai.teli.smartsales.fragments.Performance;
import com.hyundai.teli.smartsales.views.HTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class NDE extends ActionBarActivity {

    @InjectView(R.id.menuListView)
    RelativeLayout menuList;

    @InjectView(R.id.nde_video)
    HTextView ndeVideos;

    @InjectView(R.id.nde_dealer_vision)
    HTextView ndeDealerVision;

    @InjectView(R.id.nde_sales)
    HTextView ndeSales;

    @InjectView(R.id.nde_dealership_experience)
    HTextView ndeDealerExperience;

    private boolean menuClicked=false;

    public DownloadManager downloadManager;
    public long myDownloadReference ;
    public BroadcastReceiver rceiverDownloadComplete;
    public BroadcastReceiver receiverDownloadClicked;
    ProgressDialog mPbar;
    String zipPath="http://d359qmh6f57zs7.cloudfront.net/Tracking/HyundaiEliteNew.zip";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nde);
        ButterKnife.inject(this);
//        readJson();
//        DownloadFile();
        loadNdeVideos();
    }


    private void loadNdeVideos() {
        ndeVideos.setTextColor(Color.parseColor("#657FBD"));
        NDEVideos ndeVideosfragment=new NDEVideos();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,ndeVideosfragment).commit();
       }


    @OnClick({R.id.catalogueMenu,R.id.nde_video,R.id.nde_dealer_vision,R.id.nde_sales,R.id.nde_dealership_experience})
    public void onTabClickListner(View view){

        switch (view.getId()){
            case R.id.catalogueMenu:
                if(menuClicked) {
                    menuClicked = false;
                    menuList.setVisibility(View.INVISIBLE);
                }else{
                    menuList.setVisibility(View.VISIBLE);
                    menuClicked = true;
                }
                break;
            case R.id.nde_video:
                loadNdeVideos();
                setSelected(view.getId());
                break;
            case R.id.nde_dealer_vision:
                setSelected(view.getId());
//                DealerVision dealerVision = new DealerVision();
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, dealerVision).commit();
                Performance performance=new Performance();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,performance).commit();
                break;
            case R.id.nde_sales:
                setSelected(view.getId());
                NDESales ndeSales = new NDESales();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, ndeSales).commit();
                break;
            case R.id.nde_dealership_experience:
                setSelected(view.getId());
                NewDealership newDealership = new NewDealership();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, newDealership).commit();
                break;
        }

    }

    private void setSelected(int id) {
        ndeVideos.setTextColor(Color.parseColor("#FFFFFF"));
        ndeDealerVision.setTextColor(Color.parseColor("#FFFFFF"));
        ndeSales.setTextColor(Color.parseColor("#FFFFFF"));
        ndeDealerExperience.setTextColor(Color.parseColor("#FFFFFF"));

        switch (id){
            case R.id.nde_video:
                ndeVideos.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.nde_dealer_vision:
                ndeDealerVision.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.nde_sales:
                ndeSales.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.nde_dealership_experience:
                ndeDealerExperience.setTextColor(Color.parseColor("#657FBD"));
                break;
        }
    }

    public void readJson() {

        JSONObject obj = null;
        JSONArray m_jArry = null;

        try {
            obj = new JSONObject(loadJSONFromAsset());
            m_jArry = obj.getJSONArray("contents");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> m_li;

        for (int i = 0; i < m_jArry.length(); i++) {
            JSONObject jo_inside = null;
            try {
                jo_inside = m_jArry.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("contents"));
                String id = jo_inside.getString("_id");
                String category = jo_inside.getString("category");
                String path=jo_inside.getString("path");
                String isVideo=jo_inside.getString("is_video");
                m_li = new HashMap<String, String>();
                m_li.put("id", id);
                m_li.put("category", category);
                m_li.put("path",path);
                m_li.put("isVideo",isVideo);

                formList.add(m_li);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Add your values in your `ArrayList` as below:
        }
        for(int i=0;i<formList.size();i++){

        }

    }
    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("nde.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

   public void   DownloadFile(){
        downloadManager= (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        mPbar =new ProgressDialog(NDE.this);
        mPbar.setTitle("Downloading video please wait...");
        mPbar.setCancelable(false);

       File hyundaiPath = new File(Environment.getExternalStorageDirectory()
               + File.separator
               + "HYUNDAI");


       Uri uri= Uri.parse(zipPath);
       DownloadManager.Request request=new DownloadManager.Request(uri);
       request.setDescription("Video Download").
               setTitle("downloading");

              request.setDestinationInExternalFilesDir(NDE.this,
               Environment.DIRECTORY_DOWNLOADS, hyundaiPath+"/file.zip");

       request.setVisibleInDownloadsUi(true);

       request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
               DownloadManager.Request.NETWORK_MOBILE);

       myDownloadReference=downloadManager.enqueue(request);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter=new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        receiverDownloadClicked=new BroadcastReceiver() {
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

        registerReceiver(receiverDownloadClicked,intentFilter);

        // filter for download
        IntentFilter intentFilter1=new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        rceiverDownloadComplete=new BroadcastReceiver() {
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

                            try {
                                unzip(Environment.getExternalStorageDirectory() + "/" + "HYUNDAI" + "/" + "file.zip", Environment.getExternalStorageDirectory() + "/" + "HYUNDAI" + "/" + "file");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


//                            mPbar.setVisibility(View.GONE);
                            mPbar.dismiss();


                            if(mPbar.isShowing())
                                mPbar.dismiss();

                            break;
                        case DownloadManager.STATUS_FAILED:
                            Toast.makeText(NDE.this,
                                    "FAILED: " + reason,
                                    Toast.LENGTH_LONG).show();
                            break;
                        case DownloadManager.STATUS_PAUSED:
                            Toast.makeText(NDE.this,
                                    "PAUSED: " + reason,
                                    Toast.LENGTH_LONG).show();
                            break;
                        case DownloadManager.STATUS_PENDING:
                            Toast.makeText(NDE.this,
                                    "PENDING!",
                                    Toast.LENGTH_LONG).show();
                            break;
                        case DownloadManager.STATUS_RUNNING:
                            Toast.makeText(NDE.this,
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


    public static void unzip(String zipFile, String location) throws IOException {
        try {
            File f = new File(location);
            if (!f.isDirectory()) {
                f.mkdirs();
            }
            ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile));
            try {
                ZipEntry ze = null;
                while ((ze = zin.getNextEntry()) != null) {
                    String path = location + ze.getName();

                    if (ze.isDirectory()) {
                        File unzipFile = new File(path);
                        if (!unzipFile.isDirectory()) {
                            unzipFile.mkdirs();
                        }
                    } else {
                        FileOutputStream fout = new FileOutputStream(path, false);
                        try {
                            for (int c = zin.read(); c != -1; c = zin.read()) {
                                fout.write(c);
                            }
                            zin.closeEntry();
                            //readJsFile();
                        } finally {
                            fout.close();


                        }
                    }
                }
            } finally {
                zin.close();


            }
        } catch (Exception e) {
            Log.e("EXCEPTION", "Unzip exception", e);
        }
    }


}
