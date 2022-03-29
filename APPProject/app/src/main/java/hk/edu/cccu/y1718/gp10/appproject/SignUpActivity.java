package hk.edu.cccu.y1718.gp10.appproject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends Activity {
    private Cursor myCursor;

    private EditText etName, etPassword, etAge;
    private TextView tvBtnLogin, tvBtnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = (EditText) findViewById(R.id.name);
        etPassword = (EditText) findViewById(R.id.password);
        etAge = (EditText) findViewById(R.id.age);
        tvBtnLogin = (TextView) findViewById(R.id.login);
        tvBtnSignUp = (TextView) findViewById(R.id.signUp);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Lato-Light.ttf");
        etName.setTypeface(custom_font);
        etPassword.setTypeface(custom_font);
        etAge.setTypeface(custom_font);
        tvBtnLogin.setTypeface(custom_font);
        tvBtnSignUp.setTypeface(custom_font);

        tvBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        tvBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int uid = 1;
                DbHelper mDbHelper = new DbHelper(SignUpActivity.this);
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                String name = etName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String age = etAge.getText().toString().trim();

                if (name.equals("")) {
                    Toast toast = Toast.makeText(SignUpActivity.this, "Please fill in name field!", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                } else if (password.equals("")) {
                    Toast toast = Toast.makeText(SignUpActivity.this, "Please fill in password field!", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                } else if (age.equals("")) {
                    Toast toast = Toast.makeText(SignUpActivity.this, "Please fill in age field!", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                myCursor = db.query(
                        DbHelper.TABLE_NAME_USER,
                        new String [] {"MAX(" + DbHelper.COLUMN_NAME_USER_ID + ")"},
                        null,
                        null,
                        null,
                        null,
                        null);

                if (myCursor.moveToNext()) {
                    uid = myCursor.getInt(0) + 1;
                }

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(DbHelper.COLUMN_NAME_USER_ID, uid);
                values.put(DbHelper.COLUMN_NAME_USER_NAME, name);
                values.put(DbHelper.COLUMN_NAME_USER_PASSWORD, password);
                values.put(DbHelper.COLUMN_NAME_USER_AGE, Integer.parseInt(age));
                values.put(DbHelper.COLUMN_NAME_USER_CREDIT, 0);

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(
                        DbHelper.TABLE_NAME_USER,
                        null,
                        values);

                if(newRowId > 0) {
                    uid = (int) newRowId;
                    System.out.println("SignUp UID " + uid + " succeed!");

                    // CHANGE THE FOLLOWING
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(SignUpActivity.this, "SignUp failed! Please reset the app and open it again.", Toast.LENGTH_LONG);
                    toast.show();
                    System.out.println("SignUp UID " + uid + " failed!");
                    return;
                }
            }
        });
    }
}
