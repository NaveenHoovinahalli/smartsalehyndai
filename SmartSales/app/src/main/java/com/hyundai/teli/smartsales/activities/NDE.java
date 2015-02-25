package com.hyundai.teli.smartsales.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.adapters.NDEPagerAdapter;
import com.hyundai.teli.smartsales.models.NDEMain;
import com.hyundai.teli.smartsales.utils.AndroidUtils;
import com.hyundai.teli.smartsales.utils.Constants;
import com.hyundai.teli.smartsales.utils.HyRequestQueue;
import com.hyundai.teli.smartsales.views.HTextView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class NDE extends ActionBarActivity implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.catalogueMenu)
    ImageView mCatalogMenu;

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
    ArrayList<String> ndeTabPage = new ArrayList<String>();
    ArrayList<NDEMain> ndeMain;

    PopupWindow mQuickMenu;

    public static File NDE_FOLDER;
    public static final String BASE_PATH="/Hyundai/NDE";
    public static String JsonFile="/nde.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nde);
        ButterKnife.inject(this);

        NDE_FOLDER=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+BASE_PATH);
        if(!NDE_FOLDER.exists())
            NDE_FOLDER.mkdirs();

        loadNdeVideos();

        if(AndroidUtils.isNetworkOnline(NDE.this)) {
            fetchValues();

        }else {

            parceJson();
        }

    }

    private void fetchValues() {

        String url= String.format(Constants.NDE_URL);

        JsonArrayRequest request=new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d("NDE",""+response);
                       writeToSdcard(response.toString());

                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        HyRequestQueue.getInstance(this).getRequestQueue().add(request);

    }

    private void writeToSdcard(String jsonResponse) {
        try {


            File file = new File(NDE_FOLDER + JsonFile );
            Log.d("NDE", "write to sd card" + file);
            if (file.exists())
                file.delete();

            file.createNewFile();
            file.setReadable(true);
            file.setExecutable(true);
            file.setWritable(true);

            Log.d("NDE", "write to sd card");
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter =
                    new OutputStreamWriter(fOut);
            myOutWriter.append(jsonResponse);
            myOutWriter.close();
            fOut.close();
            parceJson();
        } catch (Exception e) {
            Log.d("NDE","Exception" + e.toString());
        }


    }
    public void parceJson(){

        String parcedJson=readFromFile();
        Gson gson=new Gson();

        ndeMain=gson.fromJson(parcedJson,new TypeToken<List<NDEMain>>(){}.getType());
        setPager();

    }

    public void setPager(){
        PagerAdapter mPagerAdapter = new NDEPagerAdapter(getSupportFragmentManager(), ndeMain);
        ndePager.setAdapter(mPagerAdapter);
        ndePager.setOnPageChangeListener(this);

    }


    public String readFromFile(){

        InputStream is = null;
        try {

            File jsonPath = new File( NDE_FOLDER + JsonFile);

            if (jsonPath.exists()) {
                FileInputStream fIn = new FileInputStream(jsonPath);
                BufferedReader myReader = new BufferedReader(
                        new InputStreamReader(fIn));

                String aDataRow = "";
                String aBuffer = "";
                while ((aDataRow = myReader.readLine()) != null) {
                    aBuffer += aDataRow + "\n";
                }
                Log.d("NDE", "Buffer::" + aBuffer);
                return aBuffer.toString();

            } else {
                Toast.makeText(this, "File Not Found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


    private void loadNdeVideos() {
        ndeVideos.setTextColor(Color.parseColor("#657FBD"));
    }


    @OnClick({R.id.catalogueMenu, R.id.nde_video, R.id.nde_dealer_vision, R.id.nde_sales, R.id.nde_dealership_experience})
    public void onTabClickListner(View view) {

        switch (view.getId()) {
            case R.id.catalogueMenu:
                if (mQuickMenu == null || !mQuickMenu.isShowing())
                    showQuickMenu();
                else
                    mQuickMenu.dismiss();
                break;
            case R.id.nde_video:
                setCurrentPage("NDE VIDEO");
                setSelected(view.getId());
                break;
            case R.id.nde_dealer_vision:
                setCurrentPage("DEALER VISION AND MISSION");
                setSelected(view.getId());
                break;
            case R.id.nde_sales:
                setCurrentPage("NDE SALES PROCESS");
                setSelected(view.getId());
                break;
            case R.id.nde_dealership_experience:
                setCurrentPage("NEW DEALERSHIP EXPERIENCE");
                setSelected(view.getId());
                break;
        }

    }

    private void setCurrentPage(String category) {

        for(int i=0;i<ndeMain.size();i++){

            if(ndeMain.get(i).getCategory().equals(category)){
                ndePager.setCurrentItem(i);
                break;
            }

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




    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {

        String category=ndeMain.get(i).getCategory();
        Log.d("NDE",""+category);

        switch (category) {
            case "NDE VIDEO":
                setSelected(R.id.nde_video);
                break;
            case "DEALER VISION AND MISSION":
                setSelected(R.id.nde_dealer_vision);
                break;
            case "NDE SALES PROCESS":
                setSelected(R.id.nde_sales);
                break;
            case "NEW DEALERSHIP EXPERIENCE":
                setSelected(R.id.nde_dealership_experience);
                break;
        }


    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private void showQuickMenu() {

        if (mQuickMenu == null) {

            View popupView = getLayoutInflater().inflate(R.layout.pop_up_menu, null);
            mQuickMenu = new PopupWindow(popupView, RelativeLayout.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            mQuickMenu.setAnimationStyle(-1);
            mQuickMenu.setOutsideTouchable(true);
            mQuickMenu.setBackgroundDrawable(getResources().getDrawable(R.drawable.no_bg));

            ImageView menuFake = (ImageView) popupView.findViewById(R.id.fakeButton);
            ImageView menuHome = (ImageView) popupView.findViewById(R.id.menuHome);
            ImageView menuBrandStory = (ImageView) popupView.findViewById(R.id.menuBrandStory);
            ImageView menuConsultation = (ImageView) popupView.findViewById(R.id.menuConsultation);
            ImageView menuNDE = (ImageView) popupView.findViewById(R.id.menuNDE);
            ImageView menuBoard = (ImageView) popupView.findViewById(R.id.menuBoard);

            menuFake.setOnClickListener(menuClickListener);
            menuHome.setOnClickListener(menuClickListener);
            menuBrandStory.setOnClickListener(menuClickListener);
            menuConsultation.setOnClickListener(menuClickListener);
            menuNDE.setOnClickListener(menuClickListener);
            menuBoard.setOnClickListener(menuClickListener);
        }

        if (!mQuickMenu.isShowing()) {
            mQuickMenu.showAsDropDown(mCatalogMenu, 0, -112);
        }
    }

    private View.OnClickListener menuClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mQuickMenu.dismiss();

            switch (view.getId()) {

                case R.id.fakeButton:
                    mQuickMenu.dismiss();
                    break;
                case R.id.menuHome:
                    finish();
                    break;
                case R.id.menuBrandStory:
                    Intent openBrandStory = new Intent(NDE.this, BrandStory.class);
                    startActivity(openBrandStory);
                    finish();
                    break;
                case R.id.menuConsultation:
                    Intent openConsultation = new Intent(NDE.this, Consultation.class);
                    startActivity(openConsultation);
                    finish();
                    break;
                case R.id.menuNDE:
                    break;
                case R.id.menuBoard:
                    Intent openMessageBoard = new Intent(NDE.this, MessageBoard.class);
                    startActivity(openMessageBoard);
                    finish();
                    break;
            }
        }
    };
}







