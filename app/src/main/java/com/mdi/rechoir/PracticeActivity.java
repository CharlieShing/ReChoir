package com.mdi.rechoir;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class PracticeActivity extends AppCompatActivity {

    private CanvasView customCanvas;
    private MediaPlayer mp;
    private boolean looping;
    private int seekD;

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

        // Initialize mp
        mp = MediaPlayer.create(this, R.raw.princeigor);
        looping = false;
        seekD = 5000;

        // Initialize canvas
        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);

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
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
        customCanvas = null;
    }

    @SuppressLint("NewApi")
    public void play_toggle(View v) {
        if (!mp.isPlaying()) {
            customCanvas.play();
            mp.start();
        } else {
            customCanvas.pause();
            mp.pause();
        }

        // Toggle play button background
        Button play_button = (Button) findViewById(R.id.button_play);
        play_button.setBackgroundResource(mp.isPlaying() ? R.mipmap.pause_icon : R.mipmap.play_icon);
    }

    public void stop(View v) {
        customCanvas.stop();
        mp.pause();
        mp.seekTo(0);

        // Toggle play button background
        Button play_button = (Button) findViewById(R.id.button_play);
        play_button.setBackgroundResource(mp.isPlaying() ? R.mipmap.pause_icon : R.mipmap.play_icon);
    }

    @SuppressLint("NewApi")
    public void rewind(View v) {
        customCanvas.rewind();
        mp.pause();
        mp.seekTo(mp.getCurrentPosition() - seekD);

        // Toggle play button background
        Button play_button = (Button) findViewById(R.id.button_play);
        play_button.setBackgroundResource(mp.isPlaying() ? R.mipmap.pause_icon : R.mipmap.play_icon);
    }


    public void forward(View v) {
        customCanvas.forward();
        mp.seekTo(mp.getCurrentPosition() + seekD);
    }

    public void loop(View v) {
        customCanvas.loop_toggle();
        if (!looping) {
            mp.setLooping(true);
        } else {
            mp.setLooping(false);
        }

        // Toggle looping background
        Button play_button = (Button) findViewById(R.id.button_play);
        play_button.setBackgroundResource(mp.isPlaying() ? R.mipmap.pause_icon : R.mipmap.play_icon);
    }
}
