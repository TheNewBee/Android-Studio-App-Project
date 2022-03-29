package hk.edu.cccu.y1718.gp10.appproject;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Blob;

/**
 * Created by Raymond on 28/11/2017.
 */

public class CustomAdapter extends CursorAdapter {
    ImageView imageView;
    TextView txtId,txtName,txtEmail;
    private LayoutInflater mInflater;

    public CustomAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.txtId = (TextView) view.findViewById(R.id.txtId);
        holder.txtName = (TextView) view.findViewById(R.id.txtName);
        holder.txtEmail = (TextView) view.findViewById(R.id.txtEmail);
        holder.imageView = (ImageView) view.findViewById(R.id.image);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //If you want to have zebra lines color effect uncomment below code
        /*if(cursor.getPosition() % 2 == 1) {
            view.setBackgroundResource(R.drawable.item_list_backgroundcolor);
        } else {
            view.setBackgroundResource(R.drawable.item_list_backgroundcolor2);
        }*/

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.txtId.setText(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_NAME_PRODUCT_ID)));
        holder.txtName.setText(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_NAME_PRODUCT_NAME)));
        holder.txtEmail.setText(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_NAME_PRODUCT_PRICE)));

        byte[] blob = cursor.getBlob(cursor.getColumnIndex(DbHelper.COLUMN_NAME_PRODUCT_POSTER));
        Bitmap pos = BitmapFactory.decodeByteArray(blob, 0, blob.length);
        holder.imageView.setImageBitmap(pos);
        System.out.println(blob);
        cursor.close();
    }

    static class ViewHolder {
        TextView txtId;
        TextView txtName;
        TextView txtEmail;
        ImageView imageView;
    }
}