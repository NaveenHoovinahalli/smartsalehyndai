package com.hyundai.teli.smartsales.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class HTextView extends TextView {

	public HTextView(Context context) {
		super(context);
	}

	public HTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
//		if(!isInEditMode())
//			FontManager.setFontFromAttributeSet(context, attrs, this);
	}

	public HTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
//		if(!isInEditMode())
//			FontManager.setFontFromAttributeSet(context, attrs, this);
	}
	
}
