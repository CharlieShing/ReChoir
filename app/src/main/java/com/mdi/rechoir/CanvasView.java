package com.mdi.rechoir;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View {

    public int width;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    Context context;
    private Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;

    private Bitmap sBitmap; // Sheet bitmap

    // Line coordinates
    private float lStartX;
    private float lStartY;
    private float lEndX;
    private float lEndY;

    private float lLength; // Line length
    private Paint lPaint;   // Line paint

    // Define variables for animation
    private int fps = 60;
    long animationDuration = 10000;


    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;

        // Initialize bitmaps and line values
        sBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ave_maria_p1);

        lLength = 1000;

        lStartX = 0;
        lStartY = 0;

        lEndX = lStartX;
        lEndY = lStartY + lLength;

        lPaint = new Paint();
        lPaint.setAntiAlias(true);
        lPaint.setStrokeWidth(10f);
        lPaint.setColor(Color.RED);
        lPaint.setStyle(Paint.Style.STROKE);
        lPaint.setStrokeJoin(Paint.Join.ROUND);
        lPaint.setAlpha(75);


        // we set a new Path
        mPath = new Path();

        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);
    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        sBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ave_maria_p1);
        mCanvas = new Canvas(mBitmap);
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Dst rect defined to fill canvas
        RectF rectf = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw image on canvas
        canvas.drawBitmap(sBitmap, null, rectf, mPaint);

        // Fetch canvas dimensions
        int height = canvas.getHeight();
        int width = canvas.getWidth();

        lLength = height * 2 / 10;

        lStartX = width*2/12;
        lStartY = height/22;

        lEndX = lStartX;
        lEndY = lStartY + lLength;

        // Draw line on canvas
        canvas.drawLine(lStartX+100, lStartY+100, lEndX+100, lEndY+100, lPaint);
    }

    // when ACTION_DOWN start touch according to the x,y values
    private void startTouch(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    // when ACTION_MOVE move touch according to the x,y values
    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    public void clearCanvas() {
        mPath.reset();
        invalidate();
    }

    // when ACTION_UP stop touch
    private void upTouch() {
        mPath.lineTo(mX, mY);
    }

    //override the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }
        return true;
    }
}