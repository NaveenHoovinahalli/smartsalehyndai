package com.hyundai.teli.smartsales.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hyundai.teli.smartsales.fragments.NDEVideos;
import com.hyundai.teli.smartsales.models.NDEMain;

import java.util.ArrayList;

/**
 * Created by naveen on 2/8/15.
 */
public class NDEPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<NDEMain> ndeMain = new ArrayList<>();
//    NDEMain ndeMain;

    public NDEPagerAdapter(FragmentManager fm, ArrayList<NDEMain> ndeMain) {
        super(fm);
        this.ndeMain = ndeMain;
    }

    @Override
    public Fragment getItem(int i) {
//        return fragments.get(i);
        return new NDEVideos(ndeMain.get(i));
    }

    @Override
    public int getCount() {
//        return fragments.size();
        return ndeMain.size();
    }
}
