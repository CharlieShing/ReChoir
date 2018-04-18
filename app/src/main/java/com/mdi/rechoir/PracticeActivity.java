package com.mdi.rechoir;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class PracticeActivity extends AppCompatActivity {

    private CanvasView customCanvas;
    private boolean looping;
    AnimationDrawable loopAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.back_icon);

        looping = false;

        // Get the Intent that started this activity and extract the song name
        Intent intent = getIntent();
        //String song_name = intent.getStringExtra("SONG_NAME");
        String first_open = intent.getStringExtra("FIRST_OPEN");

        // Initialize canvas
        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);

        Context context = getApplicationContext();
        CharSequence text = "Slide up bottom for more options!";
        int duration = Toast.LENGTH_LONG;

        /* Diabling toast due to showcase being enough
        //Show toast if first time to start practice
        if (first_open.equals("yes")){
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.BOTTOM, 0, 200);
            toast.show();
        }*/

        /*
        //Start loop animation
        // Load the ImageView that will host the animation and
        // set its background to our AnimationDrawable XML resource.
        ImageView img = (ImageView) findViewById(R.id.button_loop);
        img.setBackgroundResource(R.drawable.looper_animation);
        loopAnimation = (AnimationDrawable) img.getBackground();

        /* Showcase for UX instructions */

        View slider = findViewById(R.id.controls);

        String ok = "Got it!";

        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, "Practice");

        sequence.setConfig(config);

        sequence.addSequenceItem(slider, "Slide up to reveal more options", ok);

        sequence.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //loopAnimation.start();
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

    public void loop(View v) {
        customCanvas.loop();
    }
}
