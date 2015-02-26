package com.hyundai.teli.smartsales.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.Consultation;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Photo extends BaseFragment {

    @InjectView(R.id.photo_captured)
    ImageView capturedPhoto;

    @InjectView(R.id.photo_view_layout)
    RelativeLayout iconLayout;
    private Uri fileUri;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_photo, null);
        ButterKnife.inject(this, view);
        previewCapturedImage();
        return view;
    }

    private void previewCapturedImage() {
        getActivity().getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE).edit().putBoolean("CAMERA", false).commit();
        String filePath = getActivity().getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE).getString("PHOTO_PATH", null);
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();

            options.inSampleSize = 8;
            fileUri = Uri.parse(filePath);
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

            capturedPhoto.setImageBitmap(bitmap);
            iconLayout.setVisibility(View.VISIBLE);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_retry,R.id.btn_facebook,R.id.btn_delete})
    public void OnClickListener(View view){
        switch(view.getId()){
            case R.id.btn_retry:
                ((Consultation)getActivity()).capturePhoto();
                break;
            case R.id.btn_facebook:
                Toast.makeText(getActivity(), "Shared on Facebook", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:
                File file = new File(fileUri.getPath());
                if(file.exists())
                    file.delete();
                ((Consultation)getActivity()).capturePhoto();
                break;
        }
    }
}

/*


@InjectView(R.id.preview)
FrameLayout previewLayout;

@InjectView(R.id.photo_view_layout)
RelativeLayout photoViewLayout;

@InjectView(R.id.photo_focus)
ImageView photoFocus;

Camera camera;
Preview mPreview;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, null);
        ButterKnife.inject(this, view);
        mPreview = new Preview(getActivity());
        previewLayout.addView(mPreview);
        return view;
    }

    @OnClick(R.id.photo_shutter)
    public void onShutterClicked(View view) {
        photoFocus.setVisibility(View.GONE);
        mPreview.camera.takePicture(shutterCallback, rawCallback, jpegCallback);
        photoViewLayout.setVisibility(View.VISIBLE);
    }

Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() {
    public void onShutter() {
        Log.d("Photo", "onShutter");
    }
};

*/
/**
 * Handles data for raw picture
 *//*

Camera.PictureCallback rawCallback = new Camera.PictureCallback() {
    public void onPictureTaken(byte[] data, Camera camera) {
        Log.d("Photo", "onPictureTaken");
    }
};

*/
/**
 * Handles data for jpeg picture
 *//*

Camera.PictureCallback jpegCallback = new Camera.PictureCallback() {

    public void onPictureTaken(byte[] data, Camera camera) {
        FileOutputStream outStream = null;
        try {
            // write to local sandbox file system
//				outStream = CameraDemo.this.openFileOutput(String.format("%d.jpg", System.currentTimeMillis()), 0);
            // Or write to sdcard
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            File file = new File(Environment.getExternalStorageDirectory()+ File.separator + ".Hyundai/image.jpg");
            file.createNewFile();
            outStream = new FileOutputStream(file);
            outStream.write(bytes.toByteArray());
            outStream.close();
        } catch (FileNotFoundException e) {
            Log.d("Photo", "FileNotFoundException");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("Photo", "IOException");
            e.printStackTrace();
        }
    }
};*/
