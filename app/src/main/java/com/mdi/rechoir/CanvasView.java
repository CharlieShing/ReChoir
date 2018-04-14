package com.mdi.rechoir;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View {

    public int width;
    public int height;
    Context context;

    private Bitmap sBitmap; // Sheet bitmap
    private Paint sPaint;    // Sheet paint

    // Line coordinates
    private float lStartX;
    private float lStartY;
    private float lEndX;
    private float lEndY;
    private float dLineX; // When starting to play this stores the current delta
    private float dLineY;

    private float lLength; // Line length
    private Paint lPaint;   // Line paint

    // Define variables for animation
    private boolean playing;
    private boolean loop;
    private int fps = 60;

    private float endOfLine; // The max X value for line
    private float startOfLine; // All but the first line start in different place
    private float lineSpacing; // The spacing between each line in sheet
    private int lines; // Number of lines in sheet
    private int currentLine;


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

        dLineX = 0;
        dLineY = 0;

        lines = 3;
        currentLine = 1;

        playing = false;
        loop = false;

        lPaint = new Paint();
        lPaint.setAntiAlias(true);
        lPaint.setStrokeWidth(10f);
        lPaint.setColor(Color.RED);
        lPaint.setStyle(Paint.Style.STROKE);
        lPaint.setStrokeJoin(Paint.Join.ROUND);
        lPaint.setAlpha(75);

        // and we set a new Paint with the desired attributes
        sPaint = new Paint();
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Set canvas dimensions
        height = canvas.getHeight();
        width = canvas.getWidth();

        // Set sheet dimensions
        startOfLine = width * 5 / 34;
        endOfLine = (width * 31) / 34;
        lineSpacing = height * 65 / 224;


        // If playing then move line
        if (playing) {
            if ((lStartX + dLineX) < endOfLine) {
                // Increase dLineX
                dLineX += 3;
            } else {
                // If the last line has been reached start over
                if (currentLine < lines) {
                    // Reset dLineX and move to next line by increasing dLineY
                    dLineX = -(lStartX - startOfLine);
                    dLineY += lineSpacing;
                    currentLine++;
                } else {
                    if (loop) {
                        dLineX = 0;
                        dLineY = 0;
                        currentLine = 1;
                    } else {
                        playing = false;
                    }
                }
            }
        }

        // Dst rect defined to fill canvas
        RectF rectf = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw image on canvas
        canvas.drawBitmap(sBitmap, null, rectf, sPaint);

        lLength = (height * 2) / 9;
        lStartX = (width * 4) / 15;
        lStartY = (height * 2) / 21;
        lEndX = lStartX;
        lEndY = lStartY + lLength;

        // Draw line on canvas
        canvas.drawLine(lStartX + dLineX, lStartY + dLineY, lEndX + dLineX, lEndY + dLineY, lPaint);
        this.postInvalidateDelayed( 1000 / fps);
    }

    public void play() {
        int forwardStep = 100;
        if (playing) {
            if (dLineX + lStartX + forwardStep < endOfLine) {
                dLineX += forwardStep;
            } else {
                dLineX = endOfLine - lStartX;
            }
        } else {
            playing = true;
        }
    }

    public void stop() {
        if (!playing) {
            dLineX = 0;
            dLineY = 0;
            currentLine = 1;
        } else {
            playing = false;
        }
    }

    public void rewind() {
        playing = false;
        int rewindStep = 100;
        if(currentLine == 1) {
            if (dLineX - rewindStep > 0) {
                dLineX -= rewindStep;
            } else {
                dLineX = 0;
            }
        } else {
            if ((lStartX + dLineX - rewindStep) - startOfLine > 0) {
                dLineX -= rewindStep;
            } else {
                currentLine--;
                dLineX = endOfLine - lStartX;
                dLineY -= lineSpacing;
            }
        }
    }

    public void loop() {
        if (loop) {
            loop = false;
        } else {
            loop = true;
        }
    }
}