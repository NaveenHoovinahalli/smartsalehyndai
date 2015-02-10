package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hyundai.teli.smartsales.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by nith on 2/8/15.
 */
public class Settings extends BaseFragment {

    @InjectView(R.id.check_update)
    LinearLayout mCheckUpdate;

    @InjectView(R.id.my_info)
    LinearLayout mMyInfo;

    @InjectView(R.id.customer_management)
    LinearLayout mCustomerManagement;

    @InjectView(R.id.survey)
    LinearLayout mSurvey;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings,null);
        ButterKnife.inject(this, view);
        loadCheckUpdate();

        return view;
    }

    private void loadCheckUpdate() {
        mCheckUpdate.setSelected(true);
        CheckUpdate checkUpdate = new CheckUpdate();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.settings_container, checkUpdate).commit();
    }

    @OnClick({R.id.check_update, R.id.my_info, R.id.customer_management, R.id.survey})
    public void onSettingsTabClick(View view){
        switch(view.getId()){
            case R.id.check_update:
                loadCheckUpdate();
                setSelected(view.getId());
                break;

            case R.id.my_info:
                setSelected(view.getId());
                MyInfo myInfo = new MyInfo();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.settings_container, myInfo).commit();
                break;

            case R.id.customer_management:
                setSelected(view.getId());
                CustomerManagement customerManagement = new CustomerManagement();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.settings_container, customerManagement).commit();
                break;

            case R.id.survey:
                setSelected(view.getId());
                Survey survey = new Survey();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.settings_container, survey).commit();
                break;
        }
    }

    private void setSelected(int id) {
        mCheckUpdate.setSelected(false);
        mMyInfo.setSelected(false);
        mCustomerManagement.setSelected(false);
        mSurvey.setSelected(false);
        switch (id){
            case R.id.check_update:
                mCheckUpdate.setSelected(true);
                break;

            case R.id.my_info:
                mMyInfo.setSelected(true);
                break;

            case R.id.customer_management:
                mCustomerManagement.setSelected(true);
                break;

            case R.id.survey:
                mSurvey.setSelected(true);
                break;
        }
    }
}