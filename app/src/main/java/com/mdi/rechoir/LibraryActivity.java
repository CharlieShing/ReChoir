package com.mdi.rechoir;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Arrays;

public class LibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        Intent intent = getIntent();

        // See if practice has been opened yet
        final String practice_first_open = intent.getStringExtra("PRACTICE_FIRST_OPEN");

        // Array which represents available songs in list
        final String[] songs = {"Ave Maria"};
        Arrays.sort(songs);

        final ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songs);
        listView.setAdapter(adapter);

        // Save context for use in onClick
        final Context cnt_this = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = listView.getItemAtPosition(position);
                Intent intent = new Intent(cnt_this, PracticeActivity.class);
                intent.putExtra("SONG_NAME", songs[position]);
                if (practice_first_open.equals("yes")) {
                    intent.putExtra("FIRST_OPEN", "yes");
                } else {
                    intent.putExtra("FIRST_OPEN", "no");
                }
                startActivity(intent);
            }
        });
    }
}
