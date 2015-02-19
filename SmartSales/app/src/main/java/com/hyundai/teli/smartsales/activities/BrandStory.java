package com.hyundai.teli.smartsales.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.adapters.BrandStoryPagerAdapter;
import com.hyundai.teli.smartsales.fragments.BrandStoryFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class BrandStory extends ActionBarActivity {

    @InjectView(R.id.brand_pager)
    ViewPager brandPager;

    @InjectView(R.id.catalogueMenu)
    ImageView mCatalogMenu;

    PopupWindow mQuickMenu;
    ArrayList<BrandStoryFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_story);
        ButterKnife.inject(this);
        setFragment();
        setPager();
    }

    @OnClick(R.id.catalogueMenu)
    public void OnClickListener(View view) {
        if (mQuickMenu == null || !mQuickMenu.isShowing())
            showQuickMenu();
        else
            mQuickMenu.dismiss();
    }

    private void setFragment() {
        fragments = new ArrayList<BrandStoryFragment>();
        for (int i = 0; i < 4; i++) {
            BrandStoryFragment brandStoryPager = BrandStoryFragment.newInstance();
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