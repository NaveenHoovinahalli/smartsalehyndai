package com.hyundai.teli.smartsales.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.CarDetails;
import com.hyundai.teli.smartsales.adapters.ListAdapter;
import com.hyundai.teli.smartsales.models.CarInfo;
import com.hyundai.teli.smartsales.models.StyleExteriorHotspot;
import com.hyundai.teli.smartsales.models.StyleExteriorImage;
import com.hyundai.teli.smartsales.utils.AndroidUtils;

import java.io.File;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class StyleExterior extends BaseFragment implements View.OnTouchListener, AdapterView.OnItemClickListener, View.OnClickListener {

    @InjectView(R.id.exterior_flipper)
    ViewFlipper mExteriorFlipper;

    @InjectView(R.id.interior_button)
    ImageButton interiourButton;

    @InjectView(R.id.hotspot_container)
    RelativeLayout hotspotContainer;

    @InjectView(R.id.style_list)
    ListView listView;



    @InjectView(R.id.style_interior_exterior_ll)
    LinearLayout styleExteriorMainLayout;

    ListAdapter listAdapter;

    private String Base_Path;
    private String STYLEEXTERIOR_MAIN_PATH;

    private static final int SWIPE_MIN_DISTANCE = 100;
    private static final int SWIPE_THRESHOLD_VELOCITY = 10;


    GestureDetector detector;
    private CarInfo carInfo;
    ArrayList<StyleExteriorHotspot> styleExteriorHotspots = new ArrayList<>();
    ArrayList<StyleExteriorImage> styleExteriorImages = new ArrayList<>();
    ArrayList<String> listValues=new ArrayList<String>();
    ArrayList<String> hotspotImages=new ArrayList<>();
    ArrayList<String> hotspotOnclickImage=new ArrayList<>();
    private ImageView hotspotImage;
    private boolean isFirst =true;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_style_exterior, null);
        ButterKnife.inject(this, view);
        Toast.makeText(getActivity(), "Path::" + ((CarDetails) getActivity()).getBasePath(), Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), "CAR::" + ((CarDetails) getActivity()).getCarName(), Toast.LENGTH_SHORT).show();
        Base_Path = ((CarDetails) getActivity()).getBasePath() + File.separator;
        STYLEEXTERIOR_MAIN_PATH = Base_Path + "style_exterior/";
        mExteriorFlipper.setOnTouchListener(this);
        detector = new GestureDetector(new SwipeGestureDetector());
        setValues();



       Log.d("height",""+ mExteriorFlipper.getHeight());
       Log.d("width",""+ mExteriorFlipper.getWidth());

        return view;
    }

    private void setValues() {
        parceJson();
        setFlipper();
        setList();
    }

    private void setList() {
        listAdapter=new ListAdapter(listValues, getActivity(), false);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(this);


    }

    @OnClick(R.id.style_interior_exterior_ll)
    public void onMainLayoutClicked(View view){
        hotspotContainer.setVisibility(View.VISIBLE);
        hotspotContainer.removeAllViews();
        setFlipper();
        setList();


    }

    private void setFlipper() {
        mExteriorFlipper.removeAllViews();
        if (carInfo.getStyleExterior().size() > 0) {
            for (int i = 0; i < carInfo.getStyleExterior().size(); i++) {
                if (carInfo.getStyleExterior().get(i).getStyleExteriorImages().size() > 0) {
                    for (int j = 0; j < carInfo.getStyleExterior().get(i).getStyleExteriorImages().size(); j++) {

                        ImageView image = new ImageView(getActivity());
                        image.setImageURI(Uri.parse(Base_Path + carInfo.getStyleExterior().get(i).
                                getStyleExteriorImages().get(j).getGetStyleExteriorImageFile().
                                replace("/media/tablet_images/" + carInfo.getCarName().replace(" ", "") + "/", "")));
                        mExteriorFlipper.addView(image);
                        styleExteriorImages.add(carInfo.getStyleExterior().get(i).getStyleExteriorImages().get(j));
                        if (carInfo.getStyleExterior().get(i).getStyleExteriorImages().get(j).getStyleExteriorHotspots().size() > 0) {
                            for (int k = 0; k < carInfo.getStyleExterior().get(i).getStyleExteriorImages().get(j).getStyleExteriorHotspots().size(); k++) {
                                styleExteriorHotspots.add(carInfo.getStyleExterior().get(i).
                                        getStyleExteriorImages().get(j).getStyleExteriorHotspots().get(k));
                            }
                        }
                    }
                }
            }
        }
        mExteriorFlipper.setDisplayedChild(0);
        showHotSpot();

    }

    private void parceJson() {
        Gson gson = new Gson();
        String json = AndroidUtils.readJsonfromSdcard(Base_Path + "data.json");
        carInfo = gson.fromJson(json, CarInfo.class);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        detector.onTouchEvent(event);
        return true;
    }

    @OnClick(R.id.interior_button)
    public void CallInteriorFragment() {
        StyleInterior styleInterior = new StyleInterior();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, styleInterior).commit();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showHotSpotImage(position);
    }

    private void showHotSpotImage(int position) {
    }



    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
            removeHotSpot();
            try {
                if (me1.getX() - me2.getX() > SWIPE_MIN_DISTANCE) {
                    mExteriorFlipper.showPrevious();
                } else if (me2.getX() - me1.getX() > SWIPE_MIN_DISTANCE) {
                    mExteriorFlipper.showNext();
                }
                showHotSpot();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private void removeHotSpot() {
        hotspotContainer.removeAllViews();
    }

    private void showHotSpot() {
        listValues.clear();
        hotspotImages.clear();
        int displayedChild = mExteriorFlipper.getDisplayedChild();
        if(styleExteriorImages.get(displayedChild) != null) {
            if(styleExteriorHotspots.size()<=0)
                return;
            for (int i=0; i<styleExteriorHotspots.size(); i++) {
                hotspotImage = new ImageView(getActivity());
                hotspotImage.setImageResource(R.drawable.btn_add_plus);
                int x = (int) Float.parseFloat(styleExteriorHotspots.get(displayedChild).getxValue());
                int y = (int) Float.parseFloat(styleExteriorHotspots.get(displayedChild).getyValue());
                int xnew= dpToPx(x);
                int ynew=dpToPx(y);
                hotspotImage.setX(((float) (x*1.129)));
                hotspotImage.setY((float) (y* 2.9));
                hotspotImage.setTag(i);
                hotspotOnclickImage.add(styleExteriorHotspots.get(displayedChild).getStyleExteriorHotspotDetails().getHotSpotDetailsImage()
                        .replace("/media/tablet_images/" + carInfo.getCarName().replace(" ", "") + "/", ""));
                hotspotImage.setOnClickListener(this);
                hotspotContainer.addView(hotspotImage);

                Log.d("StyleExterior", "HotSpotID::" + styleExteriorHotspots.get(displayedChild).getHotSpotId());
            }

                listValues.add(styleExteriorHotspots.get(displayedChild).getStyleExteriorHotspotDetails().getHeader());
//                hotspotImages.add(styleExteriorHotspots.get(displayedChild).getStyleExteriorHotspotDetails().getHotSpotDetailsImage()
//                .replace("/media/tablet_images/" + carInfo.getCarName().replace(" ", "") + "/", ""));


        }
    }

    @Override
    public void onClick(View v) {
        hotspotContainer.setVisibility(View.INVISIBLE);
        mExteriorFlipper.removeAllViews();
        int tagId= (int) v.getTag();
        Log.d("IMAGES",""+hotspotOnclickImage.get(tagId));

        ImageView image = new ImageView(getActivity());
        image.setImageURI(Uri.parse(Base_Path + hotspotOnclickImage.get(tagId)));
        mExteriorFlipper.addView(image);


    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}