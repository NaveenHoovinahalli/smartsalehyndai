package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.CarDetails;
import com.hyundai.teli.smartsales.models.StyleExteriorMain;
import com.hyundai.teli.smartsales.utils.AndroidUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class StyleExterior extends BaseFragment implements View.OnTouchListener {

    @InjectView(R.id.exterior_flipper)
    ViewFlipper mExteriorFlipper;

    @InjectView(R.id.interior_button)
    ImageButton interiourButton;

    ArrayList<String> exteriorImages = new ArrayList<String>();
    StyleExteriorMain styleExteriorMain;
    private String Base_Path;
    private String STYLEEXTERIOR_MAIN_PATH;

    private static final int SWIPE_MIN_DISTANCE = 100;
    private static final int SWIPE_THRESHOLD_VELOCITY = 10;


    GestureDetector detector;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_style_exterior, null);
        ButterKnife.inject(this, view);
        Toast.makeText(getActivity(), "Path::" + ((CarDetails) getActivity()).getBasePath(), Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), "CAR::" + ((CarDetails) getActivity()).getCarName(), Toast.LENGTH_SHORT).show();
        Base_Path = ((CarDetails) getActivity()).getBasePath() + File.separator;
        STYLEEXTERIOR_MAIN_PATH = Base_Path + "style_exterior/";
        setValues();
        mExteriorFlipper.setOnTouchListener(this);
        detector = new GestureDetector(new SwipeGestureDetector());

        return view;
    }

    private void setValues() {
        parceJson();
        setFlipper();
    }

    private void setFlipper() {
        mExteriorFlipper.removeAllViews();
        for (int i = 0; i < exteriorImages.size(); i++) {
            ImageView image = new ImageView(getActivity());
           Picasso.with(getActivity()).load(exteriorImages.get(i)).into(image);

//            image.setImageURI(Uri.parse(exteriorImages.get(i)));
            mExteriorFlipper.addView(image);
        }
        mExteriorFlipper.setDisplayedChild(0);
    }

    private void parceJson() {

        Gson gson = new Gson();
        String json = AndroidUtils.readJsonfromSdcard(Base_Path + "data.json");
        styleExteriorMain = gson.fromJson(json, StyleExteriorMain.class);

        exteriorImages.clear();

        for (int i = 0; i < styleExteriorMain.getStyleExteriorArrays().size(); i++) {
            for (int j = 0; j < styleExteriorMain.getStyleExteriorArrays().get(i).getStyleImageArray().size(); j++) {

                String image = styleExteriorMain.getStyleExteriorArrays().get(i).getStyleImageArray().get(j).getImageFile();
                String seperator[] = image.split("/");
                String imageFinalPath = STYLEEXTERIOR_MAIN_PATH + seperator[seperator.length - 1];
                exteriorImages.add(imageFinalPath);
                Log.d("IMAGES", "" + imageFinalPath);
            }
        }


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        detector.onTouchEvent(event);
        return true;
    }

    @OnClick(R.id.interior_button)
    public void CallInteriorFragment() {
        StyleInterior styleInterior = new StyleInterior();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, styleInterior).commit();

    }

    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
            try {
                if (me1.getX() - me2.getX() > SWIPE_MIN_DISTANCE) {
                    mExteriorFlipper.showPrevious();
                } else if (me2.getX() - me1.getX() > SWIPE_MIN_DISTANCE) {
                    mExteriorFlipper.showNext();
                }
                showHotSpot();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private void showHotSpot() {
        int DisplayedChild = mExteriorFlipper.getDisplayedChild();
        Log.d("StyleExterior","Child::" + DisplayedChild);
    }
}
