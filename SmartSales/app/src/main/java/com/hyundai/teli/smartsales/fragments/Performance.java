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
import android.widget.ListView;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.adapters.ListAdapter;
import com.hyundai.teli.smartsales.adapters.PerformanceAdapter;

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

    ArrayList<String> images;
    ArrayList<PerformanceFragment> fragments;
    int[] image = new int[]{R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5,
            R.drawable.p6, R.drawable.p7};
    String[] performancelistValuse;
    int position = 0;
    View previousView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_performance, null);
        ButterKnife.inject(this, view);
        setList();
        setPager();
        performancePager.setOnPageChangeListener(this);
        return view;
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
        performancelistValuse = new String[]{"Performance 1", "Performance 2", "Performance 3", "Performance 4",
                "Performance 5", "Performance 6", "Performance 7"};
//        ArrayAdapter<String> listAdapter=new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1,performancelistValuse);

        performanceList.setAdapter(new ListAdapter(performancelistValuse, getActivity(), true));
        performanceList.setOnItemClickListener(this);

    }

    private void setPager() {

        PagerAdapter mPagerAdapter = new PerformanceAdapter(getActivity().getSupportFragmentManager(), image);
//            NDEPagerAdapter videoAdapter=new NDEPagerAdapter(getActivity().getSupportFragmentManager(),fragments);
        performancePager.setAdapter(mPagerAdapter);
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
