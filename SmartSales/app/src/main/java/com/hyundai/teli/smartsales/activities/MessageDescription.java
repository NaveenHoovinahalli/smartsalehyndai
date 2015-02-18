package com.hyundai.teli.smartsales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageButton;

import com.hyundai.teli.smartsales.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by naveen on 18/2/15.
 */
public class MessageDescription extends ActionBarActivity {

    @InjectView(R.id.image_list)
    ImageButton callButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_description);
        ButterKnife.inject(this);
    }

 @OnClick(R.id.image_list)
    public void callActivity(){
     Intent intent=new Intent(this,MessageBoard.class);
     startActivity(intent);
     finish();
 }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MessageBoard.class);
        startActivity(intent);
        super.onBackPressed();

    }
}
