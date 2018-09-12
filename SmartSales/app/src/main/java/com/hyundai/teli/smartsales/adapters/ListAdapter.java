package com.hyundai.teli.smartsales.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.views.HTextView;

import java.util.ArrayList;

/**
 * Created by naveen on 2/8/15.
 */
public class ListAdapter extends BaseAdapter {

    ArrayList<String> listValues;
    LayoutInflater inflater;
    Context context;
    boolean viewColor;

    public ListAdapter(ArrayList<String> listValues, Context context, boolean viewColor) {
        this.listValues = listValues;
        this.context = context;
        this.viewColor = viewColor;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listValues.size();
    }

    @Override
    public Object getItem(int position) {
        return listValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(context,
                R.layout.list_item,
                null);

        if (position == 0 && viewColor) {
            view.setBackgroundColor((Color.parseColor("#657FBD")));
        }
        HTextView textView = (HTextView) view.findViewById(R.id.list_item_text);
        textView.setText(listValues.get(position));
        return view;
    }
}
