package com.mdi.rechoir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class MainActivity extends AppCompatActivity {
    private boolean practiceFirstOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /* Showcase for UX instructions */

        View lib_button = findViewById(R.id.library_button_container);
        View practice_button = findViewById(R.id.practice_button_container);
        View scan_button = findViewById(R.id.scan_button_container);

        String ok = "Got it!";

        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, "Main");

        sequence.setConfig(config);

        sequence.addSequenceItem(lib_button, "Library contains available songs to practice", ok);
        sequence.addSequenceItem(practice_button, "Here you go when you want to continue practicing song", ok);
        sequence.addSequenceItem(scan_button, "Scan notes lets you use the camera to scan a sheet of notes", ok);

        sequence.start();
    }

    public void openLibrary(View view){
        Intent intent = new Intent(this, LibraryActivity.class);
        if (practiceFirstOpen) {
            intent.putExtra("PRACTICE_FIRST_OPEN", "yes");
        } else {
            intent.putExtra("PRACTICE_FIRST_OPEN", "no");
        }
        startActivity(intent);
    }

    public void openPractice(View view){
        Intent intent = new Intent(this, PracticeActivity.class);
        if (practiceFirstOpen) {
            intent.putExtra("FIRST_OPEN", "yes");
            practiceFirstOpen = false;
        } else {
            intent.putExtra("FIRST_OPEN", "no");
        }
        startActivity(intent);
    }

    public void openScan(View view){
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }
}
