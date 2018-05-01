package com.mdi.rechoir;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class PracticeActivity extends AppCompatActivity {

    private CanvasView customCanvas;

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
