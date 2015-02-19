package com.hyundai.teli.smartsales.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.adapters.ListAdapter;
import com.hyundai.teli.smartsales.adapters.PerformanceAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Safety extends BaseFragment implements ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener {

    @InjectView(R.id.safety_pager)
    ViewPager viewPager;

    @InjectView(R.id.safety_list)
    ListView listView;

    @InjectView(R.id.safety_button)
    ImageButton safetyButton;

    @InjectView(R.id.convenience_button)
    ImageButton convenienceButton;

    PagerAdapter pagerAdapter;
    View previousView;

    int[] safetyImages={R.drawable.s1, R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,R.drawable.s6};
    int[] convenienceImages={R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,R.drawable.c6};
    String[] safetyListValues={"Safety 1","Safety 2","Safety 3","Safety 4","Safety 5","Safety 6"};
    String[] conveienceListValues={"Convenience 1","Convenience 2","Convenience 3","Convenience 4","Convenience 5","Convenience 6"};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_safety_convenience,null);
        ButterKnife.inject(this,view);
        setForSafety();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.safety_button)
    public void onSafetyClicked(){
        previousView=null;
        convenienceButton.setSelected(false);
        safetyButton.setSelected(true);
//        convenienceButton.setImageResource(R.drawable.btn_conv_normal);
//        convenienceButton.setBackgroundColor(Color.parseColor("#000000"));
//        safetyButton.setImageResource(R.drawable.btn_safety_select);
        setForSafety();
    }

    @OnClick(R.id.convenience_button)
    public void onConvenienceClicked(){
        previousView=null;
        convenienceButton.setSelected(true);
        safetyButton.setSelected(false);
//         convenienceButton.setImageResource(R.drawable.btn_conv_select);
//        safetyButton.setImageResource(R.drawable.btn_safety_normal);
//        safetyButton.setBackgroundColor(Color.parseColor("#000000"));

        setForConvenience();

    }
    private void setForSafety(){
        safetyButton.setSelected(true);
        setList(safetyListValues);
        setPager(safetyImages);
    }

    private void setForConvenience(){
        setList(conveienceListValues);
        setPager(convenienceImages);
    }

    private void setList(String[] listValues) {
//        ArrayAdapter<String> listAdapter=new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1,listValues);

        listView.setAdapter(new ListAdapter(listValues,getActivity(),true));
        listView.setOnItemClickListener(this);
    }

    private void setPager(int[] images) {

        pagerAdapter=new PerformanceAdapter(getActivity().getSupportFragmentManager(),images);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
      ViewSelection(getViewByPosition(i,listView));
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }



    private void ViewSelection(View view){

        if(previousView!=null) {
            previousView.setBackgroundColor((Color.parseColor("#3f3f3f")));
        }else{
            getViewByPosition(0,listView).setBackgroundColor((Color.parseColor("#3f3f3f")));
        }

        view.setBackgroundColor((Color.parseColor("#657FBD")));
        previousView=view;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ViewSelection(view);
        viewPager.setCurrentItem(position);
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
