package com.hyundai.teli.smartsales.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class HEditText extends EditText {

    public HEditText(Context context) {
        super(context);
    }

    public HEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        if (!isInEditMode()) {

        }
    }
}
