package com.mdi.rechoir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PracticeActivity extends AppCompatActivity {

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
    }
}
