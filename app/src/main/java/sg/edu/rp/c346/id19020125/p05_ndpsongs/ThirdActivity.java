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
    RadioButton rbSelected;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

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

        tvSongID.setText(String.valueOf(data.getId()));
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSinger());
        etYear.setText(String.valueOf(data.getYear()));
        int stars = data.getStar();

        ((RadioButton) radioGroup.getChildAt(stars-1)).setChecked(true);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(ThirdActivity.this);
                data.setId(Integer.parseInt(tvSongID.getText().toString()));
                data.setSinger(etSinger.getText().toString());
                data.setTitle(etTitle.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                int selectedRadioButton = radioGroup.getCheckedRadioButtonId();
                rbSelected = findViewById(selectedRadioButton);
                int stars = Integer.parseInt(rbSelected.getText().toString());
                data.setStar(stars);

                db.updateSong(data);
                db.close();

                Intent i = new Intent();

                setResult(RESULT_OK);
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

                setResult(RESULT_OK);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();

                setResult(RESULT_OK);
                finish();
            }
        });

    }
}