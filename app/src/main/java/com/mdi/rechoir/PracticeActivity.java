package com.mdi.rechoir;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class PracticeActivity extends AppCompatActivity {

    private CanvasView customCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        // Get the Intent that started this activity and extract the song name
        Intent intent = getIntent();
        //String song_name = intent.getStringExtra("SONG_NAME");
        String first_open = intent.getStringExtra("FIRST_OPEN");

        // Initialize canvas
        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);

        Context context = getApplicationContext();
        CharSequence text = "Slide up bottom for more options!";
        int duration = Toast.LENGTH_LONG;
        
        //Show toast if first time to start practice
        if (first_open.equals("yes")){
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.BOTTOM, 0, 200);
            toast.show();
        }

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
