package com.hyundai.teli.smartsales.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
import com.hyundai.teli.smartsales.adapters.BrandStoryPagerAdapter;
import com.hyundai.teli.smartsales.fragments.BrandStoryFragment;
import com.hyundai.teli.smartsales.models.BrandStoryValues;
import com.hyundai.teli.smartsales.utils.AndroidUtils;
import com.hyundai.teli.smartsales.utils.Constants;
import com.hyundai.teli.smartsales.utils.HyRequestQueue;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by naveen on 2/8/15.
 */
public class BrandStory extends ActionBarActivity {

    @InjectView(R.id.brand_pager)
    ViewPager brandPager;

    @InjectView(R.id.catalogueMenu)
    ImageView mCatalogMenu;

    PopupWindow mQuickMenu;
    ArrayList<BrandStoryFragment> fragments;
    ArrayList<BrandStoryValues> brandStoryValueses;
    public static File BRAND_STORY_FOLDER;
    public static final String BASE_PATH = "/Hyundai/Brandstory";
    public static String JsonFile = "/brandstory.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_story);
        ButterKnife.inject(this);

        BRAND_STORY_FOLDER = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + BASE_PATH);
        if (!BRAND_STORY_FOLDER.exists())
            BRAND_STORY_FOLDER.mkdirs();


        if (AndroidUtils.isNetworkOnline(BrandStory.this)) {
            fetchValues();

        } else {

            parceJson();
        }

    }

    private void fetchValues() {

        String url = String.format(Constants.BRAND_STORY_URL);

        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d("BRAND", "" + response);
                        writeToSdcard(response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        HyRequestQueue.getInstance(this).getRequestQueue().add(request);

    }

    public void parceJson() {

        String parcedJson = readFromFile();
        Gson gson = new Gson();

//       brandStoryValueses= new Gson().fromJson(parcedJson,BrandStoryValues.class);

        brandStoryValueses = gson.fromJson(parcedJson, new TypeToken<List<BrandStoryValues>>() {
        }.getType());
        setFragment(brandStoryValueses);
        setPager();

    }

    private void writeToSdcard(String jsonResponse) {

        try {


            File file = new File(BRAND_STORY_FOLDER + JsonFile);
            Log.d("BRAND", "write to sd card" + file);
            if (file.exists())
                file.delete();

            file.createNewFile();
            file.setReadable(true);
            file.setExecutable(true);
            file.setWritable(true);

            Log.d("BRAND", "write to sd card");
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter =
                    new OutputStreamWriter(fOut);
            myOutWriter.append(jsonResponse);
            myOutWriter.close();
            fOut.close();
        } catch (Exception e) {
            Log.d("BRAND", "Exception" + e.toString());
        }

        parceJson();

    }


    public String readFromFile() {


//        InputStream is = null;
        try {

            File jsonPath = new File(BRAND_STORY_FOLDER + JsonFile);

            if (jsonPath.exists()) {
                FileInputStream fIn = new FileInputStream(jsonPath);
                BufferedReader myReader = new BufferedReader(
                        new InputStreamReader(fIn));

                String aDataRow = "";
                String aBuffer = "";
                while ((aDataRow = myReader.readLine()) != null) {
                    aBuffer += aDataRow + "\n";

                }
                Log.d("BRAND", "Buffer::" + aBuffer);

                return aBuffer.toString();

            } else {
                Toast.makeText(this, "File Not Found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @OnClick(R.id.catalogueMenu)
    public void OnClickListener(View view) {
        if (mQuickMenu == null || !mQuickMenu.isShowing())
            showQuickMenu();
        else
            mQuickMenu.dismiss();
    }

    private void setFragment(ArrayList<BrandStoryValues> brandStoryValueses) {
        fragments = new ArrayList<BrandStoryFragment>();
        for (int i = 0; i < brandStoryValueses.size(); i++) {
            BrandStoryFragment brandStoryPager = BrandStoryFragment.newInstance(brandStoryValueses.get(i));
            fragments.add(brandStoryPager);
        }
    }

    private void setPager() {
        if (fragments.size() != 0) {
            BrandStoryPagerAdapter brandStoryPagerAdapter = new BrandStoryPagerAdapter(getSupportFragmentManager(), fragments);
            brandPager.setAdapter(brandStoryPagerAdapter);

        }
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
                    break;
                case R.id.menuConsultation:
                    Intent openConsultation = new Intent(BrandStory.this, Consultation.class);
                    startActivity(openConsultation);
                    finish();
                    break;
                case R.id.menuNDE:
                    Intent openNDE = new Intent(BrandStory.this, NDE.class);
                    startActivity(openNDE);
                    finish();
                    break;
                case R.id.menuBoard:
                    Intent openMessageBoard = new Intent(BrandStory.this, MessageBoard.class);
                    startActivity(openMessageBoard);
                    finish();
                    break;
            }
        }
    };
}