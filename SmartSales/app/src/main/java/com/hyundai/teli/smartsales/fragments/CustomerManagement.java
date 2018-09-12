package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.Consultation;
import com.hyundai.teli.smartsales.adapters.CustomerListAdapter;
import com.hyundai.teli.smartsales.models.CustomerWalkinFromServer;
import com.hyundai.teli.smartsales.utils.AndroidUtils;
import com.hyundai.teli.smartsales.utils.Constants;
import com.hyundai.teli.smartsales.utils.HyDataManager;
import com.hyundai.teli.smartsales.utils.HyRequestQueue;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by naveen on 2/8/15.
 */
public class CustomerManagement extends BaseFragment implements AdapterView.OnItemClickListener {

    ArrayList<CustomerWalkinFromServer> customerWalkinFromServer;

    @InjectView(R.id.listViewcustomer)
    ListView listCustomer;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_management, null);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(AndroidUtils.isNetworkOnline(getActivity()))
        fetchValues();
        else
            loadFromSavedPath();
    }

    private void loadFromSavedPath() {
        String data= HyDataManager.init(getActivity()).getCustomerData();
        Log.d("Customer","data"+data);
        if(!data.isEmpty()) {
            Gson gson = new Gson();
            customerWalkinFromServer = gson.fromJson(data, new TypeToken<List<CustomerWalkinFromServer>>() {
            }.getType());
            if (customerWalkinFromServer.size() > 0)
                setList();
        }

    }

    private void fetchValues() {
        String url=String.format(Constants.CUSTOMER_DETAILS ,"1", AndroidUtils.getDeviceImei(getActivity().getApplicationContext()));

        Log.d("CustomerManagment",""+url);

        JsonArrayRequest customerRequest=new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                       Log.d("Customer","Response-"+response.toString());
                        parceJson(response);
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        HyRequestQueue.getInstance(getActivity()).getRequestQueue().add(customerRequest);
    }

    private void parceJson(JSONArray response) {

        Gson gson=new Gson();
        customerWalkinFromServer=gson.fromJson(response.toString(),new TypeToken<List<CustomerWalkinFromServer>>() {
        }.getType());

            if(customerWalkinFromServer.size()>0) {
                String data=new Gson().toJson(customerWalkinFromServer);
                HyDataManager.init(getActivity()).saveCustomerData(data);
                setList();
            }
    }

    public void setList(){
        listCustomer.setAdapter(new CustomerListAdapter(getActivity(),customerWalkinFromServer));
        listCustomer.setOnItemClickListener(this);

    }

    @OnClick(R.id.registerButton)
    public void registerClick(View view) {
        ((Consultation) getActivity()).showRegistration();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

     CustomerWalkinFromServer oneItem= customerWalkinFromServer.get(position);

        String item= new Gson().toJson(oneItem);
        HyDataManager.init(getActivity()).saveCustomerItem(item);
        ((Consultation) getActivity()).showRegistration();

    }
}
