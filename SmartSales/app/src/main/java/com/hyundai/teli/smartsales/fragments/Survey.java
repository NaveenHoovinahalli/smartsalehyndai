package com.hyundai.teli.smartsales.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.db_models.CustomerSurvey;
import com.hyundai.teli.smartsales.utils.AndroidUtils;
import com.hyundai.teli.smartsales.utils.Constants;
import com.hyundai.teli.smartsales.views.HEditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by naveen on 2/8/15.
 */
public class Survey extends BaseFragment {

    @InjectView(R.id.radio_group1)
    RadioGroup radioGroup1;

    @InjectView(R.id.radio_group2)
    RadioGroup radioGroup2;

    @InjectView(R.id.radio_group3)
    RadioGroup radioGroup3;

    @InjectView(R.id.radio_group4)
    RadioGroup radioGroup4;

    @InjectView(R.id.radio_group5)
    RadioGroup radioGroup5;

    @InjectView(R.id.radio_group6)
    RadioGroup radioGroup6;

    @InjectView(R.id.radio_group7)
    RadioGroup radioGroup7;

    @InjectView(R.id.radio_group8)
    RadioGroup radioGroup8;

    @InjectView(R.id.radio_group9)
    RadioGroup radioGroup9;

    @InjectView(R.id.registerButton)
    Button registerButton;

    @InjectView(R.id.survey_thanks)
    ImageView imageView;

    int sum=0;
    String vinNo,mobileNo;
    String message;

    StringBuilder surveyOption ;

    @InjectView(R.id.tv_name)
    Spinner spinnerName;

    @InjectView(R.id.surveyor_name)
    HEditText custometName;


    List<CustomerSurvey> listSurvey;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_survey, null);
        ButterKnife.inject(this, view);
        clearRadioButtons();
        custometName.getText().clear();

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setTitle("Please wait....");
        progressDialog.setCancelable(false);

        if(AndroidUtils.isNetworkOnline(getActivity()))
            readFromDB();
        return view;
    }


    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Please enter Vin# or Mobile#");
        builder.setCancelable(false);

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText inputVin = new EditText(getActivity());
        inputVin.setLeft(20);
        inputVin.setInputType(InputType.TYPE_CLASS_NUMBER);
        inputVin.setHint("vin#");
        layout.addView(inputVin);

        final EditText editMobileNo = new EditText(getActivity());
        editMobileNo.setInputType(InputType.TYPE_CLASS_NUMBER);
        editMobileNo.setHint("mobile#");
        layout.addView(editMobileNo);
        builder.setView(layout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vinNo = inputVin.getText().toString();
                mobileNo = editMobileNo.getText().toString();
                if ((!vinNo.equals("") && !vinNo.equals("vin#")) || (!mobileNo.equals("") && !mobileNo.equals("mobile#") && mobileNo.length()>10)) {
                    dialog.dismiss();
                    saveToDB();
                    imageView.setVisibility(View.VISIBLE);


                }
            }
        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });

        builder.show();

    }

    private void clearRadioButtons() {
        radioGroup1.clearCheck();
        radioGroup2.clearCheck();
        radioGroup3.clearCheck();
        radioGroup4.clearCheck();
        radioGroup5.clearCheck();
        radioGroup6.clearCheck();
        radioGroup7.clearCheck();
        radioGroup8.clearCheck();
        radioGroup9.clearCheck();


    }

    @OnClick(R.id.registerButton)
     public void onSubmit(View view){
         int allCkecked=isCheckedRadio();
         if(allCkecked==0) {
             Toast.makeText(getActivity(), "Please fill for all the question", Toast.LENGTH_SHORT).show();
                return;
         }else if(Calculate()>72) {
             sum=0;
             showDialog();
             clearRadioButtons();
         }else {
             callDialog();
             clearRadioButtons();
         }

//        showDialog();
    }

    private void saveToDB() {

        CustomerSurvey customerSurvey=new CustomerSurvey();

        if(mobileNo==null)
            mobileNo="12";
        if(vinNo==null)
            vinNo="12";
        Log.d("Survey","Options"+surveyOption);

          customerSurvey.setDealer("1");
        customerSurvey.setCustomerName(custometName.getText().toString());
        customerSurvey.setMobileNo(mobileNo.toString());
        customerSurvey.setVinNo(vinNo.toString());
        customerSurvey.setSurveyQuestion("1,2,3,4,5,6,7,8,9");
        customerSurvey.setSurveyOptions(surveyOption.toString());
        customerSurvey.save();

        if(AndroidUtils.isNetworkOnline(getActivity()))
            readFromDB();


    }

    private void readFromDB() {
        progressDialog.show();
        listSurvey = CustomerSurvey.listAll(CustomerSurvey.class);
        if (listSurvey.size() > 0) {
            for (int i = 0; i < listSurvey.size(); i++) {

                new MyAsyncTask(i).execute();
            }
        }
        progressDialog.dismiss();
    }


