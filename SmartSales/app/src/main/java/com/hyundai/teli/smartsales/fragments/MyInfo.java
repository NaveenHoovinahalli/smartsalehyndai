package com.hyundai.teli.smartsales.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.ProfileFullScreen;
import com.hyundai.teli.smartsales.db_models.DealerInfo;
import com.hyundai.teli.smartsales.views.HEditText;
import com.hyundai.teli.smartsales.views.HTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class MyInfo extends BaseFragment {

    @InjectView(R.id.full_name)
    HEditText mDealerName;

    @InjectView(R.id.dealer_name_card)
    HTextView mDealerCardName;

    @InjectView(R.id.dealer_email_card)
    HTextView mDealerCardEmail;

    @InjectView(R.id.dealer_email)
    HEditText mDealerEmail;

    @InjectView(R.id.dealer_id)
    HEditText mDealerId;

    @InjectView(R.id.dealer_branch)
    HEditText mDealerBranch;

    @InjectView(R.id.dealer_phone_no_card)
    HTextView mBranchCardPhone;

    @InjectView(R.id.dealer_branch_tel)
    HEditText mBranchPhone;

    @InjectView(R.id.dealer_mobile_no)
    HEditText mDealerMobile;

    @InjectView(R.id.dealer_mobile_no_card)
    HTextView mDealerCardMobile;

    @InjectView(R.id.dealer_branch_card)
    HTextView mDealerCardBranch;

    @InjectView(R.id.pic_view)
    ImageView mProfilePic;

    @InjectView(R.id.small_pic)
    ImageView mSmallProfilePic;

    private Uri fileUri;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_info, null);
        ButterKnife.inject(this, view);
        displayDealer();
        return view;
    }

    private void displayDealer() {
        try {
            DealerInfo dealerInfo = DealerInfo.findById(DealerInfo.class, (long) 1);
            Log.d("MyInfo", "Dealer ID::" + dealerInfo.getDealerId());
            mDealerName.setText(dealerInfo.getFullName());
            mDealerCardName.setText(dealerInfo.getFullName());
            mDealerEmail.setText(dealerInfo.getEmail());
            mDealerCardEmail.setText(dealerInfo.getEmail());
            mDealerId.setText(dealerInfo.getDealerId());
            mDealerBranch.setText(dealerInfo.getBranch());
            mBranchPhone.setText(dealerInfo.getBranchPhoneNo());
            mBranchCardPhone.setText(dealerInfo.getBranchPhoneNo());
            mDealerMobile.setText(dealerInfo.getMobileNo());
            mDealerCardMobile.setText(dealerInfo.getMobileNo());
            mDealerCardBranch.setText(dealerInfo.getBranch());

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            fileUri = Uri.parse(dealerInfo.getProfileImage());
            Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);
            mProfilePic.setImageBitmap(bitmap);
            mSmallProfilePic.setImageBitmap(bitmap);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.ok_button, R.id.full_screen})
    public void OnClickListener(View view) {
        switch (view.getId()) {
            case R.id.ok_button:
                saveDealer();
                break;
            case R.id.full_screen:
                Intent openFullScreen = new Intent(getActivity(), ProfileFullScreen.class);
                startActivity(openFullScreen);
                break;
        }
    }

    private void saveDealer() {
        try {
            DealerInfo dealerInfo = DealerInfo.findById(DealerInfo.class, (long) 1);
            Log.d("MyInfo", "Dealer ID::" + dealerInfo.getDealerId());
            dealerInfo.setFullName(mDealerName.getText().toString());
            dealerInfo.setBranch(mDealerBranch.getText().toString());
            if (mBranchPhone.getText().toString().length() < 10 || mDealerMobile.getText().length() < 10) {
                Toast.makeText(getActivity(), "Please Enter valid Number", Toast.LENGTH_SHORT).show();
            } else {
                dealerInfo.setBranchPhoneNo(mBranchPhone.getText().toString());
                dealerInfo.setMobileNo(mDealerMobile.getText().toString());
            }
            dealerInfo.save();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
