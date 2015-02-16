package com.hyundai.teli.smartsales.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

import com.hyundai.teli.smartsales.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MessageBoard extends ActionBarActivity {

    @InjectView(R.id.webview)
    WebView webview;

    String Url = "http://www.google.co.in";
    String temp_Url = "file:///android_asset/html/List.html";

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_popup);
        ButterKnife.inject(this);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAllowContentAccess(true);
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setAllowFileAccessFromFileURLs(true);
        webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webview.loadUrl(temp_Url);
    }
}
