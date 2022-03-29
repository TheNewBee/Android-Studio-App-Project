package hk.edu.cccu.y1718.gp10.appproject;

/**
 * Created by JY on 30/11/2017.
 */

import android.graphics.Bitmap;

public class Movie {
    private String name;
    private int pid;
    private Bitmap poster;
    private String length;
    private String rating;
    private String category;
    private String director;
    private String cast;
    private String genre;
    private String box_office;
    private String onscreen_date;
    private String description;
    private int price;
    private int credit;

    public Movie(Bitmap post, String n, int id, String leng, String rate, String cate, String dir, String cas, String genr, String box, String on_screen, String descript, int pric, int credi) {
        name = n;
        pid = id;
        poster = post;
        length = leng;
        rating = rate;
        category = cate;
        director = dir;
        cast = cas;
        genre = genr;
        box_office = box;
        onscreen_date = on_screen;
        description = descript;
        price = pric;
        credit = credi;
    }

    public Bitmap getBitmap() { return poster; }
    public String getName() { return name; }
    public int getPid(){return pid;}
    public String getLength(){return length;}
    public String getRating(){return rating;}
    public String getCategory(){return category;}
    public String getDirector(){return director;}
    public String getCast(){return cast;}
    public String getGenre(){return genre;}
    public String getBox_office(){return box_office;}
    public String getOnscreen_date(){return onscreen_date;}
    public String getDescription(){return description;}
    public int getPrice(){return price;}
    public int getCredit(){return credit;}
}
