package com.hyundai.teli.smartsales.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.hyundai.teli.smartsales.fragments.PerformanceFragment;

/**
 * Created by naveen on 11/2/15.
 */
public class PerformanceAdapter extends FragmentStatePagerAdapter {

//    ArrayList<String> images=new ArrayList<>();
    int[] images;
    int position;
    public PerformanceAdapter(android.support.v4.app.FragmentManager fm, int[] images) {
        super(fm);
        this.images=images;
    }

    @Override
    public Fragment getItem(int i) {
        Log.d("PerformanceAdapter", "ImagePath" +images[i]);

        return new PerformanceFragment(images[i]);
    }

    @Override
    public int getCount() {
        return images.length;
    }
}