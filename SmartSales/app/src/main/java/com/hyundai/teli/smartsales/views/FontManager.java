package com.hyundai.teli.smartsales.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class FontManager {
	static final String TAG = "FontManager";
	
	private static Typeface regularTF = null;
	private static Typeface boldTF = null;
	private static Typeface lightTF = null;
	
	public enum Font {
		Regular("fonts/ModernH-Medium.ttf"),
		Bold("fonts/ModernH-Bold.ttf"),
		Light("fonts/ModernH-Bold.ttf");
		
		public final String filename;
		private Font(String name) {
			filename = name;
		}
	}
	
	public static void setFont(TextView tv, int fontId) {
		tv.setTypeface(getTypefaceFromId(tv, fontId));
	}

	public static Typeface getTypefaceFromId(TextView tv, int fontId) {

		switch(fontId) {
		case 1:
			if(boldTF == null)
				boldTF = Typeface.createFromAsset(tv.getContext().getAssets(), Font.Bold.filename);

//			Log.d(TAG, "set Bold");
			return boldTF;
		case 2:
			if(lightTF == null)
				lightTF = Typeface.createFromAsset(tv.getContext().getAssets(), Font.Light.filename);

//			Log.d(TAG, "set Light");
			return lightTF;
		case 0:
		default:
			if(regularTF == null)
				regularTF = Typeface.createFromAsset(tv.getContext().getAssets(), Font.Regular.filename);

//			Log.d(TAG, "set Regular");
			return regularTF;
		}		
		
	}

	public static void setFontFromAttributeSet(Context context, AttributeSet attrs, TextView tv) {
//		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HTextView);
//		int fontId = a.getInt(R.styleable.HTextView_font_style, -1);
//		FontManager.setFont(tv, fontId);
	}
}
