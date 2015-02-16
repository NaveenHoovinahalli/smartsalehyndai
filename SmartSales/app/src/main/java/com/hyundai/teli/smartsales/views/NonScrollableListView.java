package com.hyundai.teli.smartsales.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class NonScrollableListView extends ListView {
	
	public NonScrollableListView(Context context) {
		super(context);
	}
	
	public NonScrollableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NonScrollableListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// Calculate entire height by providing a very large height hint.
		 // But do not use the highest 2 bits of this integer; those are
		 // reserved for the MeasureSpec mode.
		 int expandSpec = MeasureSpec.makeMeasureSpec(
		 Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		 super.onMeasure(widthMeasureSpec, expandSpec);
		 
		 ViewGroup.LayoutParams params = getLayoutParams();
		 params.height = getMeasuredHeight();
	}

}

