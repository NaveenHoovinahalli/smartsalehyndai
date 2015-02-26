package com.hyundai.teli.smartsales.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.views.HTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by naveen on 18/2/15.
 */
public class MessageDescription extends ActionBarActivity {

    @InjectView(R.id.image_list)
    ImageButton callButton;

    @InjectView(R.id.catalogueMenu)
    ImageView mCatalogMenu;

    @InjectView(R.id.title)
    HTextView messageTitle;

    @InjectView(R.id.date)
     HTextView messageDate;

    @InjectView(R.id.no)
     HTextView messageNo;

    @InjectView(R.id.description)
      HTextView messageDesription;

    PopupWindow mQuickMenu;

    public static final String TITLE="title";
    public static final String DESCRIPTION="description";
    public static final String DATE="date";
    public static final String NO="no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_description);
        ButterKnife.inject(this);

        if(getIntent().hasExtra(TITLE)){
            messageNo.setText(getIntent().getStringExtra(NO));
            messageTitle.setText(getIntent().getStringExtra(TITLE));
            messageDate.setText(getIntent().getStringExtra(DATE));
            messageDesription.setText(getIntent().getStringExtra(DESCRIPTION));

        }

    }

    @OnClick(R.id.image_list)
    public void callActivity() {
        Intent intent = new Intent(this, MessageBoard.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.catalogueMenu)
    public void onMenuClicked() {
        if (mQuickMenu == null || !mQuickMenu.isShowing())
            showQuickMenu();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MessageBoard.class);
        startActivity(intent);
        super.onBackPressed();

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
                    Intent openBrandStory = new Intent(MessageDescription.this, BrandStory.class);
                    startActivity(openBrandStory);
                    finish();
                    break;
                case R.id.menuConsultation:
                    Intent consultation = new Intent(MessageDescription.this, Consultation.class);
                    startActivity(consultation);
                    finish();
                    break;
                case R.id.menuNDE:
                    Intent openNDE = new Intent(MessageDescription.this, NDE.class);
                    startActivity(openNDE);
                    finish();
                    break;
                case R.id.menuBoard:
                    Intent openMessageBoard = new Intent(MessageDescription.this, MessageBoard.class);
                    startActivity(openMessageBoard);
                    finish();
                    break;
            }
        }
    };
}
