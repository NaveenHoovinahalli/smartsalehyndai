package com.hyundai.teli.smartsales.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.MessageDescription;

/**
 * Created by naveen on 18/2/15.
 */
public class MessageListAdapter extends BaseAdapter {

    Context context;
    int size=5;
    ViewHolder viewHolder;
    public MessageListAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

      if(convertView==null) {
          convertView = View.inflate(context, R.layout.message_list_item, null);
          viewHolder=new ViewHolder();
          viewHolder.no= (com.hyundai.teli.smartsales.views.HTextView) convertView.findViewById(R.id.message_no);
          viewHolder.title= (com.hyundai.teli.smartsales.views.HTextView) convertView.findViewById(R.id.message_title);
          viewHolder.date= (com.hyundai.teli.smartsales.views.HTextView) convertView.findViewById(R.id.message_date);
          viewHolder.button= (Button) convertView.findViewById(R.id.message_view);
          viewHolder.button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent=new Intent(context, MessageDescription.class);
                  context.startActivity(intent);
              }
          });
          convertView.setTag(viewHolder);

      }else {
          viewHolder= (ViewHolder) convertView.getTag();
      }
        return convertView;
    }

    class ViewHolder{
        com.hyundai.teli.smartsales.views.HTextView no,title,date;
        Button button;
    }
}
