package com.hyundai.teli.smartsales.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.adapters.ListAdapter;
import com.hyundai.teli.smartsales.adapters.PerformanceAdapter;
import com.hyundai.teli.smartsales.models.PerformanceMain;
import com.hyundai.teli.smartsales.utils.HyDataManager;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Performance extends BaseFragment implements AdapterView.OnItemClickListener, ViewPager.OnPageChangeListener {

    @InjectView(R.id.performance_pager)
    ViewPager performancePager;

    @InjectView(R.id.performance_list)
    ListView performanceList;

    ArrayList<String> perforanceimages=new  ArrayList<String>();
    ArrayList<PerformanceFragment> fragments;

    ArrayList<String> performancelistValuse=new ArrayList<String>();
    int position = 0;
    View previousView = null;
    PerformanceMain performanceMain;

    public String Base_Path="/Hyundai/Cars/Grandi10/";
    public static String PERFORMANCE_MAIN_PATH;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_performance, null);
        ButterKnife.inject(this, view);
        PERFORMANCE_MAIN_PATH= Environment.getExternalStorageDirectory().getAbsolutePath()+ Base_Path +"performance/";

        setValues();
//        setList();
//        setPager();
//        performancePager.setOnPageChangeListener(this);
        return view;
    }

    private void setValues() {
        parceJson();
        setList();
        setPager();
//        performancePager.setOnPageChangeListener(this);
    }

    private void parceJson() {

        Gson gson=new Gson();
        String json= HyDataManager.readJsonfromSdcard(Environment.getExternalStorageDirectory().getAbsolutePath() + Base_Path + "data.json");
        performanceMain=gson.fromJson(json,PerformanceMain.class);

        perforanceimages.clear();
        performancelistValuse.clear();

        for(int i=0;i<performanceMain.getPerformanceArrays().size();i++){
            for(int j=0;j<performanceMain.getPerformanceArrays().get(i).getPerformanceItems().size();j++){

                String image=performanceMain.getPerformanceArrays().get(i).getPerformanceItems().get(j).getPerfromanceImage();
                String seperator[]= image.split("/");
                String imageFinalPath=PERFORMANCE_MAIN_PATH+seperator[seperator.length-1];

                perforanceimages.add(imageFinalPath);
                performancelistValuse.add(performanceMain.getPerformanceArrays().get(i).getPerformanceItems().get(i).getTitle());
            }
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void setList() {

        performanceList.setAdapter(new ListAdapter(performancelistValuse, getActivity(), true));
        performanceList.setOnItemClickListener(this);

    }

    private void setPager() {

        PagerAdapter mPagerAdapter = new PerformanceAdapter(getActivity().getSupportFragmentManager(),perforanceimages);
        performancePager.setAdapter(mPagerAdapter);
        performancePager.setOnPageChangeListener(this);
        Log.d("Performance", "Position" + performancePager.getCurrentItem());

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ViewSelection(view);
        performancePager.setCurrentItem(position);

    }

    private void ViewSelection(View view) {
        if (previousView != null) {
            previousView.setBackgroundColor((Color.parseColor("#3f3f3f")));
        } else {
            getViewByPosition(0, performanceList).setBackgroundColor((Color.parseColor("#3f3f3f")));
        }

        view.setBackgroundColor((Color.parseColor("#657FBD")));
        previousView = view;

    }


    @Override
    public void onPageScrolled(int i, float v, int i2) {
    }

    @Override
    public void onPageSelected(int i) {
        Log.d("Performance", "VIEW " + getViewByPosition(i, performanceList));
        ViewSelection(getViewByPosition(i, performanceList));
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }
}
