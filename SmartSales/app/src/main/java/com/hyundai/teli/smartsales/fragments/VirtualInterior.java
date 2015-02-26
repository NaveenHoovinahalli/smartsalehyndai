package com.hyundai.teli.smartsales.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.CarDetails;
import com.hyundai.teli.smartsales.models.VRInteriorMain;
import com.hyundai.teli.smartsales.utils.AndroidUtils;
import com.hyundai.teli.smartsales.utils.HyDataManager;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by nith on 2/19/15.
 */
public class VirtualInterior extends BaseFragment implements View.OnTouchListener {

    @InjectView(R.id.vr_int_flipper)
    ViewFlipper mVRIntFlipper;

    private static final int SWIPE_MIN_DISTANCE = 10;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    public String Base_Path;
    public VRInteriorMain vrInteriorMain;
    public String VRINTERIOR_MAIN_PATH;

    private String[] vrinteriorImages;

    GestureDetector detector;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_virtual_interior, null);
        ButterKnife.inject(this, view);
        Base_Path = ((CarDetails) getActivity()).getBasePath() + File.separator;
        VRINTERIOR_MAIN_PATH = Base_Path + "vr_interior/";

        setValues();
        mVRIntFlipper.setOnTouchListener(this);
        detector = new GestureDetector(new SwipeGestureDetector());


        return view;
    }

    private void setValues() {
        parceJson();
        for (int i = 0; i < vrinteriorImages.length; i++) {
            ImageView image = new ImageView(getActivity());
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageURI(Uri.parse(vrinteriorImages[i]));
            mVRIntFlipper.addView(image);
        }
    }

    private void parceJson() {

        Gson gson = new Gson();
        String json = AndroidUtils.readJsonfromSdcard(Base_Path + "data.json");
        vrInteriorMain = gson.fromJson(json, VRInteriorMain.class);
        for (int i = 0; i < vrInteriorMain.getVrInteriorMain().size(); i++) {
            vrinteriorImages = new String[vrInteriorMain.getVrInteriorMain().get(i).getVrInteriorArray().size()];
            for (int j = 0; j < vrInteriorMain.getVrInteriorMain().get(i).getVrInteriorArray().size(); j++) {

                String image = vrInteriorMain.getVrInteriorMain().get(i).getVrInteriorArray().get(j).getInteriorImage();
                String seperator[] = image.split("/");
                String imageFinalPath = VRINTERIOR_MAIN_PATH + seperator[seperator.length - 1];

                vrinteriorImages[j] = imageFinalPath;

            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        detector.onTouchEvent(event);
        return true;
    }

    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
            try {
                if (me1.getX() - me2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    if (mVRIntFlipper.indexOfChild(mVRIntFlipper.getCurrentView()) == vrinteriorImages.length - 1) {
                        return false;
                    }
                    mVRIntFlipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_left_in));
                    mVRIntFlipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_left_out));
                    mVRIntFlipper.showNext();
                } else if (me2.getX() - me1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    if (mVRIntFlipper.indexOfChild(mVRIntFlipper.getCurrentView()) == 0) {
                        return false;
                    }
                    mVRIntFlipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_right_in));
                    mVRIntFlipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_right_out));
                    mVRIntFlipper.showPrevious();
                }
                return true;

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }


    }

    @OnClick(R.id.btn_vr_exterior)
    public void onClickListener(View view) {
        VirtualReality virtualReality = new VirtualReality();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, virtualReality).commit();
    }
}
