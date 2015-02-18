package com.hyundai.teli.smartsales.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.adapters.ListAdapter;
import com.hyundai.teli.smartsales.adapters.StyleInteriorAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class StyleInterior extends BaseFragment implements AdapterView.OnItemClickListener, ViewPager.OnPageChangeListener, View.OnClickListener {

    @InjectView(R.id.interior_pager)
    ViewPager interiorPager;

    @InjectView(R.id.interior_button)
    ImageButton interiorButton;

    @InjectView(R.id.exterior_button)
     ImageButton exteriorButton;

    @InjectView(R.id.style_list)
    ListView styleInteriorList;

    @InjectView(R.id.interior_main_image)
    ImageView interiorMainImage;

    @InjectView(R.id.style_interior_exterior_ll)
    LinearLayout linearLayoutInterior;

    @InjectView(R.id.relativeLayout_hotspot)
    RelativeLayout layoutHotspot;

    int[] images={R.drawable.int1,R.drawable.int2,R.drawable.int3,
            R.drawable.int4,R.drawable.int5,R.drawable.int6};
    String[] names={"Interior 1","Interior 2","Interior 3","Interior 4","Interior 5",
            "Interior 6"};
    View previousView=null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_style_interior,null);
        ButterKnife.inject(this,view);
        interiorMainImage.setImageResource(R.drawable.int0);
        setFragment();
        return view;
    }

    private void setFragment() {

       linearLayoutInterior.setBackgroundColor((Color.parseColor("#657FBD")));
        setList();
        setPager();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setImageHotSpot();
    }

    private void setImageHotSpot() {


        for(int i=1;i<7;i++){
            final RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin=30*i;
            params.rightMargin=35*i;
            params.topMargin=30*i;
            final ImageView imageView=new ImageView(getActivity());
            imageView.setImageResource(R.drawable.btn_add_plus);
            imageView.setTag(i);
            layoutHotspot.addView(imageView,params);
            imageView.setOnClickListener(this);
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position= (int) imageView.getTag();
//                    setPager();
//                    interiorPager.setCurrentItem(position-1);
//                    Toast.makeText(getActivity(),"Position"+position,Toast.LENGTH_SHORT).show();
//                    ViewSelection(getViewByPosition((position-1),styleInteriorList));
//
//                }
//            });

        }

    }

    private void setPager() {
        interiorPager.setAdapter(new StyleInteriorAdapter(getActivity().getSupportFragmentManager(),images));
         interiorPager.setOnPageChangeListener(this);
    }

    private void setList() {
        styleInteriorList.setAdapter(new ListAdapter(names,getActivity(),false));
        styleInteriorList.setOnItemClickListener(this);
    }

    @OnClick(R.id.style_interior_exterior_ll)
    public void onMainImageButtonClicked(){
        interiorMainImage.setVisibility(View.VISIBLE);
        interiorPager.setVisibility(View.INVISIBLE);

        linearLayoutInterior.setBackgroundColor((Color.parseColor("#657FBD")));
        if(previousView!=null)
            previousView.setBackgroundColor((Color.parseColor("#3f3f3f")));

    }

    @OnClick(R.id.exterior_button)
    public void OnEtreriorClicked(){
        StyleExterior styleExterior = new StyleExterior();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, styleExterior).commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       if(interiorMainImage.getVisibility()==View.VISIBLE)
        interiorMainImage.setVisibility(View.INVISIBLE);
        if(interiorPager.getVisibility()==View.INVISIBLE)
        interiorPager.setVisibility(View.VISIBLE);
        linearLayoutInterior.setBackgroundColor((Color.parseColor("#3f3f3f")));
        ViewSelection(view);
        interiorPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {

        ViewSelection(getViewByPosition((i),styleInteriorList));
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private void ViewSelection(View view) {
        if(previousView!=null) {
            previousView.setBackgroundColor((Color.parseColor("#3f3f3f")));
        }else{
            linearLayoutInterior.setBackgroundColor((Color.parseColor("#3f3f3f")));
        }
        view.setBackgroundColor((Color.parseColor("#657FBD")));
        previousView=view;
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    @Override
    public void onClick(View v) {


        int position= (int) v.getTag();
//                    setPager();
                    interiorPager.setCurrentItem(position-1);
                    Toast.makeText(getActivity(), "Position" + position, Toast.LENGTH_SHORT).show();
                    ViewSelection(getViewByPosition((position-1),styleInteriorList));

    }
}
