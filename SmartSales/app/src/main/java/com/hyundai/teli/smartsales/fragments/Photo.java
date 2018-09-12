package com.hyundai.teli.smartsales.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.Consultation;

import org.brickred.socialauth.android.SocialAuthAdapter;

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

    @InjectView(R.id.btn_facebook)
    ImageButton facebook;

    SocialAuthAdapter socialAuthAdapter;
    private UiLifecycleHelper uiHelper;


    Bitmap bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_photo, null);
        ButterKnife.inject(this, view);


//        socialAuthAdapter=new SocialAuthAdapter(new ResponseListener());
        previewCapturedImage();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), null);
//        uiHelper.onCreate(savedInstanceState);
    }

    private void previewCapturedImage() {
        getActivity().getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE).edit().putBoolean("CAMERA", false).commit();
        String filePath = getActivity().getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE).getString("PHOTO_PATH", null);
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();

            options.inSampleSize = 8;
            fileUri = Uri.parse(filePath);
             bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

            capturedPhoto.setImageBitmap(bitmap);
            iconLayout.setVisibility(View.VISIBLE);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_retry, R.id.btn_facebook, R.id.btn_delete})
    public void OnClickListener(View view) {
        switch (view.getId()) {
            case R.id.btn_retry:
                ((Consultation) getActivity()).capturePhoto();
                break;
            case R.id.btn_facebook:
                setFacebook();
//                Toast.makeText(getActivity(), "Shared on Facebook", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:
                File file = new File(fileUri.getPath());
                if (file.exists())
                    file.delete();
                ((Consultation) getActivity()).capturePhoto();
                break;
        }
    }

    private void setFacebook() {

        try {
            FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(getActivity())

                    .setLink("http://www.hyundai.com/in/en/Main/index.html")
                    .setCaption("Test")
                    .setPicture("http://prov.intaract.mobi/intaractapp/image_pop?image_id=39626")
                    .build();
            uiHelper.trackPendingDialogCall(shareDialog.present());
        }catch (Exception e){

            Toast.makeText(getActivity(), "Please Install Facebook", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
            @Override
            public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
                Log.e("Activity", String.format("Error: %s", error.toString()));
            }

            @Override
            public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
                Log.i("Activity", "Success!");
            }
        });
    }


//    private void setFacebook() {
//        socialAuthAdapter.addProvider(SocialAuthAdapter.Provider.FACEBOOK,R.drawable.facebook);
//
//        try {
//            //new tab id
//            socialAuthAdapter.addConfig(SocialAuthAdapter.Provider.FACEBOOK, "1563474210557285", "302b574622f72423b9a94a3df816034f", null);
//
////          socialAuthAdapter.addConfig(SocialAuthAdapter.Provider.FACEBOOK, "297841130364674", "dc9c59d0c72d4f2533580e80ba4c2a59", null);
//
////            socialAuthAdapter.addConfig(SocialAuthAdapter.Provider.FACEBOOK, "409534545880372", "d3db8a47a7861c6bb759d849f9663ad6", null);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        socialAuthAdapter.enable(facebook);
//    }
//
//
//
//    private final class ResponseListener implements DialogListener {
//        @Override
//        public void onComplete(Bundle values) {
//
//            // Variable to receive message status
//            Log.d("Share-Bar", "Authentication Successful");
//            Log.d("Share-Bar","Inside response listner");
//
//
//            String provider= values.getString(SocialAuthAdapter.PROVIDER);
//
//            Log.d("Share-Bar","Connected to "+provider);
//
//            String path="https://techwithdianasaur.wordpress.com/2014/10/27/setting-up-socialauth-in-android-studio/";
//            socialAuthAdapter.updateStatus("Hello from Apps", new MessageListener(), true);
//
//        }
//
//        @Override
//        public void onError(SocialAuthError socialAuthError) {
//            Log.d("Share-Bar e", socialAuthError.getMessage());
//
//        }
//
//        @Override
//        public void onCancel() {
//            Log.d("Share-Bar", "Authentication Cancelled");
//
//        }
//
//        @Override
//        public void onBack() {
//            Log.d("Share-Bar", "Dialog Closed by pressing Back Key");
//
//        }
//    }
//
//
//
//
//
//
//
//
//    private final class MessageListener implements SocialAuthListener<Integer> {
//        @Override
//        public void onExecute(String provider, Integer t) {
//
//            Integer status = t;
//            Log.d("Share-Bar ", "" + status);
//            if (status.intValue() == 200 || status.intValue() == 201 || status.intValue() == 204)
//                Toast.makeText(getActivity(), "Message posted on " + provider, Toast.LENGTH_LONG).show();
//            else
//                Toast.makeText(getActivity(), "Message not posted on" + provider, Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        public void onError(SocialAuthError e) {
//
//        }
//    }

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
