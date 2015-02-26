package com.hyundai.teli.smartsales.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.CarDetails;
import com.hyundai.teli.smartsales.models.CarName;
import com.hyundai.teli.smartsales.utils.Constants;
import com.hyundai.teli.smartsales.utils.HyRequestQueue;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Showroom extends BaseFragment {

    @InjectView(R.id.car_container)
    LinearLayout carContainer;

    @InjectView(R.id.progressBar)
    ProgressBar mProgressBar;

    ArrayList<ImageView> carDescriptionList = new ArrayList<>();

    int[] cars = new int[]{
            R.drawable.car_accent,
            R.drawable.car_azera,
            R.drawable.car_elantra,
            R.drawable.car_genesis,
            R.drawable.car_i30,
            R.drawable.car_i10,
            R.drawable.car_ix35,
            R.drawable.car_santafe,
            R.drawable.car_sonata
    };

    int[] carsDesc = new int[]{
            R.drawable.car_accent_desc,
            R.drawable.car_azera_desc,
            R.drawable.car_elantra_desc,
            R.drawable.car_genesis_desc,
            R.drawable.car_i30_desc,
            R.drawable.car_i10_desc,
            R.drawable.car_ix35_desc,
            R.drawable.car_santafe_desc,
            R.drawable.car_sonata_desc
    };
    private LinearLayout carDetailIcons;
    private int previousId = -1;
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_showroom, null);
        ButterKnife.inject(this, view);
        mContext = getActivity();
