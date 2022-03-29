package hk.edu.cccu.y1718.gp10.appproject;

/**
 * Created by JY on 30/11/2017.
 */

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DBTest extends Activity {
    private DbHelper DbHelper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        ImageView image = new ImageView(this);
        TextView text = new TextView(this);

        DbHelper = new DbHelper(this);

        Movie themanfromuncle = new Movie(
                BitmapFactory.decodeResource(getResources(), R.drawable.themanfromunclejpg),
                "The Man From Uncle", 1, "116", "2B", "Comedy", "Guy Ritchie", "Henry WIlliam Dalgliesh Cavill, Alicia Amaanda Vikander'", "Comedy", "7 billion", "13-8-2015", "Description", 23, 3
        );
        DbHelper.open();
        DbHelper.createMovieEntry(themanfromuncle);
        DbHelper.close();

        themanfromuncle = null;

        DbHelper.open();
        themanfromuncle = DbHelper.getMovieFromDB();
        DbHelper.close();

        image.setImageBitmap(themanfromuncle.getBitmap());
        text.setText("Name: "+themanfromuncle.getName());

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        setContentView(layout);
        addContentView(image, params);
        addContentView(text, params);
    }
}
