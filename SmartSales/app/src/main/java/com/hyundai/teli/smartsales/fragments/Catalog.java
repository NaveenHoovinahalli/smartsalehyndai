package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.activities.CarDetails;
import com.joanzapata.pdfview.PDFView;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by naveen on 2/8/15.
 */
public class Catalog extends BaseFragment {

    @InjectView(R.id.pdfview)
    PDFView pdfView;

    public static String CATALOG_PATH;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, null);
        ButterKnife.inject(this, view);
        CATALOG_PATH = ((CarDetails)getActivity()).getBasePath() + File.separator + "catalogue/";
        setPdf();
        return view;
    }

    private void setPdf() {
        File file = new File(CATALOG_PATH + "catalog.pdf");

              if (file.exists()) {
                     pdfView.fromFile(file)
//                             .pages(0, 2, 1, 3, 3, 3)
                             .defaultPage(1)
                             .showMinimap(false)
                             .enableSwipe(true)
                             .load();


        }
    }
}
