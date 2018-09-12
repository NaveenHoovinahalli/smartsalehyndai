package com.hyundai.teli.smartsales.adapters;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.models.CarName;
import com.hyundai.teli.smartsales.utils.BusProvider;
import com.hyundai.teli.smartsales.utils.Constants;
import com.hyundai.teli.smartsales.views.HTextView;
import com.squareup.otto.Produce;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by naveen on 2/8/15.
 */
public class UpdateAdapter extends BaseAdapter {

    private ArrayList<CarName> mCarInfo;
    private Activity mContext;
    private ViewHolder viewHolder;
    private String mProgress;

    public UpdateAdapter(Activity context, ArrayList<CarName> carInfo) {
        this.mContext = context;
        this.mCarInfo = carInfo;
    }

    @Override
    public int getCount() {
        return mCarInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return mCarInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, final ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            view = inflater.inflate(R.layout.update_element, null, true);
            viewHolder = new ViewHolder();
            viewHolder.carName = (HTextView) view.findViewById(R.id.title_view);
            viewHolder.date = (HTextView) view.findViewById(R.id.date_view);
            viewHolder.desc = (HTextView) view.findViewById(R.id.desc_view);
            viewHolder.btnUpdate = (ImageButton) view.findViewById(R.id.update_bt);
            viewHolder.progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
            viewHolder.progress = (HTextView) view.findViewById(R.id.progress_view);
            viewHolder.status = (HTextView) view.findViewById(R.id.state_view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Position::" + mCarInfo.get(position).getId(), Toast.LENGTH_SHORT).show();
                viewHolder.desc.setText("Vehicle update is available");
                viewHolder.status.setText("Downloading...");
                new DownloadFileFromURL().execute(mCarInfo.get(position).getId());

            }
        });
        viewHolder.carName.setText(mCarInfo.get(position).getCarName());
        return view;
    }

    class ViewHolder {
        private HTextView carName;
        private HTextView date;
        private HTextView desc;
        private ImageButton btnUpdate;
        private ProgressBar progressBar;
        private HTextView progress;
        private HTextView status;
    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... id) {
            Log.d("Update Adapter", "DOWNLOAD ID::" + id);
            int count;
            try {
                URL url = new URL(Constants.DOWNLOAD + id[0] + "/");
                Log.d("Update Adapter", "DOWNLOAD URL::" + url);
                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file
                OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Hyundai/");
                Log.d("Update Adapter", "Output Path::" + output);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
//            BusProvider.getInstance().post(progress[0]);
            mProgress = progress[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    /*@Produce
    public String produceEvent() {
        return mProgress;
    }*/
}
