package com.hyundai.teli.smartsales.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.TextView;

public class HCheckBox extends CheckBox {

	public HCheckBox(Context context) {
		super(context);
	}

	public HCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HCheckBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void setTypeface(Typeface tf, int style) {
		super.setTypeface(tf, style);

		if(!isInEditMode()) {
			if(style == Typeface.BOLD)
				FontManager.setFont(this, style);
			else
				FontManager.setFont(this, 0);			
		}
	}
	
}
