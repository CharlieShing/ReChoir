package com.mdi.rechoir;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;

public class PracticeActivity extends AppCompatActivity {

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

        String output = "Now playing: " + song_name;

        TextView textView = findViewById(R.id.textView);
        textView.setText(output);

        /*
        The following handles drawing and moving of image with the help of an ImageView
         */

        // Assign image
        image = (ImageView) findViewById(R.id.imageView);

        // Get Screen Size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        // Set image to default position
        image.setX(-40.0f);
        

        /*
        The following handles alternative solution; to use a canvas and draw image on that.
        This might enable easier and more accurate handling of image manipulation such as zooming.
         */

        /*

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ave_maria_p1);

        */
    }
}
