package sg.edu.rp.c346.id19020125.p05_ndpsongs;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String singer;
    private String title;
    private int year;
    private int star;

    public Song(String singer, String title, int year, int star){
        this.singer = singer;
        this.title = title;
        this.year = year;
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
