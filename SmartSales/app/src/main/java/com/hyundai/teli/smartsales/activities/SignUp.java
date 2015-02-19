package com.hyundai.teli.smartsales.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.models.DealerInfo;
import com.hyundai.teli.smartsales.utils.AndroidUtils;
import com.hyundai.teli.smartsales.views.HEditText;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class SignUp extends Activity {

    @InjectView(R.id.pic_view)
    ImageView mDealerPic;

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

    @InjectView(R.id.tv_name)
    Spinner mDealerPrefix;

    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);
        signUpUser();
    }

    private void signUpUser() {


    }

    @OnClick(R.id.pic_view)
    public void onPicClicked(){
        showAlert();
    }

    private void showAlert() {
        final CharSequence[] options={"Take Photo","Chose from Gallery"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Add Photo");
        builder.setItems(options,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
           if(options[which].equals("Take Photo")){
               Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               startActivityForResult(intent,1);
           }else if(options[which].equals("Chose from Gallery")){
               Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               startActivityForResult(intent, 2);
           }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==1){
            Bundle bundle=data.getExtras();
            bmp=(Bitmap) bundle.get("data");
            bmp=getResizedBitmap(bmp,150,225);
            mDealerPic.setImageBitmap(bmp);
        }
        if(resultCode==RESULT_OK && requestCode==2){
            InputStream stream = null;
            try {
                if (bmp != null) {
                    bmp.recycle();
                }
                stream = getContentResolver().openInputStream(data.getData());
                try {
                    bmp = BitmapFactory.decodeStream(stream);
                    bmp = getResizedBitmap(bmp, 150, 225);
                }catch (OutOfMemoryError e){

                }
//               bmp=getRoundedShape(bmp);

                mDealerPic.setImageBitmap(bmp);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    @OnClick(R.id.btn_next)
    public void OnNextClick(View view){

        if(!AndroidUtils.isNetworkOnline(this)){
            Toast.makeText(getApplicationContext(), "Sorry! No Internet Connection", Toast.LENGTH_SHORT).show();
            return;
        }
        if(validateDetails()!= null){
            Toast.makeText(this,validateDetails(),Toast.LENGTH_SHORT).show();
        }else {

            Log.d("SignUpActivity","Name prefix::" +mDealerPrefix.getSelectedItem().toString());
            Log.d("SignUpActivity","Name::" +mDealerName.getText().toString());
            Log.d("SignUpActivity","Email::" +mDealerEmail.getText().toString());
            Log.d("SignUpActivity","Dealer ID::" +mDealerId.getText().toString());
            Log.d("SignUpActivity","Branch::" +mDealerBranch.getText().toString());
            Log.d("SignUpActivity", "Branch No::" + mDealerBranchNo.getText().toString());
            Log.d("SignUpActivity","Mobile No::" +mDealerMobileNo.getText().toString());



            DealerInfo dealerInfo = new DealerInfo();

            try {
                dealerInfo.setFullName(mDealerName.getText().toString());
                dealerInfo.setEmail(mDealerEmail.getText().toString());
                dealerInfo.setDealerId(mDealerId.getText().toString());
                dealerInfo.setBranch(mDealerBranch.getText().toString());
                dealerInfo.setBranchPhoneNo(mDealerBranchNo.getText().toString());
                dealerInfo.setMobileNo(mDealerMobileNo.getText().toString());
            } catch (NullPointerException npe) {
                Toast.makeText(getApplicationContext(), "Next Button will be enabled Once all info is filled", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent openDealerCard = new Intent(this, DealerCard.class);
            openDealerCard.putExtra("DEALER_INFO", dealerInfo);
            startActivity(openDealerCard);
        }
    }


    private String validateDetails() {
        if (isEmpty(mDealerName))
            return "Please enter name.";
        else if (mDealerId.getText().toString().isEmpty())
            return "Please enter dealer id.";
        else if (isEmpty(mDealerBranch))
            return "Please enter branch name.";
        else if (mDealerBranchNo.getText().toString().isEmpty())
            return "Please enter branch no";
        else if (mDealerMobileNo.getText().toString().length()<10)
            return "Please enter valid no.";
        else if(bmp==null)
            return "Please update the picture";
        else if (!mDealerEmail.getText().toString().isEmpty() || !mDealerEmail.equals("E-mail Address")) {
            if (!isValidEmail(mDealerEmail.getText().toString().trim()))
                return "Please enter valid email";
        }

        return null;
    }

    boolean isEmpty(EditText editText) {
        return editText.getText().toString().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    public Bitmap getResizedBitmap(Bitmap image, int bitmapWidth, int bitmapHeight) {
        return Bitmap.createScaledBitmap(image, bitmapWidth, bitmapHeight,
                true);
    }

}