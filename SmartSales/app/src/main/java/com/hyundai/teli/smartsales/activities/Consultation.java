package com.hyundai.teli.smartsales.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.fragments.Estimate;
import com.hyundai.teli.smartsales.fragments.Photo;
import com.hyundai.teli.smartsales.fragments.Settings;
import com.hyundai.teli.smartsales.fragments.Showroom;
import com.hyundai.teli.smartsales.views.HTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Consultation extends ActionBarActivity {

    @InjectView(R.id.show_room)
    HTextView mShowRoom;

    @InjectView(R.id.estimate)
    HTextView mEstimate;

    @InjectView(R.id.photo)
    HTextView mPhoto;

    @InjectView(R.id.settings)
    ImageView mSettings;

    @InjectView(R.id.catalogueMenu)
    ImageView mCatalogMenu;

    Settings settings;
    PopupWindow mQuickMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);
        ButterKnife.inject(this);
        Intent receivedIntent = getIntent();
        if(receivedIntent.hasExtra("SCREEN")){
            String category = receivedIntent.getStringExtra("SCREEN");
            switch (category){

                case "CONSULTATION":
                    loadShowRoom();
                    break;

                case "MY_INFO":
                    loadSettings(category);
                    break;

                case "CUSTOMER_MANAGEMENT":
                    loadSettings(category);
                    break;

                case "SURVEY":
                    loadSettings(category);
                    break;
            }
        }else{
            loadShowRoom();
        }
    }

    private void loadShowRoom() {
        mShowRoom.setTextColor(Color.parseColor("#657FBD"));
        Showroom showroom = new Showroom();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, showroom).commit();
    }

    private void loadSettings(String tab) {
        Bundle bundle = new Bundle();
        bundle.putString("TAB", tab);
        settings = new Settings();
        settings.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, settings).commit();
    }

    @OnClick({R.id.catalogueMenu,R.id.show_room, R.id.estimate, R.id.photo, R.id.settings})
    public void OnTabClickListener(View view){
        switch(view.getId()){
            case R.id.catalogueMenu:
                if (mQuickMenu == null || !mQuickMenu.isShowing())
                    showQuickMenu();
                else
                    mQuickMenu.dismiss();
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
                loadSettings("UPDATE");
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

    public void showRegistration(){
        Toast.makeText(getApplicationContext(), "Consultation", Toast.LENGTH_SHORT).show();
        settings.addRegistrationFragment();
    }

    public void hideRegistration(){
        settings.removeRegistrationFragment();
    }

    private void showQuickMenu() {

        if (mQuickMenu == null) {

            View popupView = getLayoutInflater().inflate(R.layout.pop_up_menu, null);
            mQuickMenu = new PopupWindow(popupView, RelativeLayout.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            mQuickMenu.setAnimationStyle(-1);
            mQuickMenu.setOutsideTouchable(true);
            mQuickMenu.setBackgroundDrawable(getResources().getDrawable(R.drawable.no_bg));

            ImageView menuFake          = (ImageView) popupView.findViewById(R.id.fakeButton);
            ImageView menuHome          = (ImageView) popupView.findViewById(R.id.menuHome);
            ImageView menuBrandStory    = (ImageView) popupView.findViewById(R.id.menuBrandStory);
            ImageView menuConsultation  = (ImageView) popupView.findViewById(R.id.menuConsultation);
            ImageView menuNDE           = (ImageView) popupView.findViewById(R.id.menuNDE);
            ImageView menuBoard         = (ImageView) popupView.findViewById(R.id.menuBoard);

            menuFake        .setOnClickListener(menuClickListener);
            menuHome        .setOnClickListener(menuClickListener);
            menuBrandStory  .setOnClickListener(menuClickListener);
            menuConsultation.setOnClickListener(menuClickListener);
            menuNDE         .setOnClickListener(menuClickListener);
            menuBoard       .setOnClickListener(menuClickListener);
        }

        if (!mQuickMenu.isShowing()) {
            mQuickMenu.showAsDropDown(mCatalogMenu, 0, -112);
        }
    }

    private View.OnClickListener menuClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mQuickMenu.dismiss();

            switch (view.getId()){

                case R.id.fakeButton:
                    mQuickMenu.dismiss();
                    break;
                case R.id.menuHome:
                    finish();
                    break;
                case R.id.menuBrandStory:
                    Intent openBrandStory = new Intent(Consultation.this, BrandStory.class);
                    startActivity(openBrandStory);
                    finish();
                    break;
                case R.id.menuConsultation:
                    break;
                case R.id.menuNDE:
                    Intent openNDE = new Intent(Consultation.this, NDE.class);
                    startActivity(openNDE);
                    finish();
                    break;
                case R.id.menuBoard:
                    Intent openMessageBoard = new Intent(Consultation.this, MessageBoard.class);
                    startActivity(openMessageBoard);
                    finish();
                    break;
            }
        }
    };

}