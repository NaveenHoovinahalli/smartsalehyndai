package com.hyundai.teli.smartsales.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.models.CustomerWalkinFromServer;
import com.hyundai.teli.smartsales.views.HTextView;

import java.util.ArrayList;

/**
 * Created by naveen on 5/3/15.
 */
public class CustomerListAdapter extends BaseAdapter {

    Context context;
    ArrayList<CustomerWalkinFromServer>  customerArray;
    ViewHolder viewHolder;

    public CustomerListAdapter(Context context, ArrayList<CustomerWalkinFromServer> customerWalkinFromServer) {

        this.context=context;
        this.customerArray=customerWalkinFromServer;

    }

    @Override
    public int getCount() {
        return customerArray.size();
    }

    @Override
    public Object getItem(int position) {
        return customerArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null) {

           convertView = View.inflate(context, R.layout.customer_managment_list_item, null);
            viewHolder=new ViewHolder();
            viewHolder.id= (HTextView) convertView.findViewById(R.id.customerId);
            viewHolder.name=(HTextView) convertView.findViewById(R.id.customerName);
            viewHolder.model= (HTextView) convertView.findViewById(R.id.customerModel);
            viewHolder.mobileNo= (HTextView) convertView.findViewById(R.id.customerMobileNo);
            viewHolder.eDate= (HTextView) convertView.findViewById(R.id.customerEqDate);
            viewHolder.NFDate= (HTextView) convertView.findViewById(R.id.customerNFDate);

            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.id.setText(customerArray.get(position).getId());
        viewHolder.name.setText(customerArray.get(position).getCustomerName());
        viewHolder.model.setText(customerArray.get(position).getModel());
            if(!customerArray.get(position).getMobileNumber().isEmpty())
        viewHolder.mobileNo.setText(customerArray.get(position).getMobileNumber());
        viewHolder.eDate.setText(customerArray.get(position).getEnquireDate());
        viewHolder.NFDate.setText(customerArray.get(position).getNextFollowUpDate());

        return convertView;
    }

    class ViewHolder{
        HTextView id,name,model,mobileNo,eDate,NFDate;
    }
}
