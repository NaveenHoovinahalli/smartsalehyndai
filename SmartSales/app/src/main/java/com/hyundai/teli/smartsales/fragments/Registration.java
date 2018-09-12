package com.hyundai.teli.smartsales.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.CaptureSignature;
import com.hyundai.teli.smartsales.activities.Consultation;
import com.hyundai.teli.smartsales.db_models.CustomerDeliveryChecklist;
import com.hyundai.teli.smartsales.db_models.CustomerWalkin;
import com.hyundai.teli.smartsales.models.CustomerWalkinFromServer;
import com.hyundai.teli.smartsales.utils.AndroidUtils;
import com.hyundai.teli.smartsales.utils.Constants;
import com.hyundai.teli.smartsales.utils.HyDataManager;
import com.hyundai.teli.smartsales.utils.HyRequestQueue;
import com.hyundai.teli.smartsales.views.HCheckBox;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by naveen on 2/8/15.
 */
public class Registration extends BaseFragment implements View.OnFocusChangeListener {

    @InjectView(R.id.etdatepicker)
    HEditText etDatepicker;
    @InjectView(R.id.etfollowdatepicked)
    HEditText etfollowDatepicker;

    @InjectView(R.id.enq_source_spinner)
    Spinner spinnerEnqSource;

    @InjectView(R.id.etCustometType)
    HEditText etCustomerType;

    @InjectView(R.id.full_name)
    HEditText etFullName;

    @InjectView(R.id.radio_group)
    RadioGroup rgGender;

    @InjectView(R.id.customer_email)
    HEditText etEmail;

    @InjectView(R.id.customer_mobile)
    HEditText etMobileNo;

    @InjectView(R.id.et_model)
    HEditText etModel;

    @InjectView(R.id.et_fueltype)
    HEditText etFuelType;

    @InjectView(R.id.enq_type_spinner)
    Spinner spinnerEnqType;

    @InjectView(R.id.testdrive_spinner)
    Spinner spinnerTestDrive;

    @InjectView(R.id.present_spinner)
    Spinner spinnerPresentCar;

    CustomerWalkin customerWalkin;
    CustomerDeliveryChecklist customerDeliveryChecklist;
    public static final int SIGNATURE_ACTIVITY = 1;


    String vinNo;
    String mobileNo;
    private HCheckBox basiCB1,basiCB2,basiCB3,basiCB4,basiCB5,basiCB6,basiCB7,basiCB8,basiCB9,basiCB10,basiCB11,basiCB12,
    basiCB13,basiCB14,basiCB15,basiCB16,basiCB17,basiCB18,basiCB19,basiCB20,basiCB21,basiCB22,basiCB23,basiCB24,basiCB25,basiCB26,basiCB27;

    private HCheckBox receiptCB1,receiptCB2,receiptCB3,receiptCB4,receiptCB5,receiptCB6,
            receiptCB7,receiptCB8,receiptCB9,receiptCB10,receiptCB11,receiptCB12;

    HEditText chkCustomerName;
    HEditText chkSalesConsultant;
    HEditText chkModel;
    HEditText chkDeliveryDate;
    HEditText chkvinNo;
    HEditText chkmobileNo;
        ProgressDialog progressDialog;
     HEditText chkDate;
    List<CustomerDeliveryChecklist> cHKList;
    CustomerWalkinFromServer customerWalkinFromServer;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, null);
        ButterKnife.inject(this, view);
        customerWalkin=new CustomerWalkin();
        customerDeliveryChecklist=new CustomerDeliveryChecklist();
        setcurrentDate();
        etDatepicker.setOnFocusChangeListener(this);
        etfollowDatepicker.setOnFocusChangeListener(this);

        fetchItem();

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setTitle("Please wait....");
        progressDialog.setCancelable(false);
        if(AndroidUtils.isNetworkOnline(getActivity())) {
            getCustomerWalkinfromDB();
            getChecklistFromDb();
        }
        return view;
    }

    private void fetchItem() {
       String oneItem= HyDataManager.init(getActivity()).getCustomerItem();
        if(!oneItem.isEmpty()){
            Gson gson=new Gson();
            customerWalkinFromServer= gson.fromJson(oneItem, CustomerWalkinFromServer.class);
            Log.d("OneItem",customerWalkinFromServer.getCustomerName());
            Log.d("OneItem",customerWalkinFromServer.getEmail());
            Log.d("OneItem",customerWalkinFromServer.getMobileNumber());
            etCustomerType.setText(customerWalkinFromServer.getCustometType());
            etDatepicker.setText(customerWalkinFromServer.getEnquireDate());
            etfollowDatepicker.setText(customerWalkinFromServer.getNextFollowUpDate());
            etEmail.setText(customerWalkinFromServer.getEmail());
            etFuelType.setText(customerWalkinFromServer.getFuelType());
            etModel.setText(customerWalkinFromServer.getModel());
            etFullName.setText(customerWalkinFromServer.getCustomerName());
            etMobileNo.setText(customerWalkinFromServer.getMobileNumber());

            String gender= customerWalkinFromServer.getGender();
            if(gender.equals("Male")){
                rgGender.check(R.id.radio_male);

            }else {
                rgGender.check(R.id.radio_female);

            }

            spinnerEnqSource.setSelection(getIndex(spinnerEnqSource, customerWalkinFromServer.getEnquireSource()));
            spinnerEnqType.setSelection(getIndex(spinnerEnqType, customerWalkinFromServer.getEnquireType()));
            spinnerPresentCar.setSelection(getIndex(spinnerPresentCar,customerWalkinFromServer.getPresentCar()));
            spinnerTestDrive.setSelection(getIndex(spinnerTestDrive,customerWalkinFromServer.getTestDrive()));

            HyDataManager.init(getActivity()).saveCustomerItem("");

        }
    }


    //private method of your class
    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }


    @OnClick({R.id.okButton, R.id.cancelButton, R.id.deliveryCheckListBtn})
    public void OnClickListener(View view) {
        switch (view.getId()) {
            case R.id.okButton:
                saveValues();
//                ((Consultation) getActivity()).hideRegistration();
                break;
            case R.id.cancelButton:
                ((Consultation) getActivity()).hideRegistration();
                break;
            case R.id.deliveryCheckListBtn:
                askVinNo();
        }
    }

    private void saveValues() {
        Log.d("Registration",""+spinnerEnqSource.getSelectedItem().toString());
            if(validatefields()!=null){
                Toast.makeText(getActivity(), "" + validatefields(), Toast.LENGTH_SHORT).show();
                return;
            }else if(AndroidUtils.isNetworkOnline(getActivity())) {
                postRequest();

            }else {

                customerWalkin.setDealerId("1");
                customerWalkin.setEnquireSource(spinnerEnqSource.getSelectedItem().toString());
                customerWalkin.setCustometType(etCustomerType.getText().toString());
                customerWalkin.setEnquireDate(dateReverse(etDatepicker.getText().toString()));
                customerWalkin.setCustomerName(etFullName.getText().toString());
                customerWalkin.setGender(getgenderRadioButton());
                customerWalkin.setNextFollowUpDate(dateReverse(etfollowDatepicker.getText().toString()));
                customerWalkin.setEmail(etEmail.getText().toString());
                customerWalkin.setMobileNumber(etMobileNo.getText().toString());
                customerWalkin.setModel(etModel.getText().toString());
                customerWalkin.setFuelType(etFuelType.getText().toString());
                customerWalkin.setEnquireType(spinnerEnqType.getSelectedItem().toString());
                customerWalkin.setTestDrive(spinnerTestDrive.getSelectedItem().toString());
                customerWalkin.setPresentCar(spinnerPresentCar.getSelectedItem().toString());
                customerWalkin.save();

            }
        ((Consultation) getActivity()).hideRegistration();

    }

    private void postRequest() {
        JSONObject params = new JSONObject();
        try {
//            DealerInfo dealerInfo = DealerInfo.findById(DealerInfo.class, (long) 1);
//            Log.d("Registration", "dealer ID" + dealerInfo.getId());

            params.put("dealer","1");
            params.put("tablet_id",AndroidUtils.getDeviceImei(getActivity().getApplicationContext()));
            params.put("mobile_number",etMobileNo.getText().toString());
            params.put("enquire_source",spinnerEnqSource.getSelectedItem().toString());
            params.put("customer_type",etCustomerType.getText().toString());
            params.put("enquire_date",dateReverse(etDatepicker.getText().toString()));
            params.put("customer_name",etFullName.getText().toString());
            params.put("gender",getgenderRadioButton());
            params.put("next_follow_up_date",dateReverse(etfollowDatepicker.getText().toString()));
            params.put("email",etEmail.getText().toString());
            params.put("model",etModel.getText().toString());
            params.put("fuel_type",etFuelType.getText().toString());
            params.put("enquire_type",spinnerEnqType.getSelectedItem().toString());
            params.put("test_drive",spinnerTestDrive.getSelectedItem().toString());
            params.put("present_car",spinnerPresentCar.getSelectedItem().toString());
            submitCustomerWalkinToServer(params);

            Log.d("Registration", "JsonParams::" + params);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


   public void  submitCustomerWalkinToServer(JSONObject params){
       String url = String.format(Constants.CUSTOMER_WALKIN);

       progressDialog.show();

       JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Registration", "response::" + response.toString());
                        progressDialog.dismiss();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Registration", "Error::" + error.networkResponse.statusCode);
                        progressDialog.dismiss();

                    }
                }) ;
        HyRequestQueue.getInstance(getActivity()).getRequestQueue().add(request);

    }


    public void getCustomerWalkinfromDB(){

        List<CustomerWalkin> customerWalkinList=CustomerWalkin.listAll(CustomerWalkin.class);
        if(customerWalkinList.size()>0) {
            for (int i = 0; i < customerWalkinList.size(); i++) {
                JSONObject params = new JSONObject();
                try {

                    params.put("dealer",customerWalkinList.get(i).getDealerId());
                    params.put("tablet_id", AndroidUtils.getDeviceImei(getActivity().getApplicationContext()));

                    params.put("mobile_number", customerWalkinList.get(i).getMobileNumber());
                    params.put("enquire_source", customerWalkinList.get(i).getEnquireSource());
                    params.put("customer_type", customerWalkinList.get(i).getCustometType());
                    params.put("enquire_date", customerWalkinList.get(i).getEnquireDate());
                    params.put("customer_name", customerWalkinList.get(i).getCustomerName());
                    params.put("gender", customerWalkinList.get(i).getGender());
                    params.put("next_follow_up_date", customerWalkinList.get(i).getNextFollowUpDate());
                    params.put("email", customerWalkinList.get(i).getEmail());
                    params.put("model", customerWalkinList.get(i).getModel());
                    params.put("fuel_type", customerWalkinList.get(i).getFuelType());
                    params.put("enquire_type", customerWalkinList.get(i).getEnquireType());
                    params.put("test_drive", customerWalkinList.get(i).getTestDrive());
                    params.put("present_car", customerWalkinList.get(i).getPresentCar());
                    submitCustomerWalkinToServer(params);
                    customerWalkinList.get(i).delete();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void getChecklistFromDb(){
        progressDialog.show();
         cHKList=CustomerDeliveryChecklist.listAll(CustomerDeliveryChecklist.class);
        if(cHKList.size()>0){
            for(int i=0;i<cHKList.size();i++){
//                cHKList.get(i).delete();

                new MyAsyncTask(i).execute();


            }
        }

        progressDialog.dismiss();
    }

    private String getgenderRadioButton() {
        int id = rgGender.getCheckedRadioButtonId();
        if (id == R.id.radio_female)
            return "Female";
        else if (id == R.id.radio_male)
            return "Male";
        return "";
    }

    private String validatefields() {

        if (etCustomerType.getText().toString().isEmpty())
            return "Please enter customer type";
        else if (etFullName.getText().toString().isEmpty())
            return "Please enter the name";
        else if (rgGender.getCheckedRadioButtonId() == -1)
            return "Please select the gender";
        else if (etMobileNo.getText().toString().length() < 10)
            return "Please enter valid no";
        else if (etModel.getText().toString().isEmpty())
            return "Please enter the model";
        else if (etFuelType.getText().toString().isEmpty())
            return "Please enter the fuel type";
        else if (!etEmail.getText().toString().isEmpty()) {
            if (!isValidEmail(etEmail.getText().toString().trim()))
                return "Please enter valid email";
        }
        return null;
    }

    private String validateCHKfields() {

        if (chkCustomerName.getText().toString().isEmpty())
            return "Please enter customer name";
        else if (chkSalesConsultant.getText().toString().isEmpty())
            return "Please enter the sales consultant name";
        else if (chkmobileNo.getText().toString().length() < 10)
            return "Please enter valid no";
        else if (chkvinNo.getText().toString().isEmpty())
            return "Please enter vin#";
        else if (chkModel.getText().toString().isEmpty())
            return "Please enter model";
        else if(HyDataManager.init(getActivity()).getSignatureImage("1").isEmpty())
            return "Signature is missing";

        return null;
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

    private void askVinNo() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Please enter Vin# or Mobile#");

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
                if ((!vinNo.equals("") && !vinNo.equals("vin#")) || (!mobileNo.equals("") && !mobileNo.equals("mobile#"))) {
                    showDialog();


                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_customer_delivery);

        Button dialogCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button dialogRegister = (Button) dialog.findViewById(R.id.btn_register);
        ImageView signature= (ImageView) dialog.findViewById(R.id.signatureImageView);

        getDialogCheckboxId(dialog);
        dialog.show();
        setcurrentDateCHK();
        chkmobileNo.setText(mobileNo);
        chkvinNo.setText(vinNo);
        chkDeliveryDate.setOnFocusChangeListener(this);
        chkDate.setOnFocusChangeListener(this);
        chkDeliveryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setviewDateCHK(v);
            }
        });
        chkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           setviewDateCHK(v);
            }
        });

        dialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialogRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateCHKfields()!=null) {
                    Toast.makeText(getActivity(), "" +validateCHKfields(), Toast.LENGTH_SHORT).show();
                    return;
                }
