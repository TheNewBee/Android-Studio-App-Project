package hk.edu.cccu.y1718.gp10.appproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.SQLException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import android.content.ContentValues;
/**
 * Created by Raymond on 26/11/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME_USER = "user";
    public static final String COLUMN_NAME_USER_ID = "uid";
    public static final String COLUMN_NAME_USER_NAME = "name";
    public static final String COLUMN_NAME_USER_PASSWORD = "password";
    public static final String COLUMN_NAME_USER_AGE = "age";
    public static final String COLUMN_NAME_USER_CREDIT = "credit";

    public static final String TABLE_NAME_PRODUCT = "product";
    public static final String COLUMN_NAME_PRODUCT_ID = "pid";
    public static final String COLUMN_NAME_PRODUCT_NAME = "name";
    public static final String COLUMN_NAME_PRODUCT_POSTER = "poster";
    public static final String COLUMN_NAME_PRODUCT_LENGTH = "length";
    public static final String COLUMN_NAME_PRODUCT_RATING = "rating";
    public static final String COLUMN_NAME_PRODUCT_CATEGORY = "category";
    public static final String COLUMN_NAME_PRODUCT_DIRECTOR = "director";
    public static final String COLUMN_NAME_PRODUCT_CAST = "cast";
    public static final String COLUMN_NAME_PRODUCT_GENRE = "genre";
    public static final String COLUMN_NAME_PRODUCT_BOX_OFFICE = "box_office";
    public static final String COLUMN_NAME_PRODUCT_ONSCREEN_DATE = "onscreen_date";
    public static final String COLUMN_NAME_PRODUCT_DESCRIPTION = "description";
    public static final String COLUMN_NAME_PRODUCT_PRICE = "price";
    public static final String COLUMN_NAME_PRODUCT_CREDIT = "credit";

    public static final String TABLE_NAME_CART = "cart";
    public static final String COLUMN_NAME_CART_USER_ID = "uid";
    public static final String COLUMN_NAME_CART_PRODUCT_ID = "pid";
    public static final String COLUMN_NAME_CART_QUANTITY = "quantity";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UnlimitedDVD.db";

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME_PRODUCT);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_PRODUCT);
            onCreate(db);
        }
    }

    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;
    private final Context mCtx;

    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_USER =
            "CREATE TABLE " + TABLE_NAME_USER + " (" +
                    COLUMN_NAME_USER_ID + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_NAME_USER_NAME + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_USER_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_USER_AGE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_NAME_USER_CREDIT + INTEGER_TYPE + COMMA_SEP +
                    " PRIMARY KEY (" +
                            COLUMN_NAME_USER_ID +
                    ")" +
            " );";



    private static final String SQL_CREATE_PRODUCT =
            "CREATE TABLE " + TABLE_NAME_PRODUCT + " (" +
                    COLUMN_NAME_PRODUCT_ID + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_NAME_PRODUCT_NAME + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_PRODUCT_POSTER + " blob not null, " + COMMA_SEP +
                    COLUMN_NAME_PRODUCT_LENGTH + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_PRODUCT_RATING + REAL_TYPE + COMMA_SEP +
                    COLUMN_NAME_PRODUCT_CATEGORY + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_PRODUCT_DIRECTOR + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_PRODUCT_CAST + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_PRODUCT_GENRE + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_PRODUCT_BOX_OFFICE + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_PRODUCT_ONSCREEN_DATE + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_PRODUCT_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_PRODUCT_PRICE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_NAME_PRODUCT_CREDIT + INTEGER_TYPE + COMMA_SEP +
                    " PRIMARY KEY (" +
                            COLUMN_NAME_PRODUCT_ID +
                    ")" +
            " );";

    private static final String SQL_CREATE_CART =
            "CREATE TABLE " + TABLE_NAME_CART + " (" +
                    COLUMN_NAME_CART_USER_ID + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_NAME_CART_PRODUCT_ID + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_NAME_CART_QUANTITY + INTEGER_TYPE + COMMA_SEP +
                    " PRIMARY KEY (" +
                            COLUMN_NAME_CART_USER_ID + COMMA_SEP +
                            COLUMN_NAME_CART_PRODUCT_ID +
                    ")" +
            " );";



    private static final String SQL_INSERT_USER =
            "INSERT INTO " + TABLE_NAME_USER + " VALUES (" +
                    "1" + COMMA_SEP +
                    "'mary'" + COMMA_SEP +
                    "'mary123'" + COMMA_SEP +
                    "28" + COMMA_SEP +
                    "50000" +
            " ), (" +
                    "2" + COMMA_SEP +
                    "'john'" + COMMA_SEP +
                    "'john123'" + COMMA_SEP +
                    "14" + COMMA_SEP +
                    "200" +
            " );";


    private static final String SQL_INSERT_PRODUCT =
            "INSERT INTO " + TABLE_NAME_PRODUCT + " VALUES (" +
                    "1" + COMMA_SEP +
                    "'The Man from U.N.C.L.E'" + COMMA_SEP +
                    "C:\\Users\\JY\\OneDrive\\REVIEW\\AST20207\\Project\\APPProject\\app\\src\\main\\res\\drawable\\themanfromunclejpg.jpg" + COMMA_SEP +
                    "'116'" + COMMA_SEP +
                    "'7.5'" + COMMA_SEP +
                    "'2B'" + COMMA_SEP +
                    "'Guy Ritchie'" + COMMA_SEP +
                    "'Henry WIlliam Dalgliesh Cavill, Alicia Amaanda Vikander'" + COMMA_SEP +
                    "'Comedy'" + COMMA_SEP +
                    "''" + COMMA_SEP + // box office
                    "'13-8-2015'" + COMMA_SEP +
                    "''" + COMMA_SEP +
                    "23" + COMMA_SEP +
                    "3" +
            " ), (" +
                    "2" + COMMA_SEP +
                    "'Inside Out'" + COMMA_SEP +
                    "''" + COMMA_SEP +
                    "'94'" + COMMA_SEP +
                    "'8.3'" + COMMA_SEP +
                    "'2'" + COMMA_SEP +
                    "'Peter Docter, Ronnie Del Carmen'" + COMMA_SEP +
                    "'Richard John Kind, Amy Meredith Poehier'" + COMMA_SEP +
                    "'Cartoon'" + COMMA_SEP +
                    "''" + COMMA_SEP + // box office
                    "'17-6-2015'" + COMMA_SEP +
                    "''" + COMMA_SEP +
                    "37" + COMMA_SEP +
                    "3" +
            " ), (" +
                    "3" + COMMA_SEP +
                    "'Ant-Man'" + COMMA_SEP +
                    "''" + COMMA_SEP +
                    "'115'" + COMMA_SEP +
                    "'7.4'" + COMMA_SEP +
                    "'2B'" + COMMA_SEP +
                    "'Peyton Reed'" + COMMA_SEP +
                    "'Paul Stephen Rudd, Nicole Evangeline Lily'" + COMMA_SEP +
                    "'Science Fiction'" + COMMA_SEP +
                    "''" + COMMA_SEP + // box office
                    "'16-7-2015'" + COMMA_SEP +
                    "''" + COMMA_SEP +
                    "35" + COMMA_SEP +
                    "3" +
            " ), (" +
                    "4" + COMMA_SEP +
                    "'Me Before You'" + COMMA_SEP +
                    "''" + COMMA_SEP +
                    "'93'" + COMMA_SEP +
                    "'7.4'" + COMMA_SEP +
                    "'2B'" + COMMA_SEP +
                    "'Thea Sharrock'" + COMMA_SEP +
                    "'Samuel George Clafilin, Emilia Clarke'" + COMMA_SEP +
                    "'Cartoon'" + COMMA_SEP +
                    "''" + COMMA_SEP + // box office
                    "'14-7-2016'" + COMMA_SEP +
                    "''" + COMMA_SEP +
                    "29" + COMMA_SEP +
                    "3" +
            " ), (" +
                    "5" + COMMA_SEP +
                    "'Meet The Robinsons'" + COMMA_SEP +
                    "''" + COMMA_SEP +
                    "'102'" + COMMA_SEP +
                    "'6.9'" + COMMA_SEP +
                    "'1'" + COMMA_SEP +
                    "'stephen j anderson'" + COMMA_SEP +
                    "'Daniel Handsens, Angela Bassett'" + COMMA_SEP +
                    "'Cartoon'" + COMMA_SEP +
                    "''" + COMMA_SEP + // box office
                    "'23-3-2007'" + COMMA_SEP +
                    "''" + COMMA_SEP +
                    "15" + COMMA_SEP +
                    "3" +
            " );";

    private static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + TABLE_NAME_USER + ";";

    private static final String SQL_DELETE_PRODUCT =
            "DROP TABLE IF EXISTS " + TABLE_NAME_PRODUCT + ";";

    private static final String SQL_DELETE_CART =
            "DROP TABLE IF EXISTS " + TABLE_NAME_CART + ";";


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_USER);
        db.execSQL(SQL_DELETE_PRODUCT);
        db.execSQL(SQL_DELETE_CART);
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_PRODUCT);
        db.execSQL(SQL_CREATE_CART);
        db.execSQL(SQL_INSERT_USER);
        db.execSQL(SQL_INSERT_PRODUCT);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_USER);
        db.execSQL(SQL_DELETE_PRODUCT);
        db.execSQL(SQL_DELETE_CART);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void Reset() { mDbHelper.onUpgrade(this.mDb, 1, 1); }

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mCtx = context;
        mDbHelper = new DatabaseHelper(mCtx);
    }

    public DbHelper open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() { mDbHelper.close(); }

    public void createMovieEntry(Movie movie) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        movie.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, out);
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME_PRODUCT_POSTER, out.toByteArray());
        cv.put(COLUMN_NAME_PRODUCT_NAME, movie.getName());
        cv.put(COLUMN_NAME_PRODUCT_BOX_OFFICE, movie.getBox_office());
        cv.put(COLUMN_NAME_PRODUCT_CAST, movie.getCast());
        cv.put(COLUMN_NAME_PRODUCT_CATEGORY, movie.getCategory());
        cv.put(COLUMN_NAME_PRODUCT_CREDIT, movie.getCredit());
        cv.put(COLUMN_NAME_PRODUCT_ID, movie.getPid());
        cv.put(COLUMN_NAME_PRODUCT_DESCRIPTION, movie.getDescription());
        cv.put(COLUMN_NAME_PRODUCT_DIRECTOR, movie.getDirector());
        cv.put(COLUMN_NAME_PRODUCT_GENRE, movie.getGenre());
        cv.put(COLUMN_NAME_PRODUCT_LENGTH, movie.getLength());
        cv.put(COLUMN_NAME_PRODUCT_ONSCREEN_DATE, movie.getOnscreen_date());
        cv.put(COLUMN_NAME_PRODUCT_PRICE, movie.getPrice());
        cv.put(COLUMN_NAME_PRODUCT_RATING, movie.getRating());
        mDb.insert(TABLE_NAME_PRODUCT, null, cv);
    }

    public Movie getMovieFromDB() throws SQLException {
        Cursor cur = mDb.query(true,
                TABLE_NAME_PRODUCT,
                new String[] {COLUMN_NAME_PRODUCT_POSTER, COLUMN_NAME_PRODUCT_NAME, COLUMN_NAME_PRODUCT_BOX_OFFICE, COLUMN_NAME_PRODUCT_CAST, COLUMN_NAME_PRODUCT_CATEGORY, COLUMN_NAME_PRODUCT_CREDIT, COLUMN_NAME_PRODUCT_DESCRIPTION, COLUMN_NAME_PRODUCT_DIRECTOR, COLUMN_NAME_PRODUCT_GENRE, COLUMN_NAME_PRODUCT_ID, COLUMN_NAME_PRODUCT_LENGTH, COLUMN_NAME_PRODUCT_ONSCREEN_DATE, COLUMN_NAME_PRODUCT_PRICE, COLUMN_NAME_PRODUCT_RATING},
                null, null,null, null, null, null);
        if(cur.moveToFirst()) {
            byte[] blob = cur.getBlob(cur.getColumnIndex(COLUMN_NAME_PRODUCT_POSTER));
            Bitmap pos = BitmapFactory.decodeByteArray(blob, 0, blob.length);
            String name = cur.getString(cur.getColumnIndex(COLUMN_NAME_PRODUCT_NAME));
            String box = cur.getString(cur.getColumnIndex(COLUMN_NAME_PRODUCT_BOX_OFFICE));
            String cast = cur.getString(cur.getColumnIndex(COLUMN_NAME_PRODUCT_CAST));
            String cate = cur.getString(cur.getColumnIndex(COLUMN_NAME_PRODUCT_CATEGORY));
            int credit = cur.getInt(cur.getColumnIndex(COLUMN_NAME_PRODUCT_CREDIT));
            String descript = cur.getString(cur.getColumnIndex(COLUMN_NAME_PRODUCT_DESCRIPTION));
            String dir = cur.getString(cur.getColumnIndex(COLUMN_NAME_PRODUCT_DIRECTOR));
            String genre = cur.getString((cur.getColumnIndex(COLUMN_NAME_PRODUCT_GENRE)));
            int id = cur.getInt(cur.getColumnIndex(COLUMN_NAME_CART_PRODUCT_ID));
            String leng = cur.getString((cur.getColumnIndex(COLUMN_NAME_PRODUCT_LENGTH)));
            String onScreen = cur.getString((cur.getColumnIndex(COLUMN_NAME_PRODUCT_ONSCREEN_DATE)));
            int price = cur.getInt(cur.getColumnIndex(COLUMN_NAME_PRODUCT_PRICE));
            String rating = cur.getString(cur.getColumnIndex(COLUMN_NAME_PRODUCT_RATING));
            cur.close();
            return new Movie(pos,name,id,leng,rating,cate,dir,cast,genre,box,onScreen,descript,price,credit);
        }
        cur.close();
        return null;
    }
}
