package com.hyundai.teli.smartsales.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.models.DealerInfo;
import com.hyundai.teli.smartsales.views.HEditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class SignUp extends Activity {

    @InjectView(R.id.full_name)
    HEditText mDealerName;

    @InjectView(R.id.dealer_email)
    HEditText mDealerEmail;

    @InjectView(R.id.dealer_id)
    HEditText mDealerId;

    @InjectView(R.id.dealer_branch)
    HEditText mDealerBranch;

    @InjectView(R.id.dealer_branch_tel)
    HEditText mDealerBranchNo;

    @InjectView(R.id.dealer_mobile_no)
    HEditText mDealerMobileNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);
        signUpUser();
    }

    private void signUpUser() {


    }

    @OnClick(R.id.btn_next)
    public void OnNextClick(View view){
        DealerInfo dealerInfo = new DealerInfo();

        try {
            dealerInfo.setFullName(mDealerName.getText().toString());
            dealerInfo.setEmail(mDealerEmail.getText().toString());
            dealerInfo.setDealerId(mDealerId.getText().toString());
            dealerInfo.setBranch(mDealerBranch.getText().toString());
            dealerInfo.setBranchPhoneNo(mDealerBranchNo.getText().toString());
            dealerInfo.setMobileNo(mDealerMobileNo.getText().toString());
        }catch (NullPointerException npe){
            Toast.makeText(getApplicationContext(), "Next Button will be enabled Once all info is filled", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent openDealerCard   = new Intent(this, DealerCard.class);
        openDealerCard.putExtra("DEALER_INFO", dealerInfo);
        startActivity(openDealerCard);
    }
}