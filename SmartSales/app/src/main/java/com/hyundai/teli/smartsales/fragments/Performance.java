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
    View previousView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_performance,null);
        ButterKnife.inject(this,view);
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

        Log.d("Performance ","VIEW first"+getViewByPosition(0, performanceList));
        ViewSelection(getViewByPosition(1,performanceList));

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ViewSelection(view);
        performanceList.setSelection(position);
        performancePager.setCurrentItem(position);
        view.setBackgroundColor((Color.parseColor("#657FBD")));

    }

    private void ViewSelection(View view) {
       if(previousView!=null) {
           Log.d("Performance ","VIEW first"+view);

           previousView.setBackgroundColor((Color.parseColor("#3f3f3f")));
       }

        Log.d("Performance ","VIEW all"+view);

        view.setBackgroundColor((Color.parseColor("#657FBD")));
        previousView=view;

    }


    @Override
    public void onPageScrolled(int i, float v, int i2) { }

    @Override
    public void onPageSelected(int i) {
       Log.d("Performance", "VIEW " + getViewByPosition(i, performanceList));
        ViewSelection(getViewByPosition(i,performanceList));
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }
}
