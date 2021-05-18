package sg.edu.rp.c346.id19020125.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etSongTitle, etSinger, etYear;
    RadioGroup rg;
    RadioButton rb;
    Button btnInsert, btnShowList;
    ArrayList<Song> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSongTitle = findViewById(R.id.etSongTitle);
        etSinger = findViewById(R.id.etSingerName);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.rg);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);

        al = new ArrayList<Song>();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputSongTitle = etSongTitle.getText().toString();
                String inputSinger = etSinger.getText().toString();
                String inputYear = etYear.getText().toString();
                Integer inputYearInt = Integer.parseInt(inputYear);
                int selectedId = rg.getCheckedRadioButtonId();
                rb = findViewById(selectedId);
                String inputStar = rb.getText().toString();
                Integer inputStarInt = Integer.parseInt(inputStar);
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(inputSongTitle,inputSinger,inputYearInt,inputStarInt);
                dbh.close();
                if(inserted_id != -1) {
                    Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }
}