//        addCars();
        parseJson();
        return view;
    }

    private void parseJson() {
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Hyundai/Thumbnails/allcars.json");
            if (!file.exists()) {
                fetchCarInfo();
            } else {
                FileInputStream stream = new FileInputStream(file);
                String jsonStr = null;
                try {
                    FileChannel fc = stream.getChannel();
                    MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

                    jsonStr = Charset.defaultCharset().decode(bb).toString();

                } finally {
                    stream.close();
                }
                JSONArray response = new JSONArray(jsonStr);
                Type listType = new TypeToken<ArrayList<CarName>>() {
                }.getType();
                final ArrayList<CarName> carNames = new Gson().fromJson(response.toString(), listType);
                checkDirectory(carNames);
                Log.d("Showroom", "Response::" + carNames);

            }
        } catch (IOException fe) {
            fe.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void fetchCarInfo() {
        JsonArrayRequest carsRequest = new JsonArrayRequest(Constants.ALL_CARS,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Showroom", "Response Fetch Car Info Success::" + response);
                        Type listType = new TypeToken<ArrayList<CarName>>() {
                        }.getType();
                        final ArrayList<CarName> carNames = new Gson().fromJson(response.toString(), listType);
                        try {
                            FileWriter fw = new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Hyundai/Thumbnails/allcars.json");
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write(response.toString());
                            bw.flush();
                            bw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Handler mHandler = new Handler();

                        final Runnable myRunnable = new Runnable() {
                            public void run() {
                                checkDirectory(carNames);
                            }
                        };
                        mHandler.post(myRunnable);
//                        showToast();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Showroom", "Response Error::" + error);
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
        HyRequestQueue.getInstance(getActivity()).getRequestQueue().add(carsRequest);
    }

    private void checkDirectory(ArrayList<CarName> carNames) {
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File thumbnailsPath = new File(Environment.getExternalStorageDirectory()
                        + File.separator
                        + Constants.SDCARD_THUMBNAILS);
                Log.d("Showroom", "Thumbnail Path::" + thumbnailsPath);
                if (!thumbnailsPath.exists())
                    thumbnailsPath.mkdirs();

                for (int i = 0; i < carNames.size(); i++) {
                    File carFolder = new File(thumbnailsPath + "/" + carNames.get(i).getCarName());
                    if (!carFolder.exists()) {
                        carFolder.mkdirs();
                    }
                    Log.d("Showroom", "Car Folder Path::" + carFolder);
                    File image = new File(carFolder + "/", carNames.get(i).getCarThumbnail().replace("/media/tablet_images/"
                            + carNames.get(i).getCarName().replace(" ", "") + "/thumbnail/", ""));
                    Log.d("Showroom", "Image Path::" + image);
                    if (!image.exists()) {
                        Log.d("Showroom", "No Image !!");
                        downloadImages(carFolder, carNames.get(i).getCarName(), carNames.get(i).getCarThumbnail());
                        downloadImages(carFolder, carNames.get(i).getCarName(), carNames.get(i).getCarDescription());
                        downloadImages(carFolder, carNames.get(i).getCarName(), carNames.get(i).getCarLogo());
                    }
                }
                showCars(carNames);
            } else {
                Toast.makeText(getActivity(), "Error! No SDCARD Found!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void downloadImages(final File carFolderPath, final String carName, final String imagePath) {
        Log.d("Showroom", "Download Images::");
        Target mTarget = new Target() {

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                // Perform simple file operation tor store this bitmap to your sd card
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File file = new File(carFolderPath + "/" +
                                imagePath.replace("/media/tablet_images/" +
                                        carName.replace(" ", "") + "/thumbnail/", ""));
                        try {
                            if (!file.exists()) {
                                file.createNewFile();
                                FileOutputStream fostream = new FileOutputStream(file);
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fostream);
                                fostream.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }).start();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.d("Showroom", "ALL_MODELS_NAMES onBitmapFailed::" + errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
        mProgressBar.setVisibility(View.GONE);
        if (getActivity() != null)
            Picasso.with(mContext).load(Uri.parse(Constants.BASE_URL + imagePath)).into(mTarget);
    }

    private void showCars(final ArrayList<CarName> carNames) {
        for (int i = 0; i < carNames.size(); i++) {
            String thumbnailpath = "file://" + Environment.getExternalStorageDirectory() + File.separator + "Hyundai/Thumbnails" +
                    File.separator + carNames.get(i).getCarName() + File.separator +
                    carNames.get(i).getCarThumbnail().replace("/media/tablet_images/" + carNames.get(i).
                            getCarName().replace(" ", "") + "/thumbnail/", "");
            String descriptionPath = "file://" + Environment.getExternalStorageDirectory() + File.separator + "Hyundai/Thumbnails" +
                    File.separator + carNames.get(i).getCarName() + File.separator +
                    carNames.get(i).getCarDescription().replace("/media/tablet_images/" + carNames.get(i).
                            getCarName().replace(" ", "") + "/thumbnail/", "");
            Log.d("Showroom", "Path::" + thumbnailpath);
            final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 50, 0);
            LinearLayout.LayoutParams carImageParams = new LinearLayout.LayoutParams(484, 650);
            LinearLayout.LayoutParams carDescImageParams = new LinearLayout.LayoutParams(484, 372);

            final LinearLayout carHolder = new LinearLayout(mContext);
            carHolder.setLayoutParams(lp);
            carHolder.setOrientation(LinearLayout.VERTICAL);

            ImageView carImage = new ImageView(mContext);
            carImage.setLayoutParams(carImageParams);
            carImage.setScaleType(ImageView.ScaleType.FIT_XY);
//            carImage.setImageResource(cars[i]);
            final File carPath = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator + "Hyundai/cars/" + carNames.get(i).getCarName().replace(" ", ""));
            if (carPath.exists())
                Picasso.with(mContext).load(Uri.parse(thumbnailpath)).placeholder(R.drawable.car_placeholder).into(carImage);
            else {
                Bitmap bitmapImage = BitmapFactory.decodeFile(thumbnailpath.replace("file://", ""));
                Drawable drawableImage = new BitmapDrawable(bitmapImage);
                carImage.setBackgroundDrawable(drawableImage);
                carImage.setImageResource(R.drawable.car_placeholder);
            }


            final ImageView carDescription = new ImageView(mContext);
            carDescription.setLayoutParams(carDescImageParams);
            carDescription.setScaleType(ImageView.ScaleType.FIT_XY);
//            carDescription.setImageResource(carsDesc[i]);
            Picasso.with(mContext).load(Uri.parse(descriptionPath)).placeholder(R.drawable.car_description).into(carDescription);

            carHolder.addView(carImage);
            carHolder.addView(carDescription);

            carContainer.addView(carHolder);

            carImage.setId(i);
            carDescription.setId(i);
            carDescription.setTag("carDesc" + i);

            carDescriptionList.add(carDescription);

            final int finalI = i;
            carImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (carPath.exists()) {
                        boolean estimate = getActivity().getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE).getBoolean("ESTIMATE", false);
                        if (estimate) {
                            Intent openCarDetails = new Intent(getActivity(), CarDetails.class);
                            openCarDetails.putExtra("CAR_NAME", carNames.get(finalI).getCarName());
                            openCarDetails.putExtra("BASE_PATH", carPath.getPath());
                            openCarDetails.putExtra("TAB", "ESTIMATE");
                            startActivity(openCarDetails);
                        } else {
                            getActivity().getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE)
                                    .edit()
                                    .putString("CAR", carNames.get(finalI).getCarName())
                                    .putString("BASE_PATH", carPath.getPath())
                                    .commit();
                        }
                    }
                }
            });

            carDescription.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!carPath.exists()) {
                        showToast();
                        return;
                    }
                    if (previousId != view.getId() && previousId != -1) {
                        carDetailIcons.findViewById(previousId);
                        carDetailIcons.setVisibility(View.GONE);
                        carDescriptionList.get(previousId).setVisibility(View.VISIBLE);
                    }
                    carDescription.setVisibility(View.GONE);
                    previousId = view.getId();
                    LayoutInflater inflater = (LayoutInflater)
                            mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    carDetailIcons = (LinearLayout) inflater.inflate(R.layout.popup_shortcuts, null);
                    carHolder.addView(carDetailIcons);
                    ImageView btnVR = (ImageView) carDetailIcons.findViewById(R.id.pop_main_vr);
                    ImageView btnInfo = (ImageView) carDetailIcons.findViewById(R.id.pop_main_info);
                    ImageView btnEstimate = (ImageView) carDetailIcons.findViewById(R.id.pop_main_estimate);
                    ImageView btnComparing = (ImageView) carDetailIcons.findViewById(R.id.pop_main_comparing);

                    btnVR.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (carPath.exists()) {
                                Intent openCarDetails = new Intent(getActivity(), CarDetails.class);
                                openCarDetails.putExtra("CAR_NAME", carNames.get(finalI).getCarName());
                                openCarDetails.putExtra("BASE_PATH", carPath.getPath());
                                openCarDetails.putExtra("TAB", "VR");
                                startActivity(openCarDetails);
                            } else
                                showToast();
                        }
                    });
                    btnInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (carPath.exists()) {
                                Intent openCarDetails = new Intent(getActivity(), CarDetails.class);
                                openCarDetails.putExtra("CAR_NAME", carNames.get(finalI).getCarName());
                                openCarDetails.putExtra("BASE_PATH", carPath.getPath());
                                openCarDetails.putExtra("TAB", "INFO");
                                startActivity(openCarDetails);
                            } else
                                showToast();
                        }
                    });
                    btnEstimate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (carPath.exists()) {
                                Intent openCarDetails = new Intent(getActivity(), CarDetails.class);
                                openCarDetails.putExtra("CAR_NAME", carNames.get(finalI).getCarName());
                                openCarDetails.putExtra("BASE_PATH", carPath.getPath());
                                openCarDetails.putExtra("TAB", "ESTIMATE");
                                startActivity(openCarDetails);
                            } else
                                showToast();
                        }
                    });
                    btnComparing.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (carPath.exists()) {
                                Intent openCarDetails = new Intent(getActivity(), CarDetails.class);
                                openCarDetails.putExtra("CAR_NAME", carNames.get(finalI).getCarName());
                                openCarDetails.putExtra("BASE_PATH", carPath.getPath());
                                openCarDetails.putExtra("TAB", "COMPARE");
                                startActivity(openCarDetails);
                            } else
                                showToast();
                        }
                    });
                }
            });
        }
    }

    private void showToast() {
        Toast.makeText(getActivity(), "Sorry! No Data to Display." +
                        "\n" + "Go to SETTINGS and Download Data",
                Toast.LENGTH_SHORT).show();
    }
}