package com.hyundai.teli.smartsales.adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hyundai.teli.smartsales.fragments.CheckUpdate;
import com.hyundai.teli.smartsales.fragments.CustomerManagement;
import com.hyundai.teli.smartsales.fragments.MyInfo;
import com.hyundai.teli.smartsales.fragments.Survey;

/**
 * Created by naveen on 2/8/15.
 */
public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    Activity mContext;
    Fragment[] fragments = {new CheckUpdate(), new MyInfo(), new CustomerManagement(), new Survey()};

    FragmentManager mFragmentManager;

    public TabsPagerAdapter(Activity context, FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
        this.mContext = context;
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
