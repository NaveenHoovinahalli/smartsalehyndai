package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.hyundai.teli.smartsales.R;
import com.squareup.picasso.Picasso;

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

    private int[] convenience_car_array = {
            R.drawable.vr1,
            R.drawable.vr2,
            R.drawable.vr3
    };

    GestureDetector detector;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_virtual_interior, null);
        ButterKnife.inject(this, view);
        mVRIntFlipper.setOnTouchListener(this);
        detector = new GestureDetector(new SwipeGestureDetector());
        for (int i = 0; i < convenience_car_array.length; i++) {
            ImageView image = new ImageView(getActivity());
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(getActivity()).load(convenience_car_array[i]).into(image);
//            image.setImageResource(convenience_car_array[i]);
            mVRIntFlipper.addView(image);
        }

        return view;
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
                    if (mVRIntFlipper.indexOfChild(mVRIntFlipper.getCurrentView()) == convenience_car_array.length - 1) {
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
