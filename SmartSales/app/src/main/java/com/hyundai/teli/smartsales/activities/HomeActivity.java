package com.hyundai.teli.smartsales.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.views.HTextView;

import butterknife.InjectView;

public class HomeActivity extends ActionBarActivity {

    @InjectView(R.id.show_room)
    HTextView mShowRoom;

    @InjectView(R.id.estimate)
    HTextView mEstimate;

    @InjectView(R.id.photo)
    HTextView mPhoto;

    @InjectView(R.id.settings)
    ImageView mSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loadShowRoom();
    }

    private void loadShowRoom() {

    }
}
