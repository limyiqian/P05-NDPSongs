package sg.edu.rp.c346.id19020125.p05_ndpsongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Button btnBack;
    ToggleButton btnFilter;
    ListView lvSongs;
    CustomAdapter adapter;
    ArrayList<Song> songs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnFilter = findViewById(R.id.btnFilter);
        btnBack = findViewById(R.id.btnBack);
        lvSongs = findViewById(R.id.lvSongs);

        DBHelper dbHelper = new DBHelper(this);
        songs = dbHelper.getAllSongs();

        adapter = new CustomAdapter(this, R.layout.row, songs);
        lvSongs.setAdapter(adapter);

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Song target = songs.get(i);
                Intent a = new Intent(SecondActivity.this, ThirdActivity.class);
                a.putExtra("data", target);
                startActivity(a);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean state = btnFilter.isChecked();

                if (state){
                    songs.clear();
                    songs.addAll(dbHelper.get5StarSongs());
                }else if(!state){
                    songs.clear();
                    songs.addAll(dbHelper.getAllSongs());
                }

                adapter.notifyDataSetChanged();
            }
        });

    }
}
