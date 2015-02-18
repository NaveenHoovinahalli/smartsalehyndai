package com.hyundai.teli.smartsales.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.adapters.NDEPagerAdapter;
import com.hyundai.teli.smartsales.models.NDEMain;
import com.hyundai.teli.smartsales.views.HTextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class NDE extends ActionBarActivity implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.menuListView)
    RelativeLayout menuList;

    @InjectView(R.id.pager_container)
    ViewPager ndePager;

    @InjectView(R.id.nde_video)
    HTextView ndeVideos;

    @InjectView(R.id.nde_dealer_vision)
    HTextView ndeDealerVision;

    @InjectView(R.id.nde_sales)
    HTextView ndeSales;

    @InjectView(R.id.nde_dealership_experience)
    HTextView ndeDealerExperience;

    private boolean menuClicked = false;
    NDEPagerAdapter ndePagerAdapter;
    ArrayList<String> ndeTabPage=new ArrayList<String>();

    NDEMain ndeMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nde);
        ButterKnife.inject(this);

        checkFile();
    }

    public void checkFile(){

         if(readJson()) {
//        DownloadFile();/mnt/sdcard/Download/nde.zip///mnt/sdcard/Download/nde.json
             loadNdeVideos();
             ndePagerAdapter = new NDEPagerAdapter(getSupportFragmentManager(), ndeMain);
             ndePager.setAdapter(ndePagerAdapter);
             ndePager.setOnPageChangeListener(this);
         }else {
//             Toast.makeText(this,"File Not Found",Toast.LENGTH_SHORT).show();
//             finish();
         }

    }


    private void loadNdeVideos() {
        ndeVideos.setTextColor(Color.parseColor("#657FBD"));
    }


    @OnClick({R.id.catalogueMenu, R.id.nde_video, R.id.nde_dealer_vision, R.id.nde_sales, R.id.nde_dealership_experience})
    public void onTabClickListner(View view) {

        switch (view.getId()) {
            case R.id.catalogueMenu:
                if (menuClicked) {
                    menuClicked = false;
                    menuList.setVisibility(View.INVISIBLE);
                } else {
                    menuList.setVisibility(View.VISIBLE);
                    menuClicked = true;
                }
                break;
            case R.id.nde_video:
                setSelected(view.getId());
                break;
            case R.id.nde_dealer_vision:
                setSelected(view.getId());
                break;
            case R.id.nde_sales:
                setSelected(view.getId());
                break;
            case R.id.nde_dealership_experience:
                setSelected(view.getId());
                break;
        }

    }

    private void setSelected(int id) {
        ndeVideos.setTextColor(Color.parseColor("#FFFFFF"));
        ndeDealerVision.setTextColor(Color.parseColor("#FFFFFF"));
        ndeSales.setTextColor(Color.parseColor("#FFFFFF"));
        ndeDealerExperience.setTextColor(Color.parseColor("#FFFFFF"));

        switch (id) {
            case R.id.nde_video:
                ndeVideos.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.nde_dealer_vision:
                ndeDealerVision.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.nde_sales:
                ndeSales.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.nde_dealership_experience:
                ndeDealerExperience.setTextColor(Color.parseColor("#657FBD"));
                break;
        }
    }

    public boolean readJson() {


            String parcedJson=loadJSONFromFile();
            Log.d("Specification","JSON"+parcedJson);
        if(!parcedJson.isEmpty()) {
            ndeMain = new Gson().fromJson(parcedJson.toString(), NDEMain.class);

            for (int i = 0; i < ndeMain.getContents().size(); i++) {
                Log.d("Specification", "" + ndeMain.getContents().get(i).getCategory());
                Log.d("Specification", "" + ndeMain.getContents().get(i).getId());
                Log.d("Specification", "" + ndeMain.getContents().get(i).getIsVideo());
                Log.d("Specification", "" + ndeMain.getContents().get(i).getPath());

                ndeTabPage.add(ndeMain.getContents().get(i).getCategory());
            }
            return true;
        }else {
            Log.d("Specification","JSON is empty -"+parcedJson);
            return false;

        }
    }

    public String loadJSONFromFile() {
        String htmlPath = "";


        InputStream is = null;
        try {

            File jsonPath = new File(Environment.getExternalStorageDirectory()
                    + File.separator
                    + "/" + "Download/nde.json");

            File file = new File(jsonPath, "");
            //is = open(hyundaiPath + "specification.js");

            if (file.exists()) {
                FileInputStream fIn = new FileInputStream(file);
                BufferedReader myReader = new BufferedReader(
                        new InputStreamReader(fIn));

                String content = String.valueOf(myReader.read());


                String aDataRow = "";
                String aBuffer = "{";
                while ((aDataRow = myReader.readLine()) != null) {
                    aBuffer += aDataRow + "\n";

                }

                Log.d("Specification", "Buffer::" + aBuffer);

                String replacedString = "";


                Log.d("Specification", "replacedString::" + replacedString);
                return aBuffer;


            }
            else {
                Toast.makeText(this,"File Not Found",Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";

    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {

        int item= Integer.parseInt(ndeTabPage.get(i));
        switch (item){
            case 1:
                setSelected(R.id.nde_video);
                break;
            case 2:
                setSelected(R.id.nde_dealer_vision);
                break;
            case 3:
                setSelected(R.id.nde_sales);
                break;
            case 4:
                setSelected(R.id.nde_dealership_experience);
                break;
        }


    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}







