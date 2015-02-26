package com.hyundai.teli.smartsales.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class HyDataManager {

    private static final String VIDEO_PATH = "video_path";
    private static final String MESSAGE_JSON = "message_json";
    private final SharedPreferences msharedpreference;
    private final Context context;

    public static HyDataManager init(Context context) {
        return new HyDataManager(context);
    }

    public HyDataManager(Context context) {
        this.context = context;
        msharedpreference = context.getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE);
    }


    public void saveMessageJson(String json) {
        msharedpreference.edit().putString(MESSAGE_JSON, json).apply();
    }

    public String getMessageJson() {
        return msharedpreference.getString(MESSAGE_JSON, "");
    }

    public void saveNdeImageName(String id, String name) {
        msharedpreference.edit().putString(id, name).apply();
    }

    public String getNdeImageName(String id) {
        return msharedpreference.getString(id, "");
    }

    public void saveNdeVideoName(String id, String name) {
        msharedpreference.edit().putString(id, name).apply();
    }

    public String getNdeVideoName(String id) {
        return msharedpreference.getString(id, "");
    }


    public void saveBrandImageName(String id, String name) {
        msharedpreference.edit().putString(id, name).apply();
    }

    public String getBrandImageName(String id) {
        return msharedpreference.getString(id, "");
    }

    public void saveBrandVideoName(String id, String name) {
        msharedpreference.edit().putString(id, name).apply();
    }

    public String getBrandVideoName(String id) {
        return msharedpreference.getString(id, "");
    }

}
