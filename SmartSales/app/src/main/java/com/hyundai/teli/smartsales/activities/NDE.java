package com.hyundai.teli.smartsales.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.fragments.NDEVideos;
import com.hyundai.teli.smartsales.views.HTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class NDE extends ActionBarActivity {

    @InjectView(R.id.menuListView)
    RelativeLayout menuList;

    @InjectView(R.id.nde_video)
    HTextView ndeVideos;

    @InjectView(R.id.nde_dealer_v)
    HTextView ndeDealerVision;

    @InjectView(R.id.nde_sales)
    HTextView ndeSales;

    @InjectView(R.id.nde_dealer_experience)
    HTextView ndeDealerExperience;

    private boolean menuClicked=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nde);
        ButterKnife.inject(this);
        loadNdeVideos();
    }

    private void loadNdeVideos() {
        ndeVideos.setTextColor(Color.parseColor("#657FBD"));
        NDEVideos ndeVideosfragment=new NDEVideos();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,ndeVideosfragment).commit();
       }


    @OnClick({R.id.catalogueMenu,R.id.nde_video,R.id.nde_dealer_v,R.id.nde_sales,R.id.nde_dealer_experience})
    public void onTabClickListner(View view){

        switch (view.getId()){
            case R.id.catalogueMenu:
                if(menuClicked) {
                    menuClicked = false;
                    menuList.setVisibility(View.INVISIBLE);
                }else{
                    menuList.setVisibility(View.VISIBLE);
                    menuClicked = true;
                }
                break;
            case R.id.nde_video:
                setSelected(view.getId());
                loadNdeVideos();
                break;
            case R.id.nde_dealer_v:
                setSelected(view.getId());
                break;
            case R.id.nde_sales:
                setSelected(view.getId());
                break;
            case R.id.nde_dealer_experience:
                setSelected(view.getId());
                break;
        }

    }

    private void setSelected(int id) {
        ndeVideos.setTextColor(Color.parseColor("#FFFFFF"));
        ndeDealerVision.setTextColor(Color.parseColor("#FFFFFF"));
        ndeSales.setTextColor(Color.parseColor("#FFFFFF"));
        ndeDealerExperience.setTextColor(Color.parseColor("#FFFFFF"));

        switch (id){
            case R.id.nde_video:
                ndeVideos.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.nde_dealer_v:
                ndeDealerVision.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.nde_sales:
                ndeSales.setTextColor(Color.parseColor("#657FBD"));
                break;
            case R.id.nde_dealer_experience:
                ndeDealerExperience.setTextColor(Color.parseColor("#657FBD"));
                break;
        }
    }

}
