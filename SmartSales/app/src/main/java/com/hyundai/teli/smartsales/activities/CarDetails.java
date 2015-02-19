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

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.fragments.Catalog;
import com.hyundai.teli.smartsales.fragments.Comparing;
import com.hyundai.teli.smartsales.fragments.Estimate;
import com.hyundai.teli.smartsales.fragments.Performance;
import com.hyundai.teli.smartsales.fragments.Safety;
import com.hyundai.teli.smartsales.fragments.Specification;
import com.hyundai.teli.smartsales.fragments.StyleExterior;
import com.hyundai.teli.smartsales.fragments.VirtualReality;
import com.hyundai.teli.smartsales.views.HTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class CarDetails extends ActionBarActivity {

    @InjectView(R.id.modelName)
    HTextView mModelName;

    @InjectView(R.id.virtualReality)
    HTextView mVirtualReality;

    @InjectView(R.id.style)
    HTextView mStyle;

    @InjectView(R.id.performance)
    HTextView mPerformance;

    @InjectView(R.id.specification)
    HTextView mSpecification;

    @InjectView(R.id.safetyConvenience)
    HTextView mSafetyConvenience;

    @InjectView(R.id.estimate)
    HTextView mEstimate;

    @InjectView(R.id.comparing)
    HTextView mComparing;

    @InjectView(R.id.catalog)
    HTextView mCatalog;

    @InjectView(R.id.catalogueMenu)
    ImageView mCatalogMenu;

    PopupWindow mQuickMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        ButterKnife.inject(this);
        loadVirtualReality();
    }

    private void loadVirtualReality() {
        mVirtualReality.setTextColor(Color.parseColor("#657FBD"));
        VirtualReality virtualReality = new VirtualReality();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, virtualReality).commit();
    }

    @OnClick({R.id.catalogueMenu, R.id.virtualReality, R.id.style, R.id.performance, R.id.specification,
            R.id.safetyConvenience, R.id.estimate, R.id.comparing, R.id.catalog})
    public void onTabClickListener(View view) {
        switch (view.getId()) {

            case R.id.catalogueMenu:
                if (mQuickMenu == null || !mQuickMenu.isShowing())
                    showQuickMenu();
                else
                    mQuickMenu.dismiss();
                break;
            case R.id.virtualReality:
                loadVirtualReality();
                setSelected(view.getId());
                break;
            case R.id.style:
                setSelected(view.getId());
                StyleExterior style = new StyleExterior();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, style).commit();
                break;
            case R.id.performance:
                setSelected(view.getId());
                Performance performance = new Performance();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, performance).commit();
                break;
            case R.id.specification:
                setSelected(view.getId());
                Specification specification = new Specification();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, specification).commit();
                break;
            case R.id.safetyConvenience:
                setSelected(view.getId());
                Safety safety = new Safety();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, safety).commit();
                break;
            case R.id.estimate:
                setSelected(view.getId());
                Estimate estimate = new Estimate();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, estimate).commit();
                break;
            case R.id.comparing:
                setSelected(view.getId());
                Comparing comparing = new Comparing();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, comparing).commit();
                break;
            case R.id.catalog:
                setSelected(view.getId());
                Catalog catalog = new Catalog();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, catalog).commit();
                break;
        }
    }

    private void setSelected(int id) {
        mVirtualReality.setTextColor(Color.parseColor("#FFFFFF"));
        mStyle.setTextColor(Color.parseColor("#FFFFFF"));
        mPerformance.setTextColor(Color.parseColor("#FFFFFF"));
        mSpecification.setTextColor(Color.parseColor("#FFFFFF"));
        mSafetyConvenience.setTextColor(Color.parseColor("#FFFFFF"));
        mEstimate.setTextColor(Color.parseColor("#FFFFFF"));
        mComparing.setTextColor(Color.parseColor("#FFFFFF"));
        mCatalog.setTextColor(Color.parseColor("#FFFFFF"));
        switch (id) {

            case R.id.virtualReality:
                mVirtualReality.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.style:
                mStyle.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.performance:
                mPerformance.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.specification:
                mSpecification.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.safetyConvenience:
                mSafetyConvenience.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.estimate:
                mEstimate.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.comparing:
                mComparing.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.catalog:
                mCatalog.setTextColor(Color.parseColor("#657FBD"));
                break;
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
                    Intent openHome = new Intent(CarDetails.this, Home.class);
                    startActivity(openHome);
                    finish();
                    break;
                case R.id.menuBrandStory:
                    Intent openBrandStory = new Intent(CarDetails.this, BrandStory.class);
                    startActivity(openBrandStory);
                    finish();
                    break;
                case R.id.menuConsultation:
                    Intent openConsultation = new Intent(CarDetails.this, Consultation.class);
                    startActivity(openConsultation);
                    finish();
                    break;
                case R.id.menuNDE:
                    Intent openNDE = new Intent(CarDetails.this, NDE.class);
                    startActivity(openNDE);
                    finish();
                    break;
                case R.id.menuBoard:
                    Intent openMessageBoard = new Intent(CarDetails.this, MessageBoard.class);
                    startActivity(openMessageBoard);
                    finish();
                    break;
            }
        }
    };
}