package com.hyundai.teli.smartsales.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.adapters.BrandStoryPagerAdapter;
import com.hyundai.teli.smartsales.fragments.BrandStoryPager;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BrandStory extends ActionBarActivity {

    @InjectView(R.id.brand_pager)
    ViewPager brandPager;

    private ArrayList<BrandStoryPager> fragments = new ArrayList<BrandStoryPager>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_story);
        ButterKnife.inject(this);
        setFragment();
        setPager();
    }


    private void setFragment() {

        for (int i = 0; i < 4; i++) {
            BrandStoryPager brandStoryPager = BrandStoryPager.newInstance();
            fragments.add(brandStoryPager);
        }
    }

    private void setPager() {
        if (fragments.size() != 0) {
            BrandStoryPagerAdapter brandStoryPagerAdapter = new BrandStoryPagerAdapter(getSupportFragmentManager(), fragments);
            brandPager.setAdapter(brandStoryPagerAdapter);

        }
    }
}