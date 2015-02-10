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
public class NDEVideos extends BaseFragment {

    @InjectView(R.id.nde_videos_pager)
    ViewPager videoPager;

    ArrayList<NDEVideosPager> fragments=new ArrayList<NDEVideosPager>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.ndevideos_pager,null);
        ButterKnife.inject(this,view);
        Toast.makeText(getActivity(),"Inside NDE Videos",Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setFragments();
        setPager();
    }

    private void setFragments() {

        for(int i=0;i<4;i++){
            NDEVideosPager ndeVideosPager=NDEVideosPager.newInstance();
            fragments.add(ndeVideosPager);
        }

    }
    private void setPager() {
        if(fragments.size()!=0){
            NDEPagerAdapter videoAdapter=new NDEPagerAdapter(getActivity().getSupportFragmentManager(),fragments);
            videoPager.setAdapter(videoAdapter);
        }
    }
}
