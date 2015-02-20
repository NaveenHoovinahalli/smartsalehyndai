package com.hyundai.teli.smartsales.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.CarDetails;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Showroom extends BaseFragment {

    @InjectView(R.id.car_container)
    LinearLayout carContainer;

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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_showroom, null);
        ButterKnife.inject(this, view);
        addCars();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void addCars() {
        for (int i = 0; i < cars.length; i++) {
            final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 50, 0);

            final LinearLayout carHolder = new LinearLayout(getActivity());
            carHolder.setLayoutParams(lp);
            carHolder.setOrientation(LinearLayout.VERTICAL);

            ImageView carImage = new ImageView(getActivity());
            carImage.setImageResource(cars[i]);

            final ImageView carDescription = new ImageView(getActivity());
            carDescription.setImageResource(carsDesc[i]);

            carHolder.addView(carImage);
            carHolder.addView(carDescription);

            carContainer.addView(carHolder);

            carImage.setId(i);
            carDescription.setId(i);
            carDescription.setTag("carDesc" + i);

            carDescriptionList.add(carDescription);

            carImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
                            .edit()
                            .putString("CAR", "ELANTRA")
                            .commit();
                }
            });

            carDescription.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(previousId !=view.getId() && previousId!=-1){
                        carDetailIcons.findViewById(previousId);
                        carDetailIcons.setVisibility(View.GONE);
                        carDescriptionList.get(previousId).setVisibility(View.VISIBLE);
                    }
                    carDescription.setVisibility(View.GONE);
                    previousId = view.getId();
                    LayoutInflater inflater = (LayoutInflater)
                            getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    carDetailIcons = (LinearLayout) inflater.inflate(R.layout.popup_shortcuts, null);
                    carHolder.addView(carDetailIcons);
                    ImageView btnVR = (ImageView) carDetailIcons.findViewById(R.id.pop_main_vr);
                    ImageView btnInfo = (ImageView) carDetailIcons.findViewById(R.id.pop_main_info);
                    ImageView btnEstimate = (ImageView) carDetailIcons.findViewById(R.id.pop_main_estimate);
                    ImageView btnComparing = (ImageView) carDetailIcons.findViewById(R.id.pop_main_comparing);

                    btnVR.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent openCarDetails = new Intent(getActivity(), CarDetails.class);
                            openCarDetails.putExtra("TAB", "VR");
                            startActivity(openCarDetails);
                        }
                    });
                    btnInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent openCarDetails = new Intent(getActivity(), CarDetails.class);
                            openCarDetails.putExtra("TAB", "INFO");
                            startActivity(openCarDetails);
                        }
                    });
                    btnEstimate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent openCarDetails = new Intent(getActivity(), CarDetails.class);
                            openCarDetails.putExtra("TAB", "ESTIMATE");
                            startActivity(openCarDetails);
                        }
                    });
                    btnComparing.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent openCarDetails = new Intent(getActivity(), CarDetails.class);
                            openCarDetails.putExtra("TAB", "COMPARE");
                            startActivity(openCarDetails);
                        }
                    });
                }
            });
        }
    }
}