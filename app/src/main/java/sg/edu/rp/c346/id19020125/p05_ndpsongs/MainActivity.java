package sg.edu.rp.c346.id19020125.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etSongTitle, etSinger, etYear;
    RadioGroup rg;
    Button btnInsert, btnShowList;

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

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputSongTitle = etSongTitle.getText().toString();
                String inputSinger = etSinger.getText().toString();
                String inputYear = etYear.getText().toString();
                int SelectedId = rg.getCheckedRadioButtonId();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(i, 10);
            }
        });
    }
}