//                }else if(AndroidUtils.isNetworkOnline(getActivity())){
////                    new MyAsyncTask().execute();
//                }else {
//                }
                    saveChecklistToDB();

                    dialog.dismiss();
            }
        });

        signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(), CaptureSignature.class);
                getActivity().startActivityForResult(intent, SIGNATURE_ACTIVITY);

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

         Log.d("Registration","Signatire-inside onActivity result");

        switch (requestCode) {
            case SIGNATURE_ACTIVITY:
                if (resultCode == 1) {
                    Bundle bundle = data.getExtras();
                    String status = bundle.getString("status");
                    if (status.equalsIgnoreCase("done")) {
                        String imageUrl= bundle.getString("url");
                        Log.d("Registration","Signatire-"+imageUrl);
                        //content://media/external/images/media/1702
//                            imageView.setImageURI(Uri.parse(imageUrl));
                        Toast toast = Toast.makeText(getActivity(), "Signature capture successful!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 105, 50);
                        toast.show();
                    }
                }
                break;
        }

    }

    private String submitCustomerDeliveryToServer( int i) {



        try {

            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost(Constants.CUSTOMER_DELIVERY_CHECKLIST);
            MultipartEntity entity=new MultipartEntity();

            entity.addPart("dealer", new StringBody("1"));
            entity.addPart("tablet_id", new StringBody(AndroidUtils.getDeviceImei(getActivity().getApplicationContext())));

            entity.addPart("customer_name", new StringBody(cHKList.get(i).getCustomerName()));
            entity.addPart("mobile_number",new StringBody(cHKList.get(i).getMobileNumber()));
            entity.addPart("vin_number",new StringBody(cHKList.get(i).getVinNumber()));
            entity.addPart("model",new StringBody(cHKList.get(i).getModel()));
            entity.addPart("delivery_date",new StringBody(cHKList.get(i).getDeliveryDate()));
            entity.addPart("sales_consultant",new StringBody(cHKList.get(i).getSalesConsultnt()));
            entity.addPart("instrument_panel",new StringBody(cHKList.get(i).getInstrumentPanel()));
            entity.addPart("HVAC_control",new StringBody(cHKList.get(i).getHvacControl()));
            entity.addPart("airflow",new StringBody(cHKList.get(i).getAirflow()));
            entity.addPart("seat_adjustment",new StringBody(cHKList.get(i).getSeatAdjustment()));
            entity.addPart("MID",new StringBody(cHKList.get(i).getMid()));
            entity.addPart("headlight turn signals fog light",new StringBody(cHKList.get(i).getHeadLightFogLight()));
            entity.addPart("gear_shift_indicator",new StringBody(cHKList.get(i).getGearShiftIndicator()));
            entity.addPart("IRVM_cabin_light",new StringBody(cHKList.get(i).getIrvmCabinLight()));
            entity.addPart("vanity_mirror_sun_visar",new StringBody(cHKList.get(i).getVanityMirrorSunVisar()));
            entity.addPart("glove_box_cooling_slot",new StringBody(cHKList.get(i).getGloveBoxColling()));
            entity.addPart("seat_belts",new StringBody(cHKList.get(i).getSeatBelts()));
            entity.addPart("door_locks_child_lock",new StringBody(cHKList.get(i).getDoorLockChildLock()));
            entity.addPart("hazard_warning",new StringBody(cHKList.get(i).getHazardWarning()));
            entity.addPart("bluetooth_and_steering_mounted_controls",new StringBody(cHKList.get(i).getBlueetothandstreeing()));
            entity.addPart("headlight_levelers",new StringBody(cHKList.get(i).getHeadlightLevelers()));
            entity.addPart("gear_shifting_hand_break_lever",new StringBody(cHKList.get(i).getGearShiftHandBreakLeveler()));
            entity.addPart("ORVMs",new StringBody(cHKList.get(i).getOrvms()));
            entity.addPart("power_socket",new StringBody(cHKList.get(i).getPowerSocket()));
            entity.addPart("tilt_and_telescopic_steering",new StringBody(cHKList.get(i).getTiltAndTelescopic()));
            entity.addPart("jack_mounting_points",new StringBody(cHKList.get(i).getJackMountingPoints()));
            entity.addPart("rear_defogger",new StringBody(cHKList.get(i).getRearDefoger()));
            entity.addPart("jack_and_tool_kit_location",new StringBody(cHKList.get(i).getJackTollKitLocation()));
            entity.addPart("towing_hook",new StringBody(cHKList.get(i).getTowingHook()));
            entity.addPart("wiper_functions",new StringBody(cHKList.get(i).getWiperFunctions()));
            entity.addPart("break_oil_engine_oil",new StringBody(cHKList.get(i).getBreakOilEngileOil()));
            entity.addPart("ignition_keys_central_locking",new StringBody(cHKList.get(i).getIgnationKeysCentralLocking()));
            entity.addPart("music_system_controls",new StringBody(cHKList.get(i).getMusicSystemControls()));

            entity.addPart("owners_manual_cum_free_service_coupons",new StringBody(cHKList.get(i).getOwnersManualCumFreeServiceCoupuns()));
            entity.addPart("tool_kit",new StringBody(cHKList.get(i).getToolKit()));
            entity.addPart("jack",new StringBody(cHKList.get(i).getJack()));
            entity.addPart("first_aid_kit",new StringBody(cHKList.get(i).getFirstAidKit()));
            entity.addPart("warning_triangle",new StringBody(cHKList.get(i).getWarningTriangle()));
            entity.addPart("insurance_papers",new StringBody(cHKList.get(i).getInsurancePapers()));
            entity.addPart("registration_documents",new StringBody(cHKList.get(i).getRegistrationDocuments()));
            entity.addPart("roadside_assistance_details",new StringBody(cHKList.get(i).getRoadsideAssistance()));
            entity.addPart("sales_invoice",new StringBody(cHKList.get(i).getSalesInvoice()));
            entity.addPart("payments_receipts",new StringBody(cHKList.get(i).getPaymentsRecits()));
            entity.addPart("fiance_documents",new StringBody(cHKList.get(i).getFinanceDocuments()));
            entity.addPart("extended_warranty_policy",new StringBody(cHKList.get(i).getExtandedWarrantyPolicy()));

            entity.addPart("date",new StringBody(cHKList.get(i).getDate()));

            String imagePath=cHKList.get(i).getCustomerSignature();
            Log.d("Signature","imagepath from DB"+imagePath);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            Log.d("Registration","Bitmap"+bitmap);
            byte[] data = bos.toByteArray();
            ByteArrayBody bab = new ByteArrayBody(data, "user.jpg");
            if (bab != null) {
//                entity.addPart("photo", bab);
                Log.d("Registration","bob"+bab);
                entity.addPart("customer_signature_file",bab);

            }

            Log.d("Registration","entity"+entity);

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


    public class MyAsyncTask extends AsyncTask<String, String, String> {

        int i;
        public MyAsyncTask(int i) {
            this.i=i;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                return submitCustomerDeliveryToServer(i);
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
                cHKList.get(i).delete();
            }
        }
    }

    private void saveChecklistToDB() {
        Log.d("Registration","Signature saved prefrence--"+ HyDataManager.init(getActivity()).getSignatureImage("1"));
        //content://media/external/images/media/17939
        String signaturePath=HyDataManager.init(getActivity()).getSignatureImage("1");


        customerDeliveryChecklist.setDealer("1");
        customerDeliveryChecklist.setCustomerName(chkCustomerName.getText().toString());
        customerDeliveryChecklist.setSalesConsultnt(chkSalesConsultant.getText().toString());
        customerDeliveryChecklist.setVinNumber(chkvinNo.getText().toString());
        customerDeliveryChecklist.setDeliveryDate(dateReverse(chkDeliveryDate.getText().toString()));
        customerDeliveryChecklist.setMobileNumber(chkmobileNo.getText().toString());
        customerDeliveryChecklist.setModel(chkModel.getText().toString());

        customerDeliveryChecklist.setInstrumentPanel(String.valueOf(basiCB1.isChecked()));
        customerDeliveryChecklist.setHvacControl(String.valueOf(basiCB2.isChecked()));
        customerDeliveryChecklist.setAirflow(String.valueOf(basiCB3.isChecked()));
        customerDeliveryChecklist.setSeatAdjustment(String.valueOf(basiCB4.isChecked()));
        customerDeliveryChecklist.setMid(String.valueOf(basiCB5.isChecked()));
        customerDeliveryChecklist.setHeadLightFogLight(String.valueOf(basiCB6.isChecked()));
        customerDeliveryChecklist.setGearShiftIndicator(String.valueOf(basiCB7.isChecked()));
        customerDeliveryChecklist.setIrvmCabinLight(String.valueOf(basiCB8.isChecked()));
        customerDeliveryChecklist.setVanityMirrorSunVisar(String.valueOf(basiCB9.isChecked()));
        customerDeliveryChecklist.setGloveBoxColling(String.valueOf(basiCB10.isChecked()));
        customerDeliveryChecklist.setSeatBelts(String.valueOf(basiCB11.isChecked()));
        customerDeliveryChecklist.setDoorLockChildLock(String.valueOf(basiCB12.isChecked()));
        customerDeliveryChecklist.setHazardWarning(String.valueOf(basiCB13.isChecked()));
        customerDeliveryChecklist.setBlueetothandstreeing(String.valueOf(basiCB14.isChecked()));
        customerDeliveryChecklist.setHeadlightLevelers(String.valueOf(basiCB15.isChecked()));
        customerDeliveryChecklist.setGearShiftHandBreakLeveler(String.valueOf(basiCB16.isChecked()));
        customerDeliveryChecklist.setOrvms(String.valueOf(basiCB17.isChecked()));
        customerDeliveryChecklist.setPowerSocket(String.valueOf(basiCB18.isChecked()));
        customerDeliveryChecklist.setTiltAndTelescopic(String.valueOf(basiCB19.isChecked()));
        customerDeliveryChecklist.setJackMountingPoints(String.valueOf(basiCB20.isChecked()));
        customerDeliveryChecklist.setRearDefoger(String.valueOf(basiCB21.isChecked()));
        customerDeliveryChecklist.setJackTollKitLocation(String.valueOf(basiCB22.isChecked()));
        customerDeliveryChecklist.setTowingHook(String.valueOf(basiCB23.isChecked()));
        customerDeliveryChecklist.setWiperFunctions(String.valueOf(basiCB24.isChecked()));
        customerDeliveryChecklist.setBreakOilEngileOil(String.valueOf(basiCB25.isChecked()));
        customerDeliveryChecklist.setIgnationKeysCentralLocking(String.valueOf(basiCB26.isChecked()));
        customerDeliveryChecklist.setMusicSystemControls(String.valueOf(basiCB27.isChecked()));
        //
        customerDeliveryChecklist.setOwnersManualCumFreeServiceCoupuns(String.valueOf(receiptCB1.isChecked()));
        customerDeliveryChecklist.setToolKit(String.valueOf(receiptCB2.isChecked()));
        customerDeliveryChecklist.setJack(String.valueOf(receiptCB3.isChecked()));
        customerDeliveryChecklist.setFirstAidKit(String.valueOf(receiptCB4.isChecked()));
        customerDeliveryChecklist.setWarningTriangle(String.valueOf(receiptCB5.isChecked()));
        customerDeliveryChecklist.setInsurancePapers(String.valueOf(receiptCB6.isChecked()));
        customerDeliveryChecklist.setRegistrationDocuments(String.valueOf(receiptCB7.isChecked()));
        customerDeliveryChecklist.setRoadsideAssistance(String.valueOf(receiptCB8.isChecked()));
        customerDeliveryChecklist.setSalesInvoice(String.valueOf(receiptCB9.isChecked()));
        customerDeliveryChecklist.setPaymentsRecits(String.valueOf(receiptCB10.isChecked()));
        customerDeliveryChecklist.setFinanceDocuments(String.valueOf(receiptCB11.isChecked()));
        customerDeliveryChecklist.setExtandedWarrantyPolicy(String.valueOf(receiptCB12.isChecked()));

//        customerDeliveryChecklist.setCustomerSignature("/storage/emulated/0/DCIM/Camera/1425460705741.jpg");
        customerDeliveryChecklist.setCustomerSignature(signaturePath);
        customerDeliveryChecklist.setDate(dateReverse(chkDate.getText().toString()));
        customerDeliveryChecklist.save();

        if(AndroidUtils.isNetworkOnline(getActivity()))
            getChecklistFromDb();
    }

    private void getDialogCheckboxId(Dialog dialog) {

        //        vinNochk.setText(vinNo);
        chkvinNo = (HEditText) dialog.findViewById(R.id.vinNumEditText);
        chkCustomerName=(HEditText) dialog.findViewById(R.id.customerFristnameEditText);
        chkSalesConsultant= (HEditText) dialog.findViewById(R.id.sellerFristnameEditText);
        chkDeliveryDate= (HEditText) dialog.findViewById(R.id.deliveryDateEditText);
        chkModel= (HEditText) dialog.findViewById(R.id.modelEditText);
        chkmobileNo= (HEditText) dialog.findViewById(R.id.mobileNoEditText);

        chkDate=(HEditText)  dialog.findViewById(R.id.dateEditText);


         basiCB1 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView1);
        basiCB2 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView2);
        basiCB3 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView3);
         basiCB4 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView4);
         basiCB5 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView5);
         basiCB6 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView6);
         basiCB7 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView7);
         basiCB8 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView8);
         basiCB9 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView9);
         basiCB10 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView10);
         basiCB11 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView11);
        basiCB12 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView12);
         basiCB13 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView13);
         basiCB14 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView14);
         basiCB15 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView15);
         basiCB16 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView16);
         basiCB17 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView17);
         basiCB18 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView18);
         basiCB19 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView19);
         basiCB20 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView20);
         basiCB21 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView21);
         basiCB22 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView22);
         basiCB23 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView23);
         basiCB24 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView24);
         basiCB25 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView25);
         basiCB26 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView26);
         basiCB27 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextView27);

        //
         receiptCB1 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextViewb1);
         receiptCB2 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextViewb2);
         receiptCB3 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextViewb3);
         receiptCB4 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextViewb4);
         receiptCB5 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextViewb5);
         receiptCB6 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextViewb6);
         receiptCB7 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextViewb7);
         receiptCB8 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextViewb8);
         receiptCB9 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextViewb9);
         receiptCB10 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextViewb10);
         receiptCB11 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextViewb11);
         receiptCB12 = (HCheckBox) dialog.findViewById(R.id.abcCheckedTextViewb12);


    }


    private void setviewDate(final View view) {

        Calendar calendar = Calendar.getInstance();
        int mMonth = calendar.get(Calendar.MONTH);
        int mYear = ((calendar.get(Calendar.YEAR)));
        int mDay = calendar.get(Calendar.DATE);

        Log.d("DOB", "DATEPICKER" + mMonth + " -" + mYear + "-" + mDay);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Log.d("DATESIGNUP", "i" + i + "i2" + i2 + "i3" + i3);
                if (view.getId() == R.id.etdatepicker) {
                    etDatepicker.setText("" + i3 + "-" + (i2 + 1) + "-" + i);
                    etDatepicker.clearFocus();
                } else if(view.getId()==R.id.etfollowdatepicked) {
                    etfollowDatepicker.setText("" + i3 + "-" + (i2 + 1) + "-" + i);
                    etfollowDatepicker.clearFocus();
                }   else if(view.getId()==R.id.deliveryDateEditText){
                    chkDeliveryDate.setText("" + i3 + "-" + (i2 + 1) + "-" + i);
                    chkDeliveryDate.clearFocus();
                }else if(view.getId()==R.id.dateEditText){
                    chkDate.setText("" + i3 + "-" + (i2 + 1) + "-" + i);
                    chkDate.clearFocus();
                }
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus)
            setviewDate(v);

    }

 public  String   dateReverse(String rDate){
        String seperator[] = rDate.split("-");
        Log.d("Registration",""+seperator[seperator.length-1]);
     Log.d("Registration",""+seperator[seperator.length-2]);
     Log.d("Registration",""+seperator[seperator.length-3]);
     return (seperator[seperator.length-1]+"-"+seperator[seperator.length-2]+"-"+seperator[seperator.length-3]);

 }
    private void setcurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int mMonth = calendar.get(Calendar.MONTH);
        int mYear = ((calendar.get(Calendar.YEAR)));
        int mDay = calendar.get(Calendar.DATE);

        Log.d("Date", "DATEPICKER" + mDay + " /" + mMonth + "/" + mYear);
        etDatepicker.setText(mDay + "-" + mMonth + "-" + mYear);
        etfollowDatepicker.setText(mDay + "-" + mMonth + "-" + mYear);

    }

    private void setcurrentDateCHK() {
        Calendar calendar = Calendar.getInstance();
        int mMonth = calendar.get(Calendar.MONTH);
        int mYear = ((calendar.get(Calendar.YEAR)));
        int mDay = calendar.get(Calendar.DATE);

        Log.d("Date", "DATEPICKER" + mDay + " /" + mMonth + "/" + mYear);
        chkDeliveryDate.setText(mDay + "-" + mMonth + "-" + mYear);
        chkDate.setText(mDay + "-" + mMonth + "-" + mYear);

    }


    private void setviewDateCHK(final View view) {

        Calendar calendar = Calendar.getInstance();
        int mMonth = calendar.get(Calendar.MONTH);
        int mYear = ((calendar.get(Calendar.YEAR)));
        int mDay = calendar.get(Calendar.DATE);

        Log.d("DOB", "DATEPICKER" + mMonth + " -" + mYear + "-" + mDay);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Log.d("DATESIGNUP", "i" + i + "i2" + i2 + "i3" + i3);
                    if(view.getId()==R.id.deliveryDateEditText) {
                        chkDeliveryDate.setText("" + i3 + "-" + (i2 + 1) + "-" + i);
                        chkDeliveryDate.clearFocus();
                    }else {
                        chkDate.setText("" + i3 + "-" + (i2 + 1) + "-" + i);
                        chkDate.clearFocus();
                    }
                        }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();

    }

}
