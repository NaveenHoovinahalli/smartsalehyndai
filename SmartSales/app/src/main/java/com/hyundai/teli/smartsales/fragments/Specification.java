package com.hyundai.teli.smartsales.fragments;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.CarDetails;
import com.hyundai.teli.smartsales.models.SpecificationMain;
import com.hyundai.teli.smartsales.utils.AndroidUtils;
import com.hyundai.teli.smartsales.utils.HyDataManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Specification extends BaseFragment  {

//    @InjectView(R.id.specification_pager)
//    ViewPager specificationPager;
//
//    @InjectView(R.id.specification_list)
//    ListView specificationList;

    @InjectView(R.id.specification_webview)
    WebView specWebView;
    @InjectView(R.id.specBar)
    ProgressBar specProgress;

    public String Base_Path;
    public static String SPECIFICATION_MAIN_PATH;

    ArrayList<String> images;
    ArrayList<PerformanceFragment> fragments;
    int[] image = new int[]{R.drawable.spec1, R.drawable.spec2, R.drawable.spec3, R.drawable.spec4};
    ArrayList<String> specificationListValues;
    View previousView;
    File file2;
    SpecificationMain specificationMain;
    FeatureMain featureMain;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_specification, null);
        ButterKnife.inject(this, view);
        Base_Path = ((CarDetails)getActivity()).getBasePath() + File.separator;
        SPECIFICATION_MAIN_PATH= Environment.getExternalStorageDirectory().getAbsolutePath()+"/Hyundai/specification/";
        file2=new File(SPECIFICATION_MAIN_PATH);
        if(!file2.exists())
             file2.mkdirs();

        fetchValues();
//        getActivity().finish();
//        setList();
//        setPager();
//        specificationPager.setOnPageChangeListener(this);

        return view;
    }

    private void fetchValues() {
        parceJsonandwritetosdcard();


    }

    private void parceJsonandwritetosdcard() {

        Gson gson=new Gson();
        String json= AndroidUtils.readJsonfromSdcard(Base_Path + "data.json");

        specificationMain=gson.fromJson(json,SpecificationMain.class);
       String specJson= gson.toJson(specificationMain.getSpecificationArrays());
        Log.d("SPECIFICATION",specJson);
        writeToSdcard(specJson,"specification.json",0);

       featureMain=gson.fromJson(json,FeatureMain.class);
        String feaJson= gson.toJson(featureMain.featureArrays);
        Log.d("SPECIFICATION",feaJson);
        writeToSdcard(feaJson,"feature.json",1);




    }

    private void writeToSdcard(String jsonResponse,String fileName,int flag) {
        try {


            File file = new File(file2 +"/"+fileName);
            Log.d("NDE", "write to sd card" + file);
            if (file.exists())
                file.delete();

            file.createNewFile();
            file.setReadable(true);
            file.setExecutable(true);
            file.setWritable(true);

            Log.d("NDE", "write to sd card");
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter =
                    new OutputStreamWriter(fOut);
            myOutWriter.append(jsonResponse);
            myOutWriter.close();
            fOut.close();
        } catch (Exception e) {
            Log.d("NDE","Exception" + e.toString());
        }
        if(flag>0)
        loadWebView(file2);

    }

    private  void loadWebView(File file) {

        specWebView.getSettings().setJavaScriptEnabled(true);
        specWebView.getSettings().setAllowContentAccess(true);
        specWebView.getSettings().setAllowFileAccess(true);
        if(Build.VERSION.SDK_INT >= 16){
            specWebView.getSettings().setAllowFileAccessFromFileURLs(true);
            specWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }

        specWebView.clearCache(true);
        specWebView.setWebChromeClient(new MyWebChromeClient());
        specWebView.setWebViewClient(new MyWebViewClient());
        specWebView.loadUrl("about:blank");
        specWebView.loadUrl("file:/"+file+"/index.html");
        Log.d("Specification","HTML path::" +file+"/index.html");

    }
    private  class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            specProgress.setProgress(newProgress);
            super.onProgressChanged(view, newProgress);
        }
    }

    private  class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            specProgress.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            specProgress.setVisibility(View.GONE);
            super.onPageFinished(view, url);
        }
    }
}

