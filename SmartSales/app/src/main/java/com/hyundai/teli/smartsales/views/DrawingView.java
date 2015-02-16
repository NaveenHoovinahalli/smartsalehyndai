package com.hyundai.teli.smartsales.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class DrawingView extends View {

	private Canvas mCanvas;
	private Path mPath = new Path();
	private Paint mBitmapPaint;
	private Bitmap mBitmap;
	private Paint mPaint = new Paint();
	private String savePath;
	private OnDrawSignatureListener listener;
	
	public interface OnDrawSignatureListener {
		public void onDrawSignature();
	}
	
	public void setOnDrawSignatureListener(OnDrawSignatureListener listener){
		this.listener = listener;
	}

	public DrawingView(Context context) {
		super(context);
		initView();
	}

	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public DrawingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	private void initView() {

		mPaint.setXfermode(null);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.BLACK);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(7);

		mBitmapPaint = new Paint(Paint.DITHER_FLAG);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
		canvas.drawPath(mPath, mPaint);
	}

	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	private void touch_start(float x, float y) {
		mPath.reset();
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
	}

	private void touch_move(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;
		}
	}

	private void touch_up() {
		mPath.lineTo(mX, mY);
		// commit the path to our offscreen
		mCanvas.drawPath(mPath, mPaint);
		// kill this so we don't double draw
		mPath.reset();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touch_start(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			touch_move(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			touch_up();
			invalidate();
			break;
		}
		
		if(listener != null){
			listener.onDrawSignature();
		}
		
		return true;
	}

	public void saveDrawingImage(String path) {
		savePath = path;
		
		File file = new File(path);
		
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}

		FileOutputStream filestream = null;
		try {
			filestream = new FileOutputStream(file);
			mBitmap.compress(CompressFormat.PNG, 100, filestream);
			filestream.flush();
			filestream.close();
		} catch (IOException e) {
			savePath = "";
		}

	}
	
	public String getSavePath(){
		return savePath == null ? "" : savePath;
	}
	
	public Bitmap getDrawingView(){
		return mBitmap;
	}
}
