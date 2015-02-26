package com.hyundai.teli.smartsales.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.db_models.DealerInfo;
import com.hyundai.teli.smartsales.views.HTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class ProfileFullScreen extends ActionBarActivity {

    @InjectView(R.id.name_view)
    HTextView mDealerName;

    @InjectView(R.id.tel_view)
    HTextView mDealerPhone;

    @InjectView(R.id.mobile_view)
    HTextView mDealerMobile;

    @InjectView(R.id.email_view)
    HTextView mDealerEmail;

    @InjectView(R.id.branch_view)
    HTextView mDealerBranch;

    @InjectView(R.id.bc_pic_view)
    ImageView mDealerImage;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_full_screen);
        ButterKnife.inject(this);
        displayDealer();
    }

    private void displayDealer() {
        try {
            DealerInfo dealerInfo = DealerInfo.findById(DealerInfo.class, (long) 1);
            Log.d("MyInfo", "Dealer ID::" + dealerInfo.getDealerId());
            mDealerName.setText(dealerInfo.getFullName());
            mDealerEmail.setText(dealerInfo.getEmail());
            mDealerBranch.setText(dealerInfo.getBranch());
            mDealerMobile.setText(dealerInfo.getMobileNo());
            mDealerPhone.setText(dealerInfo.getBranchPhoneNo());

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            fileUri = Uri.parse(dealerInfo.getProfileImage());
            Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);
            mDealerImage.setImageBitmap(bitmap);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
