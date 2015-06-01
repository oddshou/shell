package com.odds.androidalltest.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundImageView extends ImageView {
	
	private Path mpath = new Path();
	private RectF mRectF = new RectF();
	private static Paint mPaintClear = new Paint(Paint.ANTI_ALIAS_FLAG);
	private static Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	static{
		mPaintClear.setColor(Color.RED);
		mPaintClear.setStyle(Style.STROKE);
		mPaintClear.setStrokeWidth(8);
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(20);  
	}

	public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public RoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public RoundImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		int w = getWidth();
		int h = getHeight();
		//裁剪一个更大的区域
		mpath.addRoundRect(new RectF(0, 0, w, h), 50.0f, 50.0f, Path.Direction.CW);
		canvas.clipPath(mpath);
		super.onDraw(canvas);
		//先画白色边框
//		mpath.reset();
		mpath.addRoundRect(new RectF(0, 0, w, h), 50.0f, 50.0f, Path.Direction.CW);
		canvas.drawPath(mpath, mPaintClear);
		mpath.reset();
		mpath.addCircle(w/2, h/2, 100, Direction.CW);
		canvas.drawPath(mpath, mPaint);
	}
	
	public static Bitmap getRoundBitmap(Bitmap scaleBitmapImage) {
		int targetWidth = 1000;
		int targetHeight = 1000;
		Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, targetHeight,
				Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(targetBitmap);
		Path path = new Path();
		path.addCircle(((float) targetWidth - 1) / 2,
				((float) targetHeight - 1) / 2,
				(Math.min(((float) targetWidth), ((float) targetHeight)) / 2),
				Path.Direction.CCW);

		canvas.clipPath(path);
		Bitmap sourceBitmap = scaleBitmapImage;
		canvas.drawBitmap(sourceBitmap, new Rect(0, 0, sourceBitmap.getWidth(),
				sourceBitmap.getHeight()), new Rect(0, 0, targetWidth,
				targetHeight), null);
		return targetBitmap;
	}
	
	public static Drawable getRoundDrawable(Drawable d){
		Bitmap b=getRoundBitmap(((BitmapDrawable) d).getBitmap());
		return new BitmapDrawable(b);
	}

}























