package com.hyundai.teli.smartsales.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.hyundai.teli.smartsales.fragments.BrandStoryPager;

import java.util.ArrayList;

/**
 * Created by naveen on 10/2/15.
 */
public class BrandStoryPagerAdapter extends FragmentPagerAdapter {

    ArrayList<BrandStoryPager> fragments=new ArrayList<BrandStoryPager>();
    public BrandStoryPagerAdapter(android.support.v4.app.FragmentManager fm, ArrayList<BrandStoryPager> fragments) {
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
