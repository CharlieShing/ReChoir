package com.mdi.rechoir;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_login:
                openLogin();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void openLibrary(View view){
        Intent intent = new Intent(this, LibraryActivity.class);
        startActivity(intent);
    }

    public void openPractice(View view){
        Intent intent = new Intent(this, PracticeActivity.class);
        startActivity(intent);
    }

    public void openScan(View view){
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

    public void openLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
