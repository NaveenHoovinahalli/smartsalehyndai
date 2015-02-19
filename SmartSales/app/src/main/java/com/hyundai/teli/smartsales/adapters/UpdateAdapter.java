package com.hyundai.teli.smartsales.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.views.HTextView;

import java.util.ArrayList;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class UpdateAdapter extends BaseAdapter {

    private Activity mContext;
    private ViewHolder viewHolder;
    ArrayList<String> mCarNames = new ArrayList<>();

    public UpdateAdapter(Activity context, ArrayList<String> carNames) {
        this.mContext = context;
        this.mCarNames = carNames;
    }

    @Override
    public int getCount() {
        return mCarNames.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            view = inflater.inflate(R.layout.update_element, null, true);
            viewHolder = new ViewHolder();
            viewHolder.carName = (HTextView) view.findViewById(R.id.title_view);
            viewHolder.date = (HTextView) view.findViewById(R.id.date_view);
            viewHolder.desc = (HTextView) view.findViewById(R.id.desc_view);
            viewHolder.progress = (HTextView) view.findViewById(R.id.progress_view);
            viewHolder.status = (HTextView) view.findViewById(R.id.state_view);
            viewHolder.btnUpdate = (ImageButton) view.findViewById(R.id.update_bt);
            viewHolder.progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.carName.setText(mCarNames.get(position));

        return view;
    }

    class ViewHolder {
        private HTextView carName;
        private HTextView date;
        private HTextView desc;
        private HTextView progress;
        private HTextView status;
        private ImageButton btnUpdate;
        private ProgressBar progressBar;
    }
}
