package sg.edu.rp.c346.id19020125.p05_ndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Song> {

    Context context;
    ArrayList<Song> songs;
    int resource;
    RadioButton rb1, rb2, rb3, rb4, rb5;

    public CustomAdapter(Context context, int resource, ArrayList<Song> songs) {
        super(context, resource, songs);
        this.context = context;
        this.resource = resource;
        this.songs = songs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(resource, parent, false);

        TextView tvYear = (TextView) rowView.findViewById(R.id.tvYear);
        TextView tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
        TextView tvSinger = (TextView) rowView.findViewById(R.id.tvSinger);
        RatingBar stars = (RatingBar) rowView.findViewById(R.id.ratingBar);

        rb1 = rowView.findViewById(R.id.radioButton1);
        rb2 = rowView.findViewById(R.id.radioButton2);
        rb3 = rowView.findViewById(R.id.radioButton3);
        rb4 = rowView.findViewById(R.id.radioButton4);
        rb5 = rowView.findViewById(R.id.radioButton5);

        Song curr = songs.get(position);
        int id = curr.getId();
        String title = curr.getTitle();
        String singer = curr.getSinger();
        int year = curr.getYear();
        Integer star = curr.getStar();

        tvYear.setText(String.valueOf(curr.getYear()));
        tvTitle.setText(curr.getTitle());
        tvSinger.setText(curr.getSinger());
        stars.setRating(Float.parseFloat(String.valueOf(curr.getStar())));

        return rowView;
    }
}

