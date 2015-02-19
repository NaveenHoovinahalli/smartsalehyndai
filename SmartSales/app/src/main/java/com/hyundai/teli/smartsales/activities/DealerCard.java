package com.hyundai.teli.smartsales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.models.DealerInfo;
import com.hyundai.teli.smartsales.views.HTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class DealerCard extends ActionBarActivity {

    @InjectView(R.id.dealer_name)
    HTextView mDealerName;

    @InjectView(R.id.dealer_phone_no)
    HTextView mDealerPhoneNo;

    @InjectView(R.id.dealer_mobile_no)
    HTextView mDealerMobileNo;

    @InjectView(R.id.dealer_email)
    HTextView mDealerEmail;

    @InjectView(R.id.dealer_branch)
    HTextView mDealerBranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_card);
        ButterKnife.inject(this);
        Intent receivedIntent = getIntent();
        DealerInfo dealerInfo = receivedIntent.getParcelableExtra("DEALER_INFO");

        mDealerName.setText(dealerInfo.getFullName());
        mDealerPhoneNo.setText(dealerInfo.getBranchPhoneNo());
        mDealerMobileNo.setText(dealerInfo.getMobileNo());
        mDealerEmail.setText(dealerInfo.getEmail());
        mDealerBranch.setText(dealerInfo.getBranch());
    }

    @OnClick(R.id.btn_ok)
    public void onOkClicked(View view) {
        Intent openHome = new Intent(this, Home.class);
        startActivity(openHome);
    }
}
