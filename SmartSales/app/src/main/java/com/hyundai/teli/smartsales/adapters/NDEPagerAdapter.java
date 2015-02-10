package com.hyundai.teli.smartsales.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.hyundai.teli.smartsales.fragments.NDEVideosPager;

import java.util.ArrayList;

/**
 * Created by naveen on 10/2/15.
 */
public class NDEPagerAdapter extends FragmentPagerAdapter {

    ArrayList<NDEVideosPager> fragments=new ArrayList<NDEVideosPager>();
    public NDEPagerAdapter(android.support.v4.app.FragmentManager fm, ArrayList<NDEVideosPager> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
