package com.hyundai.teli.smartsales.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class HEditText extends EditText {

	public HEditText(Context context) {
		super(context);
	}

	public HEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
//		if(!isInEditMode())
//			FontManager.setFontFromAttributeSet(context, attrs, this);
	}

	public HEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
//		if(!isInEditMode())
//			FontManager.setFontFromAttributeSet(context, attrs, this);
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
