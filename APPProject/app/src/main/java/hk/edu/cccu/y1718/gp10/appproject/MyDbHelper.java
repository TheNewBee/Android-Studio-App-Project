package hk.edu.cccu.y1718.gp10.appproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Raymond on 29/11/2017.
 */

public class MyDbHelper {
    private DbHelper dbHelper;

    public MyDbHelper(Context context) {
        dbHelper = new DbHelper(context);
    }

    public Cursor getProductListByKeyword(String s) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT rowid as " +
                DbHelper.COLUMN_NAME_PRODUCT_ID + "," +
                DbHelper.COLUMN_NAME_PRODUCT_NAME + "," +
                DbHelper.COLUMN_NAME_PRODUCT_POSTER + "," +
                DbHelper.COLUMN_NAME_PRODUCT_RATING + "," +
                DbHelper.COLUMN_NAME_PRODUCT_CATEGORY + "," +
                DbHelper.COLUMN_NAME_PRODUCT_PRICE +
                " FROM " + DbHelper.TABLE_NAME_PRODUCT +
                " WHERE " +  DbHelper.COLUMN_NAME_PRODUCT_NAME + "  LIKE  '%" + s + "%'"
                ;

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (!cursor.moveToFirst()) {
            cursor.close();
            cursor = null;
        }
        return cursor;
    }

}
