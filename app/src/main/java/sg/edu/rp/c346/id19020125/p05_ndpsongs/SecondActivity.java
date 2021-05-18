package sg.edu.rp.c346.id19020125.p05_ndpsongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Button btnBack;
    ToggleButton btnFilter;
    ListView lvSongs;
    CustomAdapter adapter;
    ArrayList<Song> songs;
    Spinner yearSpinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnFilter = findViewById(R.id.btnFilter);
        btnBack = findViewById(R.id.btnBack);
        lvSongs = findViewById(R.id.lvSongs);
        yearSpinner = findViewById(R.id.yearSpinner);

        DBHelper dbHelper = new DBHelper(this);
        songs = dbHelper.getAllSongs();

        adapter = new CustomAdapter(this, R.layout.row, songs);
        lvSongs.setAdapter(adapter);

        ArrayList<String> years = new ArrayList<String>();
        years.add("All Years");
        for (int i=0;i<songs.size();i++){
            String ye = String.valueOf(songs.get(i).getYear());
            if(!years.contains(ye)){
                years.add(ye);
            }
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(spinnerAdapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = years.get(position);

                if (position != 0){
                    int y = Integer.parseInt(select);
                    songs.clear();
                    songs.addAll(dbHelper.getSongsByYear(y));
                }else{
                    songs.clear();
                    songs.addAll(dbHelper.getAllSongs());
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Song target = songs.get(i);
                Intent a = new Intent(SecondActivity.this, ThirdActivity.class);
                a.putExtra("data", target);
                startActivityForResult(a, 9);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && resultCode == 9) {
            btnFilter.performClick();
        }
    }
}
