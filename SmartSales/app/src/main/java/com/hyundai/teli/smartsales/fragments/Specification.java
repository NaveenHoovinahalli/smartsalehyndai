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
import com.hyundai.teli.smartsales.adapters.SpecificationAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Specification extends BaseFragment implements ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener {

    @InjectView(R.id.specification_pager)
    ViewPager specificationPager;

    @InjectView(R.id.specification_list)
    ListView specificationList;

    ArrayList<String> images;
    ArrayList<PerformanceFragment> fragments;
    int[] image = new int[]{R.drawable.spec1, R.drawable.spec2, R.drawable.spec3, R.drawable.spec4};
    ArrayList<String> specificationListValues;
    View previousView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_specification, null);
        ButterKnife.inject(this, view);
        setList();
        setPager();
        specificationPager.setOnPageChangeListener(this);
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
//        specificationListValues = new String[]{"Specification 1", "Specification 2", "Specification 3", "Specification 4"};
//        ArrayAdapter<String> listAdapter=new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1,specificationListValues);

        specificationList.setAdapter(new ListAdapter(specificationListValues, getActivity(), true));
        specificationList.setOnItemClickListener(this);

    }

    private void setPager() {

        PagerAdapter mPagerAdapter = new SpecificationAdapter(getActivity().getSupportFragmentManager(), image);
//            NDEPagerAdapter videoAdapter=new NDEPagerAdapter(getActivity().getSupportFragmentManager(),fragments);
        specificationPager.setAdapter(mPagerAdapter);
        Log.d("Performance", "Position" + specificationPager.getCurrentItem());

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ViewSelection(view);
        specificationList.setSelection(position);
        specificationPager.setCurrentItem(position);
        view.setBackgroundColor((Color.parseColor("#657FBD")));

    }

    private void ViewSelection(View view) {
        if (previousView != null) {
            previousView.setBackgroundColor((Color.parseColor("#3f3f3f")));
        } else {
            getViewByPosition(0, specificationList).setBackgroundColor((Color.parseColor("#3f3f3f")));
        }

        view.setBackgroundColor((Color.parseColor("#657FBD")));
        previousView = view;

    }


    @Override
    public void onPageScrolled(int i, float v, int i2) {
    }

    @Override
    public void onPageSelected(int i) {
        Log.d("Performance", "VIEW " + getViewByPosition(i, specificationList));
        ViewSelection(getViewByPosition(i, specificationList));
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

