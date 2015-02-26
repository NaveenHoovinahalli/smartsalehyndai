package com.hyundai.teli.smartsales.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.models.StyleExteriorMain;
import com.hyundai.teli.smartsales.utils.HyDataManager;

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

    ArrayList<String> exteriorImages=new ArrayList<String>();
    StyleExteriorMain styleExteriorMain;
    private String Base_Path="/Hyundai/Cars/Grandi10/";
    private String STYLEEXTERIOR_MAIN_PATH;

    private static final int SWIPE_MIN_DISTANCE = 100;
    private static final int SWIPE_THRESHOLD_VELOCITY = 10;


    GestureDetector detector;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_style_exterior, null);
        ButterKnife.inject(this, view);

        STYLEEXTERIOR_MAIN_PATH= Environment.getExternalStorageDirectory().getAbsolutePath()+ Base_Path +"style_exterior/";
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
            image.setImageURI(Uri.parse(exteriorImages.get(i)));
            mExteriorFlipper.addView(image);
//            Picasso.with(getActivity()).load(convenience_car_array[i]).into(image);
        }
        mExteriorFlipper.setDisplayedChild(0);
    }

    private void parceJson() {

        Gson gson=new Gson();
        String json= HyDataManager.readJsonfromSdcard(Environment.getExternalStorageDirectory().getAbsolutePath() + Base_Path + "data.json");
        styleExteriorMain=gson.fromJson(json,StyleExteriorMain.class);

        exteriorImages.clear();

        for(int i=0;i<styleExteriorMain.getStyleExteriorArrays().size();i++){
            for(int j=0;j<styleExteriorMain.getStyleExteriorArrays().get(i).getStyleImageArray().size();j++){

                String image= styleExteriorMain.getStyleExteriorArrays().get(i).getStyleImageArray().get(j).getImageFile();
                String seperator[]= image.split("/");
                String imageFinalPath=STYLEEXTERIOR_MAIN_PATH+seperator[seperator.length-1];
                exteriorImages.add(imageFinalPath);
                Log.d("IMAGES",""+imageFinalPath);
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
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
