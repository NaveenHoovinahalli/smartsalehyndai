package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.adapters.TabsPagerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by nith on 2/8/15.
 */
public class Settings extends BaseFragment implements ViewPager.OnPageChangeListener{

    @InjectView(R.id.settings_container)
    ViewPager viewPager;

    @InjectView(R.id.check_update)
    LinearLayout mCheckUpdate;

    @InjectView(R.id.my_info)
    LinearLayout mMyInfo;

    @InjectView(R.id.customer_management)
    LinearLayout mCustomerManagement;

    @InjectView(R.id.survey)
    LinearLayout mSurvey;

    TabsPagerAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings,null);
        ButterKnife.inject(this, view);
        mAdapter = new TabsPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setOnPageChangeListener(this);
        mCheckUpdate.setSelected(true);

        return view;
    }


    @OnClick({R.id.check_update, R.id.my_info, R.id.customer_management, R.id.survey})
    public void onSettingsTabClick(View view){
        switch(view.getId()){
            case R.id.check_update:
                viewPager.setCurrentItem(0);
                break;

            case R.id.my_info:
                viewPager.setCurrentItem(1);
                break;

            case R.id.customer_management:
                viewPager.setCurrentItem(2);
                break;

            case R.id.survey:
                viewPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        switch (position){
            case 0:
                mMyInfo.setSelected(false);
                mCustomerManagement.setSelected(false);
                mSurvey.setSelected(false);
                mCheckUpdate.setSelected(true);
                break;
            case 1:
                mCheckUpdate.setSelected(false);
                mCustomerManagement.setSelected(false);
                mSurvey.setSelected(false);
                mMyInfo.setSelected(true);
                break;
            case 2:
                mCheckUpdate.setSelected(false);
                mMyInfo.setSelected(false);
                mSurvey.setSelected(false);
                mCustomerManagement.setSelected(true);
                break;
            case 3:
                mCheckUpdate.setSelected(false);
                mMyInfo.setSelected(false);
                mCustomerManagement.setSelected(false);
                mSurvey.setSelected(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}