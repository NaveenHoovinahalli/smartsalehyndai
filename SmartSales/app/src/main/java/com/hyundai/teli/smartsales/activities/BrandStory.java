package com.hyundai.teli.smartsales.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.fragments.BrandStoryPager;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BrandStory extends ActionBarActivity {

    @InjectView(R.id.brand_pager)
    ViewPager brandPager;

    private ArrayList<BrandStoryPager> fragments=new ArrayList<BrandStoryPager>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        setContentView(R.layout.activity_brand_story);
        setFragment();
        setPager();
    }



    private void setFragment() {

        for(int i=0;i<4;i++){
            BrandStoryPager brandStoryPager=BrandStoryPager.newInstance();
            fragments.add(brandStoryPager);
        }
    }

    private void setPager() {
        if(fragments.size()!=0){
            PagerAdapter pagerAdapter=new PagerAdapter(getSupportFragmentManager(),fragments);
            brandPager.setAdapter(pagerAdapter);

        }
    }

    public class PagerAdapter extends FragmentPagerAdapter{

        ArrayList<BrandStoryPager> fragments=new ArrayList<BrandStoryPager>();
        public PagerAdapter(android.support.v4.app.FragmentManager fm,ArrayList<BrandStoryPager> fragments) {
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



}
