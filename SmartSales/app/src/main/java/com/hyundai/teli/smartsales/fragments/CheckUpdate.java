package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.adapters.UpdateAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by nith on 2/8/15.
 */
public class CheckUpdate extends BaseFragment {

    @InjectView(R.id.updates_view)
    GridView mUpdateView;

    ArrayList<String> carNames;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_all,null);
        ButterKnife.inject(this, view);
        carNames = new ArrayList<>();
        carNames.add("Elantra");
        carNames.add("Elite i20");
        carNames.add("EON");
        carNames.add("i10");
        carNames.add("i10 Grand");
        carNames.add("SantaFe");
        carNames.add("Verna");
        carNames.add("Xcent");
        mUpdateView.setAdapter(new UpdateAdapter(getActivity(), carNames));
        return view;
    }
}
