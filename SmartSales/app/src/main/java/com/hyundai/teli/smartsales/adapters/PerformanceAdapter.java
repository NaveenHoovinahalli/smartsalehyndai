package com.hyundai.teli.smartsales.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.hyundai.teli.smartsales.fragments.PerformanceFragment;

import java.util.ArrayList;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class PerformanceAdapter extends FragmentStatePagerAdapter {

    //    ArrayList<String> images=new ArrayList<>();
    ArrayList<String> images;
    int position;

    public PerformanceAdapter(FragmentManager fm, ArrayList<String> images) {
        super(fm);
        this.images = images;
    }

    @Override
    public Fragment getItem(int i) {
        Log.d("PerformanceAdapter", "ImagePath" + images.get(i));

        return new PerformanceFragment(images.get(i));
    }

    @Override
    public int getCount() {
        return images.size();
    }
}