package com.hyundai.teli.smartsales.fragments;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.CarDetails;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by naveen on 2/8/15.
 */
public class Compare extends BaseFragment {

    @InjectView(R.id.compare_webview)
    WebView compareWebview;

    @InjectView(R.id.progress_compare)
    ProgressBar progressBar;

    String COMPARE_MAIN_PATH;
    String Base_Path;
    File file;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comparing, null);
        ButterKnife.inject(this, view);
        Base_Path = ((CarDetails) getActivity()).getBasePath() + File.separator;
        COMPARE_MAIN_PATH=Base_Path+"compare/";
        file=new File(COMPARE_MAIN_PATH);
        if(!file.exists())
            file.mkdirs();
        copyFile();
        readFontFile(getActivity(),file);
        loadWebView(file);
        return view;
    }

    private void copyFile() {

//        String sourcePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TongueTwister/tt_temp.3gp";
//        File source = new File(sourcePath);
//
//        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TongueTwister/tt_1A.3gp";
//        File destination = new File(destinationPath);
//        try
//        {
//            FileUtils.copyFile(source, destination);
//            FileUtils.copy(source,destination);
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }

    }

    private void loadWebView(File file) {

        compareWebview.getSettings().setJavaScriptEnabled(true);
        compareWebview.getSettings().setAllowContentAccess(true);
        compareWebview.getSettings().setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= 16) {
            compareWebview.getSettings().setAllowFileAccessFromFileURLs(true);
            compareWebview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }

        compareWebview.clearCache(true);
        compareWebview.setWebChromeClient(new MyWebChromeClient());
        compareWebview.setWebViewClient(new MyWebViewClient());
        compareWebview.loadUrl("about:blank");
        compareWebview.loadUrl("file://" + file + "/index.html");
        Log.d("Specification", "HTML path::" + file + "/index.html");

    }

    private class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            progressBar.setProgress(newProgress);
            super.onProgressChanged(view, newProgress);
        }
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressBar.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            progressBar.setVisibility(View.GONE);
            super.onPageFinished(view, url);
        }
    }

    public static void readFontFile(Context context,File path) {

        AssetManager assetManager = context.getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open("fonts/ModernH-Bold.ttf");

            if(path.exists()){
                File fontPath = new File(path+"/" +"hyundaib.ttf");
                Log.d("FILESpec","path "+fontPath);
                if(!fontPath.exists()){
                    Log.d("FILESpec","pathexits  false");
                    out = new FileOutputStream(fontPath);
                    copyFile(in, out);
                }
                fontPath.setReadable(true);
                fontPath.setWritable(true);
                fontPath.setExecutable(true);
            }
        } catch(IOException e) {
            Log.e("FILESpec", "Failed to copy asset file: ", e);
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // NOOP
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // NOOP
                }
            }
        }
    }


    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }


}
