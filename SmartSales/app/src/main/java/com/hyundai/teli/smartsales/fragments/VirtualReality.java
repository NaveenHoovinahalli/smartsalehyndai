package com.hyundai.teli.smartsales.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.CarDetails;
import com.hyundai.teli.smartsales.models.VrExteriorMain;
import com.hyundai.teli.smartsales.utils.AndroidUtils;

import java.io.File;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class VirtualReality extends BaseFragment implements View.OnTouchListener {

    @InjectView(R.id.vr_flipper)
    ViewFlipper mVRFlipper;

    @InjectView(R.id.vr_layout)
    RelativeLayout vrLayout;


    @InjectView(R.id.colorPallet0)
    ImageView colorPallet0;
    @InjectView(R.id.colorPallet1)
    ImageView colorPallet1;
    @InjectView(R.id.colorPallet2)
    ImageView colorPallet2;
    @InjectView(R.id.colorPallet3)
    ImageView colorPallet3;
    @InjectView(R.id.colorPallet4)
    ImageView colorPallet4;
    @InjectView(R.id.colorPallet5)
    ImageView colorPallet5;
    @InjectView(R.id.colorPallet6)
    ImageView colorPallet6;
    @InjectView(R.id.colorPallet7)
    ImageView colorPallet7;
    @InjectView(R.id.colorPallet8)
    ImageView colorPallet8;
    @InjectView(R.id.colorPallet9)
    ImageView colorPallet9;
    @InjectView(R.id.colorPallet10)
    ImageView colorPallet10;

    private String Base_Path;
    private String VIRTUALEXTERIOR_MAIN_PATH;
    private VrExteriorMain vrExteriorMain;


    private static final int SWIPE_MIN_DISTANCE = 10;
    private static final int SWIPE_THRESHOLD_VELOCITY = 20;


    private ArrayList<String> vrexteriorImages = new ArrayList<String>();
    private int colorCount = 0;
    private ArrayList<String> colorImagePathselected = new ArrayList<String>();
    private ArrayList<String> colorImagePathNotselected = new ArrayList<String>();

    private ArrayList<ImageView> colorPallet = new ArrayList<ImageView>();


    GestureDetector detector;
    ImageView previousView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_virtual_reality, null);
        ButterKnife.inject(this, view);
        Base_Path = ((CarDetails) getActivity()).getBasePath() + File.separator;
        colorPallet.add(colorPallet0);
        colorPallet.add(colorPallet1);
        colorPallet.add(colorPallet2);
        colorPallet.add(colorPallet3);
        colorPallet.add(colorPallet4);
        colorPallet.add(colorPallet5);
        colorPallet.add(colorPallet6);
        colorPallet.add(colorPallet7);
        colorPallet.add(colorPallet8);
        colorPallet.add(colorPallet9);
        colorPallet.add(colorPallet10);

        VIRTUALEXTERIOR_MAIN_PATH = Base_Path + "vr_exterior/";

        fetchValues();

        mVRFlipper.setOnTouchListener(this);
        detector = new GestureDetector(new SwipeGestureDetector());

        ImageView hotspot_image = new ImageView(getActivity());
        hotspot_image.setImageResource(R.drawable.hotspot_blue);
        hotspot_image.setX(150.0f);
        hotspot_image.setY(150.0f);
        vrLayout.addView(hotspot_image);


        return view;
    }

    private void fetchValues() {
        parceJson();
        getValuesforColorSelection(0);
        setColorPallet();
        colorPallet.get(0).setImageURI(Uri.parse(colorImagePathselected.get(0)));

    }

    private void setViewFlipper() {

        mVRFlipper.removeAllViews();
        for (int i = 0; i < vrexteriorImages.size(); i++) {
            ImageView image = new ImageView(getActivity());
            Log.d("EXTERIOR", "IMAGE" + vrexteriorImages.get(i));
//            Picasso.with(getActivity()).load(Uri.parse("file://"+vrexteriorImages.get(i))).into(image);
            image.setImageURI(Uri.parse(vrexteriorImages.get(i)));
            mVRFlipper.addView(image);
        }
    }

    private void setColorPallet() {
        for (int i = 0; i < colorCount; i++) {
            colorPallet.get(i).setVisibility(View.VISIBLE);
            colorPallet.get(i).setImageURI(Uri.parse(colorImagePathNotselected.get(i)));

        }

    }

    @OnClick({R.id.colorPallet0, R.id.colorPallet1, R.id.colorPallet2, R.id.colorPallet3, R.id.colorPallet4, R.id.colorPallet5, R.id.colorPallet6,
            R.id.colorPallet7, R.id.colorPallet8, R.id.colorPallet9, R.id.colorPallet10})
    public void onColorButtonSelected(View view) {
        ImageView view1;
        switch (view.getId()) {
            case R.id.colorPallet0:
                getValuesforColorSelection(0);
                setColorPallet();
                view1 = (ImageView) view;
                view1.setImageURI(Uri.parse(colorImagePathselected.get(0)));
                break;
            case R.id.colorPallet1:
                getValuesforColorSelection(1);
                setColorPallet();
                view1 = (ImageView) view;
                view1.setImageURI(Uri.parse(colorImagePathselected.get(1)));
                break;
            case R.id.colorPallet2:
                getValuesforColorSelection(2);
                setColorPallet();
                view1 = (ImageView) view;
                view1.setImageURI(Uri.parse(colorImagePathselected.get(2)));
                break;
            case R.id.colorPallet3:
                getValuesforColorSelection(3);
                setColorPallet();
                view1 = (ImageView) view;
                view1.setImageURI(Uri.parse(colorImagePathselected.get(3)));
                break;
            case R.id.colorPallet4:
                getValuesforColorSelection(4);
                setColorPallet();
                view1 = (ImageView) view;
                view1.setImageURI(Uri.parse(colorImagePathselected.get(4)));
                break;
            case R.id.colorPallet5:
                getValuesforColorSelection(5);
                setColorPallet();
                view1 = (ImageView) view;
                view1.setImageURI(Uri.parse(colorImagePathselected.get(5)));
                break;
            case R.id.colorPallet6:
                getValuesforColorSelection(6);
                setColorPallet();
                view1 = (ImageView) view;
                view1.setImageURI(Uri.parse(colorImagePathselected.get(6)));
                break;
            case R.id.colorPallet7:
                getValuesforColorSelection(7);
                setColorPallet();
                view1 = (ImageView) view;
                view1.setImageURI(Uri.parse(colorImagePathselected.get(7)));
                break;
            case R.id.colorPallet8:
                getValuesforColorSelection(8);
                setColorPallet();
                view1 = (ImageView) view;
                view1.setImageURI(Uri.parse(colorImagePathselected.get(8)));
                break;
            case R.id.colorPallet9:
                getValuesforColorSelection(9);
                setColorPallet();
                view1 = (ImageView) view;
                view1.setImageURI(Uri.parse(colorImagePathselected.get(9)));
                break;
            case R.id.colorPallet10:
                getValuesforColorSelection(10);
                setColorPallet();
                view1 = (ImageView) view;
                view1.setImageURI(Uri.parse(colorImagePathselected.get(10)));
                break;
        }

    }


    private void getValuesforColorSelection(int i) {
        vrexteriorImages.clear();
        for (int j = 0; j < vrExteriorMain.getVrExteriorCars().get(i).getVrExteriorImageArray().size(); j++) {
            String imagePath = vrExteriorMain.getVrExteriorCars().get(i).getVrExteriorImageArray().get(j).getVeExteriorImage();
            String seperator[] = imagePath.split("/");
            vrexteriorImages.add(VIRTUALEXTERIOR_MAIN_PATH + seperator[seperator.length - 2] + "/" + seperator[seperator.length - 1]);
            Log.d("EXTERIOR", "IMAGELOAD" + VIRTUALEXTERIOR_MAIN_PATH + seperator[seperator.length - 2] + "/" + seperator[seperator.length - 1]);

        }
        setViewFlipper();

    }

    private void parceJson() {
        Gson gson = new Gson();
        String json = AndroidUtils.readJsonfromSdcard(Base_Path + "data.json");
        vrExteriorMain = gson.fromJson(json, VrExteriorMain.class);

        String savingGson = new Gson().toJson(vrExteriorMain);
        Log.d("SAVINGGSON", "" + savingGson);

        colorImagePathNotselected.clear();
        colorImagePathselected.clear();

        colorCount = vrExteriorMain.getVrExteriorCars().size();
        for (int i = 0; i < vrExteriorMain.getVrExteriorCars().size(); i++) {


            String image = vrExteriorMain.getVrExteriorCars().get(i).getColorPalletSelected();
            String seperator[] = image.split("/");
            String imageFinalPath = VIRTUALEXTERIOR_MAIN_PATH + seperator[seperator.length - 2] + "/" + seperator[seperator.length - 1];
            colorImagePathselected.add(imageFinalPath);
            Log.d("SELECTOR", imageFinalPath);

            String imagetwo = vrExteriorMain.getVrExteriorCars().get(i).getColorPalletNotSelected();
            String seperatortwo[] = imagetwo.split("/");
            String imagetwoFinalPath = VIRTUALEXTERIOR_MAIN_PATH + seperatortwo[seperatortwo.length - 2] + "/" + seperatortwo[seperatortwo.length - 1];
            colorImagePathNotselected.add(imagetwoFinalPath);
            Log.d("NOTSELECTOR", imagetwoFinalPath);


        }
    }

    @OnClick({R.id.vr_play, R.id.btn_vr_interior})
    public void OnVrPlayClicked(View view) {
        switch (view.getId()) {
            case R.id.vr_play:
                if (!mVRFlipper.isSelected()) {
                    mVRFlipper.setFlipInterval(100);
                    mVRFlipper.startFlipping();
                    mVRFlipper.setSelected(true);
                } else {
                    mVRFlipper.stopFlipping();
                    mVRFlipper.setSelected(false);
                }
                break;
            case R.id.btn_vr_interior:
                VirtualInterior virtualInterior = new VirtualInterior();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, virtualInterior).commit();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        detector.onTouchEvent(event);
        return true;
    }

    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
            try {
                if (me1.getX() - me2.getX() > SWIPE_MIN_DISTANCE) {
//                    mVRFlipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.left_in));
//                    mVRFlipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.left_out));
                    mVRFlipper.showPrevious();
                } else if (me2.getX() - me1.getX() > SWIPE_MIN_DISTANCE) {
//                    mVRFlipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.right_in));
//                    mVRFlipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.right_out));
                    mVRFlipper.showNext();
                }
                return true;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }
}