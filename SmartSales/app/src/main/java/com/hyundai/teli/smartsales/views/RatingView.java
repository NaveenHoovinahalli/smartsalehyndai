package com.hyundai.teli.smartsales.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hyundai.teli.smartsales.R;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class RatingView extends LinearLayout implements OnClickListener {

    private ImageView surveyRating1;
    private ImageView surveyRating2;
    private ImageView surveyRating3;
    private ImageView surveyRating4;
    private ImageView surveyRating5;

    public RatingView(Context context) {
        super(context);
        initView(context);
    }

    public RatingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RatingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.survey_rating, this);
        surveyRating1 = (ImageView) findViewById(R.id.survey_rating1);
        surveyRating2 = (ImageView) findViewById(R.id.survey_rating2);
        surveyRating3 = (ImageView) findViewById(R.id.survey_rating3);
        surveyRating4 = (ImageView) findViewById(R.id.survey_rating4);
        surveyRating5 = (ImageView) findViewById(R.id.survey_rating5);
        surveyRating1.setOnClickListener(this);
        surveyRating2.setOnClickListener(this);
        surveyRating3.setOnClickListener(this);
        surveyRating4.setOnClickListener(this);
        surveyRating5.setOnClickListener(this);

    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.survey_rating1:
                surveyRating1.setSelected(true);

                surveyRating2.setSelected(false);
                surveyRating3.setSelected(false);
                surveyRating4.setSelected(false);
                surveyRating5.setSelected(false);
                break;
            case R.id.survey_rating2:
                surveyRating2.setSelected(true);

                surveyRating1.setSelected(false);
                surveyRating3.setSelected(false);
                surveyRating4.setSelected(false);
                surveyRating5.setSelected(false);
                break;

            case R.id.survey_rating3:
                surveyRating3.setSelected(true);

                surveyRating1.setSelected(false);
                surveyRating2.setSelected(false);
                surveyRating4.setSelected(false);
                surveyRating5.setSelected(false);

                break;

            case R.id.survey_rating4:
                surveyRating4.setSelected(true);

                surveyRating1.setSelected(false);
                surveyRating2.setSelected(false);
                surveyRating3.setSelected(false);
                surveyRating5.setSelected(false);

                break;

            case R.id.survey_rating5:
                surveyRating5.setSelected(true);
                surveyRating1.setSelected(false);
                surveyRating2.setSelected(false);
                surveyRating3.setSelected(false);
                surveyRating4.setSelected(false);
                break;

        }
    }
}
