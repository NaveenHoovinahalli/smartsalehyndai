package com.hyundai.teli.smartsales.activities;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.fragments.Photo;
import com.hyundai.teli.smartsales.fragments.Settings;
import com.hyundai.teli.smartsales.fragments.Showroom;
import com.hyundai.teli.smartsales.views.HTextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Consultation extends ActionBarActivity {

    @InjectView(R.id.show_room)
    HTextView mShowRoom;

    @InjectView(R.id.estimate)
    HTextView mEstimate;

    @InjectView(R.id.photo)
    HTextView mPhoto;

    @InjectView(R.id.settings)
    ImageView mSettings;

    @InjectView(R.id.catalogueMenu)
    ImageView mCatalogMenu;

    Settings settings;
    PopupWindow mQuickMenu;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = ".Hyundai";
    private Uri fileUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);
        Log.d("Consultation", "OnCreate");
        ButterKnife.inject(this);
        Intent receivedIntent = getIntent();
        if (receivedIntent != null && receivedIntent.hasExtra("SCREEN")) {
            String category = receivedIntent.getStringExtra("SCREEN");
            switch (category) {

                case "CONSULTATION":
                    loadShowRoom();
                    break;

                case "MY_INFO":
                    loadSettings(category);
                    break;

                case "CUSTOMER_MANAGEMENT":
                    loadSettings(category);
                    break;

                case "SURVEY":
                    loadSettings(category);
                    break;
                default:
                    loadShowRoom();
            }
        } else {
            loadShowRoom();
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Consultation","onResume");
        boolean isPaused = getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE)
                .getBoolean("PAUSED", false);
        boolean isCamera = getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE)
                .getBoolean("CAMERA", false);
        if (isPaused && !isCamera) {
            loadShowRoom();
            setSelected(0);
        }else if(isPaused && isCamera){
            Photo photo = new Photo();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, photo).commit();
        }
    }

    private void loadShowRoom() {
        mShowRoom.setTextColor(Color.parseColor("#657FBD"));
        Showroom showroom = new Showroom();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, showroom).commit();
    }

    private void loadSettings(String tab) {
        Bundle bundle = new Bundle();
        bundle.putString("TAB", tab);
        settings = new Settings();
        settings.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, settings).commit();
    }

    @OnClick({R.id.catalogueMenu, R.id.show_room, R.id.estimate, R.id.photo, R.id.settings})
    public void OnTabClickListener(View view) {
        switch (view.getId()) {
            case R.id.catalogueMenu:
                if (mQuickMenu == null || !mQuickMenu.isShowing())
                    showQuickMenu();
                else
                    mQuickMenu.dismiss();
                break;
            case R.id.show_room:
                loadShowRoom();
                setSelected(view.getId());
                break;
            case R.id.estimate:
                loadShowRoom();
                setSelected(view.getId());
                getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE)
                        .edit()
                        .putBoolean("ESTIMATE", true)
                        .commit();
                String carName = getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE).getString("CAR", "");
                if (!carName.isEmpty() && !carName.equals("")) {
                    Intent openCarDetails = new Intent(Consultation.this, CarDetails.class);
                    openCarDetails.putExtra("TAB", "ESTIMATE");
                    startActivity(openCarDetails);
                } else
                    Toast.makeText(getApplicationContext(), "Please Select a Car", Toast.LENGTH_SHORT).show();
                break;
            case R.id.photo:
                setSelected(view.getId());
                capturePhoto();
                break;
            case R.id.settings:
                loadSettings("UPDATE");
                setSelected(view.getId());
                break;
        }
    }

    private void setSelected(int id) {
        mShowRoom.setTextColor(Color.parseColor("#FFFFFF"));
        mEstimate.setTextColor(Color.parseColor("#FFFFFF"));
        mPhoto.setTextColor(Color.parseColor("#FFFFFF"));
        mSettings.setSelected(false);
        switch (id) {
            case R.id.show_room:
                mShowRoom.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.estimate:
                mEstimate.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.photo:
                mPhoto.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.settings:
                mSettings.setSelected(true);
                break;
        }
    }

    public void showRegistration() {
        Toast.makeText(getApplicationContext(), "Consultation", Toast.LENGTH_SHORT).show();
        settings.addRegistrationFragment();
    }

    public void hideRegistration() {
        settings.removeRegistrationFragment();
    }

    private void showQuickMenu() {

        if (mQuickMenu == null) {

            View popupView = getLayoutInflater().inflate(R.layout.pop_up_menu, null);
            mQuickMenu = new PopupWindow(popupView, RelativeLayout.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            mQuickMenu.setAnimationStyle(-1);
            mQuickMenu.setOutsideTouchable(true);
            mQuickMenu.setBackgroundDrawable(getResources().getDrawable(R.drawable.no_bg));

            ImageView menuFake = (ImageView) popupView.findViewById(R.id.fakeButton);
            ImageView menuHome = (ImageView) popupView.findViewById(R.id.menuHome);
            ImageView menuBrandStory = (ImageView) popupView.findViewById(R.id.menuBrandStory);
            ImageView menuConsultation = (ImageView) popupView.findViewById(R.id.menuConsultation);
            ImageView menuNDE = (ImageView) popupView.findViewById(R.id.menuNDE);
            ImageView menuBoard = (ImageView) popupView.findViewById(R.id.menuBoard);

            menuFake.setOnClickListener(menuClickListener);
            menuHome.setOnClickListener(menuClickListener);
            menuBrandStory.setOnClickListener(menuClickListener);
            menuConsultation.setOnClickListener(menuClickListener);
            menuNDE.setOnClickListener(menuClickListener);
            menuBoard.setOnClickListener(menuClickListener);
        }

        if (!mQuickMenu.isShowing()) {
            mQuickMenu.showAsDropDown(mCatalogMenu, 0, -112);
        }
    }

    private View.OnClickListener menuClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mQuickMenu.dismiss();

            switch (view.getId()) {

                case R.id.fakeButton:
                    mQuickMenu.dismiss();
                    break;
                case R.id.menuHome:
                    finish();
                    break;
                case R.id.menuBrandStory:
                    Intent openBrandStory = new Intent(Consultation.this, BrandStory.class);
                    startActivity(openBrandStory);
                    finish();
                    break;
                case R.id.menuConsultation:
                    break;
                case R.id.menuNDE:
                    Intent openNDE = new Intent(Consultation.this, NDE.class);
                    startActivity(openNDE);
                    finish();
                    break;
                case R.id.menuBoard:
                    Intent openMessageBoard = new Intent(Consultation.this, MessageBoard.class);
                    startActivity(openMessageBoard);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE)
                .edit()
                .putBoolean("PAUSED", true)
                .commit();
        getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE)
                .edit()
                .putBoolean("ESTIMATE", false)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE)
                .edit()
                .putBoolean("PAUSED", false)
                .commit();
    }

    public void capturePhoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE)
                .edit()
                .putString("PHOTO_PATH", fileUri.toString())
                .commit();

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Log.d("Consultation","onActivityResult");
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE)
                        .edit()
                        .putBoolean("CAMERA", true)
                        .commit();

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}