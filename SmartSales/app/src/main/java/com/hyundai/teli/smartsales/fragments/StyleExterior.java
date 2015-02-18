package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.hyundai.teli.smartsales.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class StyleExterior extends BaseFragment implements View.OnTouchListener{

    @InjectView(R.id.exterior_flipper)
    ViewFlipper mExteriorFlipper;

    @InjectView(R.id.interior_button)
    ImageButton interiourButton;


    private static final int SWIPE_MIN_DISTANCE = 10;
    private static final int SWIPE_THRESHOLD_VELOCITY = 20;

    private int[] convenience_car_array = {
            R.drawable.elantra_teight_blue0,
            R.drawable.elantra_teight_blue1,
            R.drawable.elantra_teight_blue2,
            R.drawable.elantra_teight_blue3,
            R.drawable.elantra_teight_blue4,
            R.drawable.elantra_teight_blue5,
            R.drawable.elantra_teight_blue6,
            R.drawable.elantra_teight_blue7,
            R.drawable.elantra_teight_blue8,
            R.drawable.elantra_teight_blue9,
            R.drawable.elantra_teight_blue10,
            R.drawable.elantra_teight_blue11,
            R.drawable.elantra_teight_blue12,
            R.drawable.elantra_teight_blue13,
            R.drawable.elantra_teight_blue14,
            R.drawable.elantra_teight_blue15,
            R.drawable.elantra_teight_blue16,
            R.drawable.elantra_teight_blue17
    };

    GestureDetector detector;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_style_exterior, null);
        ButterKnife.inject(this, view);
        mExteriorFlipper.setOnTouchListener(this);
        detector = new GestureDetector(new SwipeGestureDetector());
        for(int i=0;i<convenience_car_array.length;i++)
        {
            ImageView image = new ImageView(getActivity());
            image.setImageResource(convenience_car_array[i]);
            mExteriorFlipper.addView(image);
//            Picasso.with(getActivity()).load(convenience_car_array[i]).into(image);
        }
        mExteriorFlipper.setDisplayedChild(0);
        return view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        detector.onTouchEvent(event);
        return true;
    }

    @OnClick(R.id.interior_button)
    public void CallInteriorFragment(){
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
