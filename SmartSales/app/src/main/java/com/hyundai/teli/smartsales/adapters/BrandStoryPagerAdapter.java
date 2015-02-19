package com.hyundai.teli.smartsales.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hyundai.teli.smartsales.fragments.BrandStoryItem;

import java.util.ArrayList;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class BrandStoryPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<BrandStoryItem> fragments=new ArrayList<BrandStoryItem>();
    public BrandStoryPagerAdapter(android.support.v4.app.FragmentManager fm, ArrayList<BrandStoryItem> fragments) {
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
