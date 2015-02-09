package com.hyundai.teli.smartsales.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nith on 1/25/15.
 */
public class HyDataManager {

    private final SharedPreferences msharedpreference;
    private final Context context;

    public static HyDataManager init(Context context){
        return new HyDataManager(context);
    }

    public HyDataManager(Context context){
        this.context = context;
        msharedpreference = context.getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE);
    }
}
