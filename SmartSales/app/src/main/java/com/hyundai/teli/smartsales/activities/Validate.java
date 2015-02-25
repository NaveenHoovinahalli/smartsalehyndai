package com.hyundai.teli.smartsales.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.TextView;

import com.hyundai.teli.smartsales.R;
import com.hyundai.teli.smartsales.views.adapters.NumericWheelAdapter;
import com.hyundai.teli.smartsales.views.interfaces.OnWheelChangedListener;
import com.hyundai.teli.smartsales.views.interfaces.OnWheelScrollListener;
import com.hyundai.teli.smartsales.views.spinner_wheel.AbstractWheel;

import org.json.JSONException;
import org.json.JSONObject;

public class Validate extends ActionBarActivity {

    private boolean wheelScrolled = false;
    private String mDealerId;
    private int[] key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate);

        Intent receivedIntent = getIntent();
        mDealerId = receivedIntent.getStringExtra("DEALER_ID");

        initWheel(R.id.password1);
        initWheel(R.id.password2);
        initWheel(R.id.password3);
        initWheel(R.id.password4);
        initWheel(R.id.password5);
        initWheel(R.id.password6);
        initWheel(R.id.password7);
        initWheel(R.id.password8);
        getDealerId();
        updateStatus();
    }

    private void getDealerId() {
        Log.d("Validate", "Dealer ID::" + mDealerId);
        try {
            JSONObject jsonObject = new JSONObject(mDealerId);
            mDealerId = jsonObject.getString("auth_code");
            key = new int[mDealerId.length()];
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < mDealerId.length(); i++) {
            key[i] = mDealerId.charAt(i) - '0';
            Log.d("Validate", "Key Value::" + key[i]);
        }
    }

    private void initWheel(int id) {
        AbstractWheel wheel = getWheel(id);
        wheel.setViewAdapter(new NumericWheelAdapter(this, 0, 9));
        wheel.setCurrentItem((int) (Math.random() * 10));
        wheel.addChangingListener(changedListener);
        wheel.addScrollingListener(scrolledListener);
        wheel.setCyclic(true);
        wheel.setInterpolator(new AnticipateOvershootInterpolator());
    }

    private AbstractWheel getWheel(int id) {
        return (AbstractWheel) findViewById(id);
    }

    OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
        public void onScrollingStarted(AbstractWheel wheel) {
            wheelScrolled = true;
        }

        public void onScrollingFinished(AbstractWheel wheel) {
            wheelScrolled = false;
            updateStatus();
        }
    };

    private OnWheelChangedListener changedListener = new OnWheelChangedListener() {
        public void onChanged(AbstractWheel wheel, int oldValue, int newValue) {
            if (!wheelScrolled) {
                updateStatus();
            }
        }
    };

    private void updateStatus() {
        TextView text = (TextView) findViewById(R.id.pwd_status);

        if (testPin(key[0], key[1], key[2], key[3], key[4], key[5], key[6], key[7])) {
            text.setText("PIN ACCEPTED!");
            Intent openHome = new Intent(Validate.this, Home.class);
            startActivity(openHome);
            getSharedPreferences("HYUNDAI_PREFERENCE", Context.MODE_PRIVATE)
                    .edit()
                    .putBoolean("SIGN_UP", true)
                    .commit();
            finish();
        } else {
            text.setText("INVALID PIN");
        }
    }

    private boolean testPin(int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8) {

        return testWheelValue(R.id.password1, v1) &&
                testWheelValue(R.id.password2, v2) &&
                testWheelValue(R.id.password3, v3) &&
                testWheelValue(R.id.password4, v4) &&
                testWheelValue(R.id.password5, v5) &&
                testWheelValue(R.id.password6, v6) &&
                testWheelValue(R.id.password7, v7) &&
                testWheelValue(R.id.password8, v8);
    }

    private boolean testWheelValue(int id, int value) {
        return getWheel(id).getCurrentItem() == value;
    }
}
