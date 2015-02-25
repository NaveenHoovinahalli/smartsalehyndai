package com.hyundai.teli.smartsales.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hyundai.teli.smartsales.R;

import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class Photo extends BaseFragment {

    @InjectView(R.id.facebook_btn)
    Button facebook;

    SocialAuthAdapter socialAuthAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, null);
        ButterKnife.inject(this, view);
        socialAuthAdapter=new SocialAuthAdapter(new ResponseListener());
        facebook.setBackgroundResource(R.drawable.facebook);

        setFacebook();
        return view;
    }

    private void setFacebook() {
        socialAuthAdapter.addProvider(SocialAuthAdapter.Provider.FACEBOOK,R.drawable.facebook);

        try {
//            socialAuthAdapter.addConfig(SocialAuthAdapter.Provider.FACEBOOK, "800298590050731", "79d14a77a991cd628f46beb35bd2390f", null);

//          socialAuthAdapter.addConfig(SocialAuthAdapter.Provider.FACEBOOK, "297841130364674", "dc9c59d0c72d4f2533580e80ba4c2a59", null);

            socialAuthAdapter.addConfig(SocialAuthAdapter.Provider.FACEBOOK, "409534545880372", "d3db8a47a7861c6bb759d849f9663ad6", null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        socialAuthAdapter.enable(facebook);
    }



    private final class ResponseListener implements DialogListener {
        @Override
        public void onComplete(Bundle values) {

            // Variable to receive message status
            Log.d("Share-Bar", "Authentication Successful");
            Log.d("Share-Bar","Inside response listner");


            String provider= values.getString(SocialAuthAdapter.PROVIDER);

            Log.d("Share-Bar","Connected to "+provider);

        String path="https://techwithdianasaur.wordpress.com/2014/10/27/setting-up-socialauth-in-android-studio/";
            socialAuthAdapter.updateStatus("Hello from Apps", new MessageListener(), true);

            }

        @Override
        public void onError(SocialAuthError socialAuthError) {
            Log.d("Share-Bar e", socialAuthError.getMessage());

        }

        @Override
        public void onCancel() {
            Log.d("Share-Bar", "Authentication Cancelled");

        }

        @Override
        public void onBack() {
            Log.d("Share-Bar", "Dialog Closed by pressing Back Key");

        }
    }








    private final class MessageListener implements SocialAuthListener<Integer> {
        @Override
        public void onExecute(String provider, Integer t) {

            Integer status = t;
            Log.d("Share-Bar ",""+status );
            if (status.intValue() == 200 || status.intValue() == 201 || status.intValue() == 204)
                Toast.makeText(getActivity(), "Message posted on " + provider, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getActivity(), "Message not posted on" + provider, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SocialAuthError e) {

        }
    }

}
