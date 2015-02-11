package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.adapters.NDEPagerAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by naveen on 10/2/15.
 */
public class NDESales extends BaseFragment{

    @InjectView(R.id.nde_sales_pager)
    ViewPager salesPager;

    ArrayList<NDEVideosPager> fragments=new ArrayList<NDEVideosPager>();
    ArrayList<String> images;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_sales,null);
        ButterKnife.inject(this, view);
        images=new ArrayList<String>();

        setFragments();
        setPager();
        return view;
    }

    private void setFragments() {

        images.add("android.resource://" + getActivity().getPackageName() + "/raw/performance/1");
        images.add("android.resource://" + getActivity().getPackageName() + "/raw/performance/2");
        images.add("android.resource://" + getActivity().getPackageName() + "/raw/performance/3");
        images.add("android.resource://" + getActivity().getPackageName() + "/raw/performance/4");

        for(int i=0;i<4;i++){
            Toast.makeText(getActivity(), "Inside NDE Videos", Toast.LENGTH_SHORT).show();
            NDEVideosPager ndeVideosPager=NDEVideosPager.newInstance(images);
            fragments.add(ndeVideosPager);
        }

    }
    private void setPager() {
        if(fragments.size()!=0){
            NDEPagerAdapter videoAdapter=new NDEPagerAdapter(getActivity().getSupportFragmentManager(),fragments);
            salesPager.setAdapter(videoAdapter);
        }
    }


}
