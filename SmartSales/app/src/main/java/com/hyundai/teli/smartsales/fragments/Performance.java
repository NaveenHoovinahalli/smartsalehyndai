package com.hyundai.teli.smartsales.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.adapters.PerformanceAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by nith on 2/8/15.
 */
public class Performance extends BaseFragment implements AdapterView.OnItemClickListener, ViewPager.OnPageChangeListener {

    @InjectView(R.id.performance_pager)
    ViewPager performancePager;

    @InjectView(R.id.performance_list)
    ListView performanceList;

   ArrayList<String> images;
    ArrayList<PerformanceFragment> fragments;
    int[] image;
    String[] performancelistValuse;
    int position=0;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_performance,null);
        ButterKnife.inject(this,view);
        fragments=new ArrayList<PerformanceFragment>();
        images=new ArrayList<String>();
        setList();
        setFragment();
        setPager();
        performancePager.setOnPageChangeListener(this);
        return view;
    }

    private void setList(){
        performancelistValuse=new String[]{"Brand Story","Message","NDE","Showroom"};
        ArrayAdapter<String> listAdapter=new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,performancelistValuse);
        performanceList.setAdapter(listAdapter);
        performanceList.setOnItemClickListener(this);

    }

    private void setFragment() {

        image= new int[]{R.drawable.btn_brand_story_normal, R.drawable.btn_gateway_message_normal,
                R.drawable.btn_gateway_nde, R.drawable.btn_gateway_showroom_normal};

    }
    private void setPager() {

         PagerAdapter mPagerAdapter = new PerformanceAdapter(getActivity().getSupportFragmentManager(),image,position);
//            NDEPagerAdapter videoAdapter=new NDEPagerAdapter(getActivity().getSupportFragmentManager(),fragments);
            performancePager.setAdapter(mPagerAdapter);
            Log.d("Performance","Position"+performancePager.getCurrentItem());


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        performancePager.setCurrentItem(position);
        view.setBackgroundColor((Color.parseColor("#657FBD")));

    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {


    }

    @Override
    public void onPageSelected(int i) {
//        Log.d("Performance","Current page"+i);

//        Log.d("Performance","Position "+performanceList.getItemAtPosition(i));


    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
