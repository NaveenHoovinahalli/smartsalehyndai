package com.hyundai.teli.smartsales.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.MessageDescription;
import com.hyundai.teli.smartsales.models.MessageBoardValues;
import com.hyundai.teli.smartsales.views.HTextView;

import java.util.List;

/**
 * Created by naveen on 18/2/15.
 */
public class MessageListAdapter extends BaseAdapter {

    Context context;
    List<MessageBoardValues> messageBoardValueses;
    ViewHolder viewHolder;

    public MessageListAdapter(Context context, List<MessageBoardValues> messageBoardValues) {
        this.context = context;
        this.messageBoardValueses=messageBoardValues;
    }

    @Override
    public int getCount() {
        return messageBoardValueses.size();
    }

    @Override
    public Object getItem(int position) {
        return messageBoardValueses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.message_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.no = (HTextView) convertView.findViewById(R.id.message_no);
            viewHolder.title = (HTextView) convertView.findViewById(R.id.message_title);
            viewHolder.date = (HTextView) convertView.findViewById(R.id.message_date);
            viewHolder.button = (Button) convertView.findViewById(R.id.message_view);
            viewHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String date=messageBoardValueses.get(position).getCreatedDate();
                    String time=messageBoardValueses.get(position).getCreatedTime();
                    String dateTime= date+" "+time ;
                    Intent intent = new Intent(context, MessageDescription.class);
                    intent.putExtra(MessageDescription.NO,""+(position+1));
                    intent.putExtra(MessageDescription.TITLE,messageBoardValueses.get(position).getMessageTitle());
                    intent.putExtra(MessageDescription.DESCRIPTION,messageBoardValueses.get(position).getMessageDetails());
                    intent.putExtra(MessageDescription.DATE,dateTime);

                    context.startActivity(intent);
                }
            });
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        viewHolder.no.setText(""+(position+1));
        viewHolder.title.setText(messageBoardValueses.get(position).getMessageTitle().toString());
        String date=messageBoardValueses.get(position).getCreatedDate();
        String time=messageBoardValueses.get(position).getCreatedTime();
        String dateTime= date+" "+time ;
        viewHolder.date.setText(dateTime);
        String title=messageBoardValueses.get(position).getMessageTitle();

        Log.d("MESSAGE", "title" + title);
        Log.d("MESSAGE","Date"+dateTime);

        return convertView;
    }

    class ViewHolder {
        HTextView no, title, date;
        Button button;
    }
}
