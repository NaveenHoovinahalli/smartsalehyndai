package com.hyundai.teli.smartsales.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.fragments.Comparing;
import com.hyundai.teli.smartsales.fragments.Estimate;
import com.hyundai.teli.smartsales.fragments.Performance;
import com.hyundai.teli.smartsales.fragments.Safety;
import com.hyundai.teli.smartsales.fragments.Specification;
import com.hyundai.teli.smartsales.fragments.Style;
import com.hyundai.teli.smartsales.fragments.VirtualReality;
import com.hyundai.teli.smartsales.views.HTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.virtualReality, R.id.style, R.id.performance, R.id.specification,
            R.id.safetyConvenience, R.id.estimate, R.id.comparing})
    public void onTabClickListener(View view){
        switch (view.getId()){

            case R.id.virtualReality:
                VirtualReality virtualReality = new VirtualReality();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, virtualReality).commit();
                break;
            case R.id.style:
                Style style = new Style();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, style).commit();
                break;
            case R.id.performance:
                Performance performance = new Performance();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, performance).commit();
                break;
            case R.id.specification:
                Specification specification = new Specification();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, specification).commit();
                break;
            case R.id.safetyConvenience:
                Safety safety = new Safety();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, safety).commit();
                break;
            case R.id.estimate:
                Estimate estimate = new Estimate();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, estimate).commit();
                break;
            case R.id.comparing:
                Comparing comparing = new Comparing();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, comparing).commit();
                break;
        }
    }
}