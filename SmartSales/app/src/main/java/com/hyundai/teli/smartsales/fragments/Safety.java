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
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.gson.Gson;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.CarDetails;
import com.hyundai.teli.smartsales.adapters.ListAdapter;
import com.hyundai.teli.smartsales.adapters.PerformanceAdapter;
import com.hyundai.teli.smartsales.models.ConvenienceMain;
import com.hyundai.teli.smartsales.models.SafetyMain;
import com.hyundai.teli.smartsales.utils.AndroidUtils;
import com.hyundai.teli.smartsales.utils.HyDataManager;

import java.io.File;
import java.util.ArrayList;

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

    SafetyMain safetyMains;
    ConvenienceMain convenienceMain;

    ArrayList<String> safetyImages = new ArrayList<String>();
    ArrayList<String> safetyListValues = new ArrayList<String>();
    ArrayList<String> convenienceImages = new ArrayList<String>();
    ArrayList<String> convenienceListValues = new ArrayList<String>();

    public String Base_Path = "/Hyundai/Cars/Grandi10/";
    public static String SAFETY_CONVENIENCE_MAIN_PATH;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_safety_convenience, null);
        ButterKnife.inject(this, view);
        Base_Path = ((CarDetails) getActivity()).getBasePath() + File.separator;
        setForSafety();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.safety_button)
    public void onSafetyClicked() {
        previousView = null;
        convenienceButton.setSelected(false);
        safetyButton.setSelected(true);
        setForSafety();
    }

    @OnClick(R.id.convenience_button)
    public void onConvenienceClicked() {
        previousView = null;
        convenienceButton.setSelected(true);
        safetyButton.setSelected(false);

        setForConvenience();

    }

    private void setForSafety() {
        SAFETY_CONVENIENCE_MAIN_PATH = Base_Path + "safety/";

        parcesafetyJson();

        safetyButton.setSelected(true);
        if (safetyListValues.size() > 0 && safetyImages.size() > 0) {
            setList(safetyListValues);
            setPager(safetyImages);
        }
    }

    private void parcesafetyJson() {

        Gson gson = new Gson();
        String json = AndroidUtils.readJsonfromSdcard(Base_Path + "data.json");
        safetyMains = gson.fromJson(json, SafetyMain.class);
        Log.d("Safety", "from method" + json);
        safetyImages.clear();
        safetyListValues.clear();

        for (int i = 0; i < safetyMains.getSafetyMainArray().size(); i++) {

            for (int j = 0; j < safetyMains.getSafetyMainArray().get(i).getSafetyImages().size(); j++) {
                Log.d("Safety", "title" + safetyMains.getSafetyMainArray().get(i).getSafetyImages().get(j).getSafetyTitle());
                Log.d("Safety", "image" + safetyMains.getSafetyMainArray().get(i).getSafetyImages().get(j).getSafetyImage());
                safetyListValues.add(safetyMains.getSafetyMainArray().get(i).getSafetyImages().get(j).getSafetyTitle());
                String image = safetyMains.getSafetyMainArray().get(i).getSafetyImages().get(j).getSafetyImage();
                String seperator[] = image.split("/");
                String imageFinalPath = SAFETY_CONVENIENCE_MAIN_PATH + seperator[seperator.length - 1];

                safetyImages.add(imageFinalPath);
            }
        }


    }

    private void parceConvenienceJson() {

        Gson gson = new Gson();
        String json = AndroidUtils.readJsonfromSdcard(Base_Path + "data.json");
        convenienceMain = gson.fromJson(json, ConvenienceMain.class);
        Log.d("Safety", "from method" + json);

        convenienceImages.clear();
        convenienceListValues.clear();

        for (int i = 0; i < convenienceMain.getConvenienceMainArray().size(); i++) {

            for (int j = 0; j < convenienceMain.getConvenienceMainArray().get(i).getConvenienceImages().size(); j++) {

                convenienceListValues.add(convenienceMain.getConvenienceMainArray().get(i).getConvenienceImages().get(j).getConvenienceyTitle());
                String image = convenienceMain.getConvenienceMainArray().get(i).getConvenienceImages().get(j).getConvenienceImage();
                String seperator[] = image.split("/");
                String imageFinalPath = SAFETY_CONVENIENCE_MAIN_PATH + seperator[seperator.length - 1];

                convenienceImages.add(imageFinalPath);
            }
        }


    }

    private void setForConvenience() {

        SAFETY_CONVENIENCE_MAIN_PATH = Base_Path + "convenience/";

        parceConvenienceJson();
        if (convenienceImages.size() > 0 && convenienceListValues.size() > 0) {

            setList(convenienceListValues);
            setPager(convenienceImages);
        }
    }

    private void setList(ArrayList<String> listValues) {

        listView.setAdapter(new ListAdapter(listValues, getActivity(), true));
        listView.setOnItemClickListener(this);
    }

    private void setPager(ArrayList<String> images) {

        pagerAdapter = new PerformanceAdapter(getActivity().getSupportFragmentManager(), images);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        ViewSelection(getViewByPosition(i, listView));
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    private void ViewSelection(View view) {

        if (previousView != null) {
            previousView.setBackgroundColor((Color.parseColor("#3f3f3f")));
        } else {
            getViewByPosition(0, listView).setBackgroundColor((Color.parseColor("#3f3f3f")));
        }

        view.setBackgroundColor((Color.parseColor("#657FBD")));
        previousView = view;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ViewSelection(view);
        viewPager.setCurrentItem(position);
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
