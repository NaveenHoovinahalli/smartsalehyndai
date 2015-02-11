package com.hyundai.teli.smartsales.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hyundai.teli.smartsales.fragments.CheckUpdate;
import com.hyundai.teli.smartsales.fragments.CustomerManagement;
import com.hyundai.teli.smartsales.fragments.MyInfo;
import com.hyundai.teli.smartsales.fragments.Survey;

/**
 * Created by nith on 1/12/15.
 */
public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    Fragment[] fragments = {new CheckUpdate(),new MyInfo(),new CustomerManagement(),new Survey()};

    FragmentManager mFragmentManager;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return 4;
    }
}
