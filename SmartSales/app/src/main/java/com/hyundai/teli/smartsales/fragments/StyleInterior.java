package com.hyundai.teli.smartsales.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.CarDetails;
import com.hyundai.teli.smartsales.adapters.ListAdapter;
import com.hyundai.teli.smartsales.adapters.StyleInteriorAdapter;
import com.hyundai.teli.smartsales.models.StyleInteriorHotspot;
import com.hyundai.teli.smartsales.models.StyleInteriorMain;
import com.hyundai.teli.smartsales.utils.AndroidUtils;

import java.io.File;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by naveen on 2/8/15.
 */
public class StyleInterior extends BaseFragment implements AdapterView.OnItemClickListener, ViewPager.OnPageChangeListener, View.OnClickListener {

    @InjectView(R.id.interior_pager)
    ViewPager interiorPager;

    @InjectView(R.id.interior_button)
    ImageButton interiorButton;

    @InjectView(R.id.exterior_button)
    ImageButton exteriorButton;

    @InjectView(R.id.style_list)
    ListView styleInteriorList;

//    @InjectView(R.id.interior_main_image)
//    ImageView interiorMainImage;

    @InjectView(R.id.style_interior_exterior_ll)
    LinearLayout linearLayoutInterior;

    @InjectView(R.id.interior_main_image_layout)
    LinearLayout layoutHotspot;


    ArrayList<String> interiorImages = new ArrayList<String>();
    StyleInteriorMain styleInteriorMain;
    private String Base_Path;
    private String STYLEINTERIOR_MAIN_PATH;
    ArrayList<StyleInteriorHotspot> styleInteriorHotspots=new ArrayList<StyleInteriorHotspot>();


    ArrayList<String> hotspotnames = new ArrayList<String>();

