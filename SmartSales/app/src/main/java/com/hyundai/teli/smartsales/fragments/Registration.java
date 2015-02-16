package com.hyundai.teli.smartsales.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.Consultation;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Registration extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration,null);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick({R.id.okButton, R.id.cancelButton, R.id.deliveryCheckListBtn})
    public void OnClickListener(View view){
        switch (view.getId()){
            case R.id.okButton:
                ((Consultation)getActivity()).hideRegistration();
                break;
            case R.id.cancelButton:
                ((Consultation)getActivity()).hideRegistration();
                break;
            case R.id.deliveryCheckListBtn:
                showDialog();
        }
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_customer_delivery);
        Button dialogCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button dialogRegister = (Button) dialog.findViewById(R.id.btn_register);

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
}
