package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.adapters.UpdateAdapter;
import com.hyundai.teli.smartsales.models.CarName;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class CheckUpdate extends BaseFragment{

    @InjectView(R.id.updates_view)
    GridView mUpdateView;

    UpdateAdapter mUpdateAdapter;
    ArrayList<CarName> carInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_all, null);
        ButterKnife.inject(this, view);
        parseJson();
        Log.d("Update", "Update Info::" + carInfo.get(0).getId());
        mUpdateAdapter = new UpdateAdapter(getActivity(), carInfo);

        mUpdateView.setAdapter(mUpdateAdapter);
        mUpdateView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Position::" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void parseJson() {
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Hyundai/Thumbnails/allcars.json");
            Log.d("Update", "Update File::" + file);
            if (!file.exists()) {
                Toast.makeText(getActivity(), "Missing Data, Check your Connectivity", Toast.LENGTH_SHORT).show();
            } else {
                FileInputStream stream = new FileInputStream(file);
                String jsonStr = null;
                try {
                    FileChannel fc = stream.getChannel();
                    MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

                    jsonStr = Charset.defaultCharset().decode(bb).toString();
                    Log.d("Update", "Update JsonString::" + jsonStr);

                } finally {
                    stream.close();
                }
                JSONArray response = new JSONArray(jsonStr);
                Log.d("Update", "Update Response::" + response.toString());
                Type listType = new TypeToken<ArrayList<CarName>>() {}.getType();
                carInfo = new Gson().fromJson(response.toString(), listType);

            }
        } catch (IOException fe) {
            Log.d("Update", "Update IOException::");
            fe.printStackTrace();
        } catch (JSONException e) {
            Log.d("Update", "Update JSONException::");
            e.printStackTrace();
        }
    }
}
