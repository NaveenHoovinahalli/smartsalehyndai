package com.hyundai.teli.smartsales.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class HyRequestQueue {
    private static HyRequestQueue mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private HyRequestQueue(Context context) {
        this.mContext = context;
        this.mRequestQueue = getRequestQueue();
    }

    public static synchronized HyRequestQueue getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new HyRequestQueue(context);
        }

        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        this.mRequestQueue = requestQueue;
    }

}
