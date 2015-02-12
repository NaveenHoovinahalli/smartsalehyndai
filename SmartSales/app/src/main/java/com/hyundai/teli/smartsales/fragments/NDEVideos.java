package com.hyundai.teli.smartsales.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.models.NDEDetail;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by naveen on 10/2/15.
 */
public class NDEVideos extends Fragment{

    @InjectView(R.id.nde_pager_image)
    ImageView imageView;

    @InjectView(R.id.nde_pager_video)
    VideoView playVideo;

    ProgressDialog mPbar;
    MediaController controller;

    String isVideo;
    String path;
    String id;
    int images[]={R.drawable.nde1,R.drawable.nde2,R.drawable.nde3,R.drawable.nde4,R.drawable.nde5,R.drawable.nde6,R.drawable.nde7,
            R.drawable.nde8,R.drawable.nde9,R.drawable.nde10,R.drawable.nde11};

    @SuppressLint("ValidFragment")
    public NDEVideos(NDEDetail ndeDetail) {

        Log.d("NDEVideos", "category" + ndeDetail.getCategory());
        Log.d("NDEVideos","Id"+ ndeDetail.getId());
        Log.d("NDEVideos","video"+ ndeDetail.getIsVideo());
        Log.d("NDEVideos","path"+ ndeDetail.getPath());
        isVideo=ndeDetail.getIsVideo();
        path=ndeDetail.getPath();
        id=ndeDetail.getId();

    }

    public NDEVideos(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.ndevideos_pager_item,null);
        ButterKnife.inject(this,view);
        imageView.setImageResource(images[Integer.parseInt(id)]);
        Toast.makeText(getActivity(),"Inside Pager",Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        controller=new MediaController(getActivity());
        controller.setAnchorView(playVideo);

     }

    @OnClick(R.id.nde_pager_video)
    public void onVideoButtonClicked(){

//        Uri videoUri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/raw/intro");
//            playVideo.setVideoURI(videoUri);
//            playVideo.start();

    }

    @OnClick(R.id.nde_pager_image)
    public void onImageClicked(){
        Toast.makeText(getActivity(),"IMAGE",Toast.LENGTH_SHORT).show();

        if(isVideo.toLowerCase().equals("true")){
            if(path!=null && !path.isEmpty()){
                imageView.setVisibility(View.INVISIBLE);
                playVideo.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(),"VIDEO",Toast.LENGTH_SHORT).show();
                Uri videoUri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/raw/intro");
                playVideo.setVideoURI(videoUri);
                playVideo.start();

            }

        }

    }
}
