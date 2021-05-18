package sg.edu.rp.c346.id19020125.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    TextView tvSongID;
    EditText etTitle, etSinger, etYear;
    RadioGroup radioGroup;
    RadioButton rb, rb1, rb2, rb3, rb4, rb5;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;
    ArrayList<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tvSongID = findViewById(R.id.textViewSongID);
        etTitle = findViewById(R.id.editTextEditTitle);
        etSinger = findViewById(R.id.editTextEditSingers);
        etYear = findViewById(R.id.editTextEditYear);
        radioGroup = findViewById(R.id.radioGroup);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnCancel = findViewById(R.id.buttonCancel);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        int selectedRadioButton = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButton == 1) {
            rb1 = findViewById(R.id.radioButton1);
            rb1.setChecked(true);
        }
        else if (selectedRadioButton == 2) {
            rb2 = findViewById(R.id.radioButton2);
            rb2.setChecked(true);
        }
        else if (selectedRadioButton == 3) {
            rb3 = findViewById(R.id.radioButton3);
            rb3.setChecked(true);
        }
        else if (selectedRadioButton == 5) {
            rb4 = findViewById(R.id.radioButton4);
            rb4.setChecked(true);
        }
        else if (selectedRadioButton == 5) {
            rb5 = findViewById(R.id.radioButton5);
            rb5.setChecked(true);
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(ThirdActivity.this);
                data.setId(Integer.parseInt(tvSongID.getText().toString()));
                data.setSinger(etSinger.getText().toString());
                data.setTitle(etTitle.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                RadioButton rbtn = findViewById(radioGroup.getCheckedRadioButtonId());
                int stars = Integer.parseInt(rbtn.getText().toString());
                data.setStar(stars);

                db.updateSong(data);
                db.close();

                Intent i = new Intent();

                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(ThirdActivity.this);
                db.deleteSong(data.getId());
                db.close();

                Intent i = new Intent();

                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();

                setResult(RESULT_OK, i);
                finish();
            }
        });

    }
}