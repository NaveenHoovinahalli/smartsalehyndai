package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.adapters.TabsPagerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Settings extends BaseFragment implements ViewPager.OnPageChangeListener{

    @InjectView(R.id.settings_container)
    ViewPager viewPager;

    @InjectView(R.id.registration_container)
    FrameLayout registrationContainer;

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
        mAdapter = new TabsPagerAdapter(getActivity(), getActivity().getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setOnPageChangeListener(this);
        Bundle bundle = getArguments();
        if (bundle.containsKey("TAB")){
            String tab = bundle.getString("TAB");
            switch (tab){
                case "UPDATE":
                    mCheckUpdate.setSelected(true);
                    break;
                case "MY_INFO":
                    viewPager.setCurrentItem(1);
                    mMyInfo.setSelected(true);
                    break;
                case "CUSTOMER_MANAGEMENT":
                    viewPager.setCurrentItem(2);
                    mCustomerManagement.setSelected(true);
                    break;
                case "SURVEY":
                    viewPager.setCurrentItem(3);
                    mSurvey.setSelected(true);
                    break;
            }
        }else{
            mCheckUpdate.setSelected(true);
        }



        return view;
    }


    @OnClick({R.id.check_update, R.id.my_info, R.id.customer_management, R.id.survey})
    public void onSettingsTabClick(View view){
        switch(view.getId()){
            case R.id.check_update:
                removeRegistrationFragment();
                viewPager.setCurrentItem(0);
                break;

            case R.id.my_info:
                removeRegistrationFragment();
                viewPager.setCurrentItem(1);
                break;

            case R.id.customer_management:
                viewPager.setCurrentItem(2);
                break;

            case R.id.survey:
                removeRegistrationFragment();
                viewPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

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

    public void addRegistrationFragment(){
        viewPager.setVisibility(View.GONE);
        registrationContainer.setVisibility(View.VISIBLE);
        Registration registration = new Registration();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.registration_container, registration).commit();
        Toast.makeText(getActivity(), "Settings", Toast.LENGTH_SHORT).show();
    }

    public void removeRegistrationFragment() {
        registrationContainer.setVisibility(View.GONE);
        viewPager.setVisibility(View.VISIBLE);
    }
}