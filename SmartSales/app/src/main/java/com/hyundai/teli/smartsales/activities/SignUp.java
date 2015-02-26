package com.hyundai.teli.smartsales.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.db_models.DealerInfo;
import com.hyundai.teli.smartsales.utils.AndroidUtils;
import com.hyundai.teli.smartsales.utils.Constants;
import com.hyundai.teli.smartsales.views.HEditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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

    @InjectView(R.id.dealer_branch)
    HEditText mDealerBranch;

    @InjectView(R.id.dealer_branch_tel)
    HEditText mDealerBranchNo;

    @InjectView(R.id.dealer_mobile_no)
    HEditText mDealerMobileNo;

    @InjectView(R.id.tv_name)
    Spinner mDealerPrefix;

    Bitmap bmp;
    private DealerInfo dealerInfo;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = ".Hyundai";
    private Uri fileUri;

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
    public void onPicClicked() {
        showAlert();
    }

    private void showAlert() {
        final CharSequence[] options = {"Take Photo", "Chose from Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (options[which].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
                } else if (options[which].equals("Chose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
            }
        });
        builder.show();
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();

                    options.inSampleSize = 8;
                    bmp = BitmapFactory.decodeFile(fileUri.getPath(),
                            options);

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                bmp = getResizedBitmap(bmp, 150, 225);
                mDealerPic.setImageBitmap(bmp);

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        if (resultCode == RESULT_OK && requestCode == 2) {
            InputStream stream = null;
            try {
                if (bmp != null) {
                    bmp.recycle();
                }
                fileUri = Uri.parse(getRealPathFromURI(data.getData()));
                Log.d("SignUp", "FileURI::" + fileUri);
                stream = getContentResolver().openInputStream(data.getData());
                try {
                    bmp = BitmapFactory.decodeStream(stream);
                    bmp = getResizedBitmap(bmp, 150, 225);
                } catch (OutOfMemoryError e) {

                }

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
    public void OnNextClick(View view) {

        if (!AndroidUtils.isNetworkOnline(this)) {
            Toast.makeText(getApplicationContext(), "Sorry! No Internet Connection", Toast.LENGTH_SHORT).show();
            return;
        }
        if (validateDetails() != null) {
            Toast.makeText(this, validateDetails(), Toast.LENGTH_SHORT).show();
        } else {
            dealerInfo = new DealerInfo();
            try {
                dealerInfo.setFullName(mDealerName.getText().toString());
                dealerInfo.setEmail(mDealerEmail.getText().toString());
                dealerInfo.setBranch(mDealerBranch.getText().toString());
                dealerInfo.setBranchPhoneNo(mDealerBranchNo.getText().toString());
                dealerInfo.setMobileNo(mDealerMobileNo.getText().toString());
                dealerInfo.setProfileImage(fileUri.getPath());

            } catch (NullPointerException npe) {
                Toast.makeText(getApplicationContext(), "Next Button will be enabled Once all info is filled", Toast.LENGTH_SHORT).show();
                return;
            }


            new MyAsyncTask().execute();
        }
    }

    public JSONObject executeMultipartPost() throws Exception {

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(Constants.SIGN_UP);
            MultipartEntity entity = new MultipartEntity();

            entity.addPart("tablet_id", new StringBody(AndroidUtils.getDeviceImei(getApplicationContext())));
            entity.addPart("email", new StringBody(dealerInfo.getEmail()));
            entity.addPart("sales_person_name", new StringBody(dealerInfo.getFullName()));
            entity.addPart("branch", new StringBody(dealerInfo.getBranch()));
            entity.addPart("branch_contact_number", new StringBody(dealerInfo.getBranchPhoneNo()));
            entity.addPart("mobile_number", new StringBody(dealerInfo.getMobileNo()));
            if (bmp != null) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 75, bos);
                byte[] data = bos.toByteArray();
                ByteArrayBody bab = new ByteArrayBody(data, "user.jpg");
                if (bab != null) {
                    entity.addPart("photo", bab);
                }
            }

            httpPost.setEntity(entity);
            HttpResponse response = null;

            response = (HttpResponse) httpclient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            JSONObject jsonObject = null;
            if (entity != null) {
                InputStream instream = httpEntity.getContent();
                jsonObject = convertInputStreamToJSONObject(instream);
                instream.close();
            }

            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String validateDetails() {
        if (isEmpty(mDealerName))
            return "Please enter name.";
        else if (isEmpty(mDealerBranch))
            return "Please enter branch name.";
        else if (mDealerBranchNo.getText().toString().isEmpty())
            return "Please enter branch no";
        else if (mDealerMobileNo.getText().toString().length() < 10)
            return "Please enter valid no.";
        else if (bmp == null)
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


    public class MyAsyncTask extends AsyncTask<String, String, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... strings) {
            try {
                return executeMultipartPost();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject s) {
            super.onPostExecute(s);
            Log.d("SignUp", "Response::" + s.toString());
            if (s != null) {
                try {
                    JSONObject jsonObject = new JSONObject(s.toString());
                    dealerInfo.setDealerId(jsonObject.getString("auth_code"));
                    dealerInfo.save();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent openValidate = new Intent(SignUp.this, Validate.class);
                openValidate.putExtra("DEALER_ID", s.toString());
                startActivity(openValidate);
                finish();
            }
        }
    }

    private static JSONObject convertInputStreamToJSONObject(InputStream inputStream)
            throws JSONException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        try {
            while ((line = bufferedReader.readLine()) != null)
                result += line;

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(result);
    }

    public Bitmap getResizedBitmap(Bitmap image, int bitmapWidth, int bitmapHeight) {
        return Bitmap.createScaledBitmap(image, bitmapWidth, bitmapHeight,
                true);
    }

    public String getRealPathFromURI(Uri contentUri) {

        // can post image
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri,
                proj, // Which columns to return
                null,       // WHERE clause; which rows to return (all rows)
                null,       // WHERE clause selection arguments (none)
                null); // Order-by clause (ascending by name)
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
    }
}
