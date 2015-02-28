package com.hyundai.teli.smartsales.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.Consultation;
import com.hyundai.teli.smartsales.views.HEditText;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Registration extends BaseFragment implements View.OnFocusChangeListener{

    @InjectView(R.id.etdatepicker)
    HEditText etDatepicker;
    @InjectView(R.id.etfollowdatepicked)
    HEditText etfollowDatepicker;


    String vinNo;
    String mobileNo;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, null);
        ButterKnife.inject(this, view);
        setcurrentDate();
        etDatepicker.setOnFocusChangeListener(this);
        etfollowDatepicker.setOnFocusChangeListener(this);
        return view;
    }

    private void setcurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int mMonth = calendar.get(Calendar.MONTH);
        int mYear = ((calendar.get(Calendar.YEAR)));
        int mDay = calendar.get(Calendar.DATE);

        Log.d("Date", "DATEPICKER" + mDay + " /" + mMonth + "/" + mYear);
        etDatepicker.setText(mDay + " /" + mMonth + "/" + mYear);
        etfollowDatepicker.setText(mDay + " /" + mMonth + "/" + mYear);

    }

    @OnClick({R.id.okButton, R.id.cancelButton, R.id.deliveryCheckListBtn})
    public void OnClickListener(View view) {
        switch (view.getId()) {
            case R.id.okButton:
                ((Consultation) getActivity()).hideRegistration();
                break;
            case R.id.cancelButton:
                ((Consultation) getActivity()).hideRegistration();
                break;
            case R.id.deliveryCheckListBtn:
                askVinNo();
        }
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

        final EditText editMobileNo=new EditText(getActivity());
        editMobileNo.setInputType(InputType.TYPE_CLASS_NUMBER);
        editMobileNo.setHint("mobile#");
        layout.addView(editMobileNo);
        builder.setView(layout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vinNo = inputVin.getText().toString();
                mobileNo=editMobileNo.getText().toString();
                if((!vinNo.equals("")&& !vinNo.equals("vin#")) || (!mobileNo.equals("") && !mobileNo.equals("mobile#")  ))
                {
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
        HEditText etVin= (HEditText) dialog.findViewById(R.id.vinNumEditText);
        Button dialogCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button dialogRegister = (Button) dialog.findViewById(R.id.btn_register);
        etVin.setText(vinNo);

        dialog.show();

        dialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialogRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }




    private void setviewDate(final View view) {

        Calendar calendar = Calendar.getInstance();
        int mMonth = calendar.get(Calendar.MONTH);
        int mYear = ((calendar.get(Calendar.YEAR)));
        int mDay = calendar.get(Calendar.DATE);

        Log.d("DOB", "DATEPICKER" + mMonth + " -" + mYear + "-" + mDay);



        DatePickerDialog datePickerDialog= new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Log.d("DATESIGNUP","i"+i+"i2"+i2+"i3"+i3);
                if(view.getId()==R.id.etdatepicker)
                etDatepicker.setText("" + i3 + "-" + (i2 + 1) + "-" + i);
                else
                    etfollowDatepicker.setText("" + i3 + "-" + (i2 + 1) + "-" + i);
            }
        },mYear,mMonth,mDay);

        datePickerDialog.show();

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        setviewDate(v);

    }
}