    View previousView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_style_interior, null);
        ButterKnife.inject(this, view);
        Base_Path = ((CarDetails) getActivity()).getBasePath() + File.separator;
        STYLEINTERIOR_MAIN_PATH = Base_Path + "style_interior/";
        setValues();

        return view;
    }

    private void setValues() {
        parceJson();
        setFragment();
    }

    private void parceJson() {

        Gson gson = new Gson();
        String json = AndroidUtils.readJsonfromSdcard(Base_Path + "data.json");
        styleInteriorMain = gson.fromJson(json, StyleInteriorMain.class);


        interiorImages.clear();
        styleInteriorHotspots.clear();
        hotspotnames.clear();
        for (int i = 0; i < styleInteriorMain.getStyleInterior().size(); i++) {
            String image = styleInteriorMain.getStyleInterior().get(i).getInteriorImage();
            String seperator[] = image.split("/");
            String imageFinalPath = STYLEINTERIOR_MAIN_PATH + seperator[seperator.length - 1];
            interiorImages.add(imageFinalPath);
            Log.d("IMAGE", "" + imageFinalPath);

            for(int j=0;j<styleInteriorMain.getStyleInterior().get(i).getHotSpots().size();j++){

                styleInteriorHotspots.add(styleInteriorMain.getStyleInterior().get(i).getHotSpots().get(j));

                styleInteriorMain.getStyleInterior().get(i).getHotSpots().get(j).getHotspotId();
                hotspotnames.add(styleInteriorMain.getStyleInterior().get(i).getHotSpots().get(j).getHotspotheader());
                styleInteriorMain.getStyleInterior().get(i).getHotSpots().get(j).getHotspotDescription();
              String x= styleInteriorMain.getStyleInterior().get(i).getHotSpots().get(j).getxValue();
              String y=  styleInteriorMain.getStyleInterior().get(i).getHotSpots().get(j).getyValue();
              String hotspotImage=styleInteriorMain.getStyleInterior().get(i).getHotSpots().get(j).getHotspotImage();


                String seperatortwo[] = hotspotImage.split("/");
                String hotspotfinalImage = STYLEINTERIOR_MAIN_PATH + seperatortwo[seperatortwo.length - 1];
                interiorImages.add(hotspotfinalImage);

                Log.d("HOTSPOTDETAILS",""+x+y+hotspotImage);

            }
        }


    }

    private void setFragment() {

        linearLayoutInterior.setBackgroundColor((Color.parseColor("#657FBD")));
        setList();
        setPager();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setImageHotSpot();
    }

    private void setImageHotSpot() {


        for (int i = 1; i < styleInteriorHotspots.size()+1; i++) {

            styleInteriorHotspots.get(i-1);
            String x=styleInteriorHotspots.get(i-1).getxValue();
            String y=styleInteriorHotspots.get(i-1).getyValue();
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//            int left= (int) ((1.6*(Float.parseFloat(x)))+13);
//            int top=(int) ((1.6*(Float.parseFloat(y)))+13);
            int left= (int) Float.parseFloat(x);
            int top= (int) Float.parseFloat(y);


            int xnew= dpToPx(left);
            int ynew=dpToPx(top+30);

//            int xnew=dpToPx(939+50);
//            int ynew=dpToPx(717+50);
            params.setMargins(xnew,ynew,0,0);


            Log.d("XYVALUES",""+x+"-"+y);
            Log.d("XYVALUESLeft",""+xnew);
            Log.d("XYVALUESRight",""+ynew);
            final ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(R.drawable.btn_add_plus);
            imageView.setX((float) (xnew*1.6));
            imageView.setY((float) (ynew*1.6));

            imageView.setTag(i);
//            imageView.setLayoutParams(params);
            layoutHotspot.addView(imageView);
            imageView.setOnClickListener(this);

        }

    }

    private void setPager() {
        interiorPager.setAdapter(new StyleInteriorAdapter(getActivity().getSupportFragmentManager(), interiorImages));
        interiorPager.setVisibility(View.VISIBLE);
        interiorPager.setCurrentItem(0);
        interiorPager.setOnPageChangeListener(this);
    }

    private void setList() {
        styleInteriorList.setAdapter(new ListAdapter(hotspotnames, getActivity(), false));
        styleInteriorList.setOnItemClickListener(this);
    }

    @OnClick(R.id.style_interior_exterior_ll)
    public void onMainImageButtonClicked() {
        layoutHotspot.setVisibility(View.VISIBLE);
        interiorPager.setCurrentItem(0);

        linearLayoutInterior.setBackgroundColor((Color.parseColor("#657FBD")));
        if (previousView != null)
            previousView.setBackgroundColor((Color.parseColor("#3f3f3f")));

    }

    @OnClick(R.id.exterior_button)
    public void OnEtreriorClicked() {
        StyleExterior styleExterior = new StyleExterior();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, styleExterior).commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (layoutHotspot.getVisibility() == View.VISIBLE)
            layoutHotspot.setVisibility(View.INVISIBLE);
        if (interiorPager.getVisibility() == View.INVISIBLE)
            interiorPager.setVisibility(View.VISIBLE);
        linearLayoutInterior.setBackgroundColor((Color.parseColor("#3f3f3f")));
        ViewSelection(view);
        interiorPager.setCurrentItem(position + 1);
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {

        if (i > 0) {
            layoutHotspot.setVisibility(View.INVISIBLE);
            ViewSelection(getViewByPosition((i - 1), styleInteriorList));
            linearLayoutInterior.setBackgroundColor((Color.parseColor("#3f3f3f")));

        } else {
            layoutHotspot.setVisibility(View.VISIBLE);
            linearLayoutInterior.setBackgroundColor((Color.parseColor("#657FBD")));
            if (previousView != null) {
                previousView.setBackgroundColor((Color.parseColor("#3f3f3f")));
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private void ViewSelection(View view) {
        if (previousView != null) {
            previousView.setBackgroundColor((Color.parseColor("#3f3f3f")));
        } else {
            linearLayoutInterior.setBackgroundColor((Color.parseColor("#3f3f3f")));
        }
        view.setBackgroundColor((Color.parseColor("#657FBD")));
        previousView = view;
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

    @Override
    public void onClick(View v) {

        layoutHotspot.setVisibility(View.INVISIBLE);
        interiorPager.setVisibility(View.VISIBLE);
        linearLayoutInterior.setBackgroundColor((Color.parseColor("#3f3f3f")));

        int position = (int) v.getTag();
        interiorPager.setCurrentItem(position);
        ViewSelection(getViewByPosition((position - 1), styleInteriorList));

    }


    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        Log.d("DP",""+dp);
        return dp;
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }


}
