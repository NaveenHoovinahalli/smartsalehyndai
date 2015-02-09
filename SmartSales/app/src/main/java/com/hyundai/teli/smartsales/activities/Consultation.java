package com.hyundai.teli.smartsales.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.fragments.Estimate;
import com.hyundai.teli.smartsales.fragments.Photo;
import com.hyundai.teli.smartsales.fragments.Settings;
import com.hyundai.teli.smartsales.fragments.Showroom;
import com.hyundai.teli.smartsales.views.HTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Consultation extends ActionBarActivity {

    @InjectView(R.id.menuListView)
    RelativeLayout menuList;

    @InjectView(R.id.show_room)
    HTextView mShowRoom;

    @InjectView(R.id.estimate)
    HTextView mEstimate;

    @InjectView(R.id.photo)
    HTextView mPhoto;

    @InjectView(R.id.settings)
    ImageView mSettings;

    private boolean menuClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);
        ButterKnife.inject(this);
        loadShowRoom();
    }

    private void loadShowRoom() {
        mShowRoom.setTextColor(Color.parseColor("#657FBD"));
        Showroom showroom = new Showroom();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, showroom).commit();
    }

    @OnClick({R.id.catalogueMenu,R.id.show_room, R.id.estimate, R.id.photo, R.id.settings})
    public void OnTabClickListener(View view){
        switch(view.getId()){
            case R.id.catalogueMenu:
                if(menuClicked) {
                    menuClicked = false;
                    menuList.setVisibility(View.INVISIBLE);
                }else{
                    menuList.setVisibility(View.VISIBLE);
                    menuClicked = true;
                }
                break;
            case R.id.show_room:
                loadShowRoom();
                setSelected(view.getId());
                break;
            case R.id.estimate:
                setSelected(view.getId());
                Estimate estimate = new Estimate();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, estimate).commit();
                break;
            case R.id.photo:
                setSelected(view.getId());
                Photo photo = new Photo();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, photo).commit();
                break;
            case R.id.settings:
                setSelected(view.getId());
                Settings settings = new Settings();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, settings).commit();
                break;
        }
    }

    private void setSelected(int id) {
        mShowRoom.setTextColor(Color.parseColor("#FFFFFF"));
        mEstimate.setTextColor(Color.parseColor("#FFFFFF"));
        mPhoto.setTextColor(Color.parseColor("#FFFFFF"));
        switch (id){
            case R.id.show_room:
                mShowRoom.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.estimate:
                mEstimate.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.photo:
                mPhoto.setTextColor(Color.parseColor("#657FBD"));
                break;
        }
    }

    public void hideMenu(){
        this.menuClicked = false;
        menuList.setVisibility(View.GONE);
    }
}