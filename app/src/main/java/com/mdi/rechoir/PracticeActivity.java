package com.mdi.rechoir;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class PracticeActivity extends AppCompatActivity {

    /*
    // Screen size
    private int screenWidth;
    private int screenHeight;

    // Image
    private ImageView image;

    // Position
    private float imageX;
    private float imageY;

    // Initialize class
    private Handler handler;
    private Timer timer = new Timer();

    // Image bitmap
    private Bitmap bm;

    // Don't really know what these are for
    private float x;
    private float y;*/

    private CanvasView customCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        // Get the Intent that started this activity and extract the song name
        Intent intent = getIntent();
        String song_name = intent.getStringExtra("SONG_NAME");

        if (song_name == null) {
            song_name = "Sound Of Silence";
        }

        // Initialize canvas
        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);

        Context context = getApplicationContext();
        CharSequence text = "Slide up bottom for more options!";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();
    }

    public void clearCanvas(View v) {
        customCanvas.clearCanvas();
    }

    public void play(View v) {
        customCanvas.play();
    }

    public void stop(View v) {
        customCanvas.stop();
    }

    public void rewind(View v) {
        customCanvas.rewind();
    }
}
