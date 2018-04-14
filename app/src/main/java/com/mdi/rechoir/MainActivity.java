package com.mdi.rechoir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private boolean practiceFirstOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
