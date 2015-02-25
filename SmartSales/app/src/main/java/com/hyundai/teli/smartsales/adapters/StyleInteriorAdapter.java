package com.hyundai.teli.smartsales.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hyundai.teli.smartsales.fragments.PerformanceFragment;

/**
 * Created by naveen on 16/2/15.
 */
public class StyleInteriorAdapter extends FragmentStatePagerAdapter {

    String[] images;

    public StyleInteriorAdapter(FragmentManager fm, int[] images) {
        super(fm);
//        this.images = images;
    }

    @Override
    public Fragment getItem(int i) {
        return new PerformanceFragment(images[i]);
    }

    @Override
    public int getCount() {
        return images.length;
    }
}