//                JSONObject params=new JSONObject();
//                try {
////                    params.put("dealer",listSurvey.get(i).getDealer());
////                    params.put("customer_name",listSurvey.get(i).getCustomerName());
////                    params.put("mobile_number",listSurvey.get(i).getMobileNo());
////                    params.put("vin_number",listSurvey.get(i).getVinNo());
////                    params.put("survey_questions",listSurvey.get(i).getSurveyQuestion());
////                    params.put("survey_options",listSurvey.get(i).getSurveyOptions());
//
//
//
//                  Log.d("Survey","db"+listSurvey.get(i).getSurveyOptions());
//                    Log.d("Survey","db"+listSurvey.get(i).getDealer());
//                    Log.d("Survey","db"+listSurvey.get(i).getMobileNo());
//                    Log.d("Survey","db"+listSurvey.get(i).getCustomerName());
//                    Log.d("Survey","db"+listSurvey.get(i).getVinNo());
//                    Log.d("Survey","db"+listSurvey.get(i).getSurveyQuestion());
//
//
//
////                    sendToServer(params);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }



//    private void sendToServer(JSONObject params) {
//        Log.d("Survey","Params"+params.toString());
//
//        String url=String.format(Constants.CUSTOMER_SURVEY);
//
//        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,
//                url,
//                params,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        Log.d("Survey","Response"+response.toString());
//
//                    }
//                },
//        new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("Survey","Response"+error.networkResponse.statusCode);
//            }
//        });
//        HyRequestQueue.getInstance(getActivity()).getRequestQueue().add(request);
//
//    }

    private void callDialog() {

        final Dialog dialog=new Dialog(getActivity());
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.survey_comments_dialog,null);
        view.setMinimumHeight(350);
        view.setMinimumWidth(400);

        dialog.setContentView(view);
        final HEditText editText= (HEditText) view.findViewById(R.id.et_comments);
        Button button= (Button) view.findViewById(R.id.butto_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().isEmpty()) {
                    message=editText.getText().toString();
                    dialog.dismiss();
                    showDialog();
                    getActivity().getFragmentManager().popBackStack();

                } else {
                    Toast.makeText(getActivity(), "Need a comment on this", Toast.LENGTH_SHORT).show();

                    return;
                }

            }
        });
        dialog.show();

    }


    public class MyAsyncTask extends AsyncTask<String, String, String> {

        int i;
        public MyAsyncTask(int i) {
            this.i=i;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                return submitSurveyToCustomer(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                Log.d("Registration", "Response::" + s.toString());
                listSurvey.get(i).delete();

            }
        }
    }

    private String submitSurveyToCustomer(int i) {

        HttpClient httpClient=new DefaultHttpClient();
        HttpPost httpPost=new HttpPost(Constants.CUSTOMER_SURVEY);
        MultipartEntity entity=new MultipartEntity();

        try {
            entity.addPart("dealer",new StringBody(listSurvey.get(i).getDealer()));
            entity.addPart("tablet_id", new StringBody(AndroidUtils.getDeviceImei(getActivity().getApplicationContext())));
            entity.addPart("customer_name", new StringBody(listSurvey.get(i).getCustomerName()));
            entity.addPart("mobile_number", new StringBody(listSurvey.get(i).getMobileNo()));
            entity.addPart("vin_number", new StringBody(listSurvey.get(i).getVinNo()));
            entity.addPart("survey_questions", new StringBody(listSurvey.get(i).getSurveyQuestion()));
            entity.addPart("survey_options", new StringBody(listSurvey.get(i).getSurveyOptions()));


           httpPost.setEntity(entity);
            HttpResponse response=null;


            response= (HttpResponse)httpClient.execute(httpPost);
            HttpEntity httpEntity=response.getEntity();
            JSONObject jsonObject=null;
            if(response !=null){
                Log.d("Registration","response--" + response.getStatusLine());
                return response.toString();

            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    private int isCheckedRadio() {

        if(radioGroup1.getCheckedRadioButtonId()==-1)
               return 0;
        else if(radioGroup2.getCheckedRadioButtonId()==-1)
            return 0;
        else if(radioGroup3.getCheckedRadioButtonId()==-1)
            return 0;
        else if(radioGroup4.getCheckedRadioButtonId()==-1)
            return 0;
        else if(radioGroup5.getCheckedRadioButtonId()==-1)
            return 0;
        else if(radioGroup6.getCheckedRadioButtonId()==-1)
            return 0;
        else if(radioGroup7.getCheckedRadioButtonId()==-1)
            return 0;
        else if(radioGroup8.getCheckedRadioButtonId()==-1)
            return 0;
        else if(radioGroup9.getCheckedRadioButtonId()==-1)
            return 0;
        return 1;

    }

    public int Calculate(){
        Log.d("Survey","mobileno"+mobileNo);
        Log.d("Survey","vin"+vinNo);
        surveyOption = new StringBuilder(100);
        sum=0;
        if(radioGroup1.getCheckedRadioButtonId()==R.id.radio_button1_1) {
            sum = sum + 10;
            surveyOption.append("10,");
        }
        if(radioGroup1.getCheckedRadioButtonId()==R.id.radio_button1_2) {
            sum = sum + 9;
            surveyOption.append("9,");

        }
        if(radioGroup1.getCheckedRadioButtonId()==R.id.radio_button1_3) {
            sum = sum + 8;
            surveyOption.append("8,");

        }

        if(radioGroup2.getCheckedRadioButtonId()==R.id.radio_button2_1) {
            sum = sum + 10;
            surveyOption.append("10,");

        }
        if(radioGroup2.getCheckedRadioButtonId()==R.id.radio_button2_2) {
            sum = sum + 9;
            surveyOption.append("9,");

        }
        if(radioGroup2.getCheckedRadioButtonId()==R.id.radio_button2_3) {
            sum = sum + 8;
            surveyOption.append("8,");

        }

        if(radioGroup3.getCheckedRadioButtonId()==R.id.radio_button3_1) {
            sum = sum + 10;
            surveyOption.append("10,");

        }
        if(radioGroup3.getCheckedRadioButtonId()==R.id.radio_button3_2) {
            sum = sum + 9;
            surveyOption.append("9,");

        }
        if(radioGroup3.getCheckedRadioButtonId()==R.id.radio_button3_3) {
            sum = sum + 8;
            surveyOption.append("8,");

        }

        if(radioGroup4.getCheckedRadioButtonId()==R.id.radio_button4_1) {
            sum = sum + 10;
            surveyOption.append("10,");

        }
        if(radioGroup4.getCheckedRadioButtonId()==R.id.radio_button4_2){
            sum=sum+9;
            surveyOption.append("9,");

        }
        if(radioGroup4.getCheckedRadioButtonId()==R.id.radio_button4_3) {
            sum = sum + 8;
            surveyOption.append("8,");

        }

        if(radioGroup5.getCheckedRadioButtonId()==R.id.radio_button5_1) {
            sum = sum + 10;
            surveyOption.append("10,");

        }
        if(radioGroup5.getCheckedRadioButtonId()==R.id.radio_button5_2) {
            sum = sum + 9;
            surveyOption.append("9,");

        }
        if(radioGroup5.getCheckedRadioButtonId()==R.id.radio_button5_3) {
            sum = sum + 8;
            surveyOption.append("8,");

        }

        if(radioGroup6.getCheckedRadioButtonId()==R.id.radio_button6_1) {
            sum = sum + 10;
            surveyOption.append("10,");

        }
        if(radioGroup6.getCheckedRadioButtonId()==R.id.radio_button6_2) {
            sum = sum + 9;
            surveyOption.append("9,");

        }
        if(radioGroup6.getCheckedRadioButtonId()==R.id.radio_button6_3) {
            sum = sum + 8;
            surveyOption.append("8,");

        }

        if(radioGroup7.getCheckedRadioButtonId()==R.id.radio_button7_1) {
            sum = sum + 10;
            surveyOption.append("10,");

        }
        if(radioGroup7.getCheckedRadioButtonId()==R.id.radio_button7_2) {
            sum = sum + 9;
            surveyOption.append("9,");

        }
        if(radioGroup7.getCheckedRadioButtonId()==R.id.radio_button7_3) {
            sum = sum + 8;
            surveyOption.append("8,");

        }

        if(radioGroup8.getCheckedRadioButtonId()==R.id.radio_button8_1) {
            sum = sum + 10;
            surveyOption.append("10,");

        }
        if(radioGroup8.getCheckedRadioButtonId()==R.id.radio_button8_2) {
            sum = sum + 9;
            surveyOption.append("9,");

        }
        if(radioGroup8.getCheckedRadioButtonId()==R.id.radio_button8_3) {
            sum = sum + 8;
            surveyOption.append("8,");

        }

        if(radioGroup9.getCheckedRadioButtonId()==R.id.radio_button9_1) {
            sum = sum + 10;
            surveyOption.append("10");

        }
        if(radioGroup9.getCheckedRadioButtonId()==R.id.radio_button9_2) {
            sum = sum + 9;
            surveyOption.append("9");

        }
        if(radioGroup9.getCheckedRadioButtonId()==R.id.radio_button9_3) {
            sum = sum + 8;
            surveyOption.append("8");

        }

        Log.d("Sum",""+sum);
        Log.d("Survey",""+surveyOption);
        return sum;

    }



}
