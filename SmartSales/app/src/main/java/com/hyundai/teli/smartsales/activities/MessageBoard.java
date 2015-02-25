package com.hyundai.teli.smartsales.activities;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.adapters.MessageListAdapter;
import com.hyundai.teli.smartsales.models.MessageBoardValues;
import com.hyundai.teli.smartsales.utils.AndroidUtils;
import com.hyundai.teli.smartsales.utils.Constants;
import com.hyundai.teli.smartsales.utils.HyDataManager;
import com.hyundai.teli.smartsales.utils.HyRequestQueue;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class MessageBoard extends ActionBarActivity {

    @InjectView(R.id.list_message)
    ListView listMessage;

    @InjectView(R.id.catalogueMenu)
    ImageView mCatalogMenu;

    PopupWindow mQuickMenu;
   ArrayList<MessageBoardValues> messageBoardValues;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_board);
        ButterKnife.inject(this);
        if(AndroidUtils.isNetworkOnline(this)) {
            fetchValues();
        }else {
          String json= HyDataManager.init(this).getMessageJson();
           if(!json.isEmpty()) {

               Type listType = new TypeToken<List<MessageBoardValues>>() {
               }.getType();
               List<MessageBoardValues> message = (List<MessageBoardValues>) new Gson().fromJson(json, listType);
               listMessage.setAdapter(new MessageListAdapter(this,message));

           }
        }

    }

    private void fetchValues() {

        String url=String.format(Constants.MESSAGE_BOARD_URL);


        JsonArrayRequest messageRequest=new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        HyDataManager.init(MessageBoard.this).saveMessageJson(response.toString());
                        parceJson(response);
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        HyRequestQueue.getInstance(this).getRequestQueue().add(messageRequest);

    }

    private void parceJson(JSONArray jsonArray) {

        Gson gson=new Gson();

        messageBoardValues=gson.fromJson(jsonArray.toString(),new TypeToken<List<MessageBoardValues>>(){}.getType());
        listMessage.setAdapter(new MessageListAdapter(this,messageBoardValues));


    }

    @OnClick(R.id.catalogueMenu)
    public void onMenuClicked() {
        if (mQuickMenu == null || !mQuickMenu.isShowing())
            showQuickMenu();

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
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
                    Intent openBrandStory = new Intent(MessageBoard.this, BrandStory.class);
                    startActivity(openBrandStory);
                    finish();
                    break;
                case R.id.menuConsultation:
                    Intent consultation = new Intent(MessageBoard.this, Consultation.class);
                    startActivity(consultation);
                    finish();
                    break;
                case R.id.menuNDE:
                    Intent openNDE = new Intent(MessageBoard.this, NDE.class);
                    startActivity(openNDE);
                    finish();
                    break;
                case R.id.menuBoard:
//                    Intent openMessageBoard = new Intent(MessageBoard.this, MessageBoard.class);
//                    startActivity(openMessageBoard);
//                    finish();
                    break;
            }
        }
    };
}
