package hk.edu.cccu.y1718.gp10.appproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private Cursor myCursor;

    private EditText etName, etPassword;
    private TextView tvBtnLogin, tvBtnSignUp, tvLogoTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etName = (EditText) findViewById(R.id.name);
        etPassword = (EditText)findViewById(R.id.password);
        tvLogoTitle = (TextView)findViewById(R.id.logoTitle);
        tvBtnLogin = (TextView)findViewById(R.id.login);
        tvBtnSignUp = (TextView)findViewById(R.id.signUp);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/Lato-Light.ttf");
        etName.setTypeface(custom_font);
        etPassword.setTypeface(custom_font);
        tvBtnLogin.setTypeface(custom_font);
        tvBtnSignUp.setTypeface(custom_font);
        tvLogoTitle.setTypeface(custom_font);

        tvBtnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        tvBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int uid = 0;
                DbHelper mDbHelper = new DbHelper(LoginActivity.this);
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                String name = etName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (name.equals("")) {
                    Toast toast = Toast.makeText(LoginActivity.this, "Please fill in name field!", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                } else if (password.equals("")) {
                    Toast toast = Toast.makeText(LoginActivity.this, "Please fill in password field!", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                // Define a projection that specifies which columns from the database
                // you will actually use after this query.
                String[] projection = {
                        DbHelper.COLUMN_NAME_USER_ID,
                        DbHelper.COLUMN_NAME_USER_NAME,
                        DbHelper.COLUMN_NAME_USER_AGE,
                        DbHelper.COLUMN_NAME_USER_CREDIT
                };

                String selection = DbHelper.COLUMN_NAME_USER_NAME + " = ? AND " +
                        DbHelper.COLUMN_NAME_USER_PASSWORD + " = ?";

                String[] selectionArgs = {
                        name,
                        password
                };

                // Gets the maximum id
                myCursor = db.query(
                        DbHelper.TABLE_NAME_USER,  // The table to query
                        projection,                               // The columns to return
                        selection,                                // The columns for the WHERE clause
                        selectionArgs,                            // The values for the WHERE clause
                        null,                                     // don't group the rows
                        null,                                     // don't filter by row groups
                        null                                 // The sort order
                );
                if(myCursor.moveToNext()) {
                    uid = myCursor.getInt(myCursor.getColumnIndex(DbHelper.COLUMN_NAME_USER_ID));
                    name = myCursor.getString(myCursor.getColumnIndex(DbHelper.COLUMN_NAME_USER_NAME));
                    int age = myCursor.getInt(myCursor.getColumnIndex(DbHelper.COLUMN_NAME_USER_AGE));
                    int credit = myCursor.getInt(myCursor.getColumnIndex(DbHelper.COLUMN_NAME_USER_CREDIT));

                    if(uid != 1 && uid != 2) {
                        Toast toast = Toast.makeText(LoginActivity.this, "Login denied! UID " + uid + " is not allow to login!", Toast.LENGTH_LONG);
                        toast.show();
                        System.out.println("Login uid: " + uid + " denied!");
                        return;
                    }

                    Toast toast = Toast.makeText(LoginActivity.this, "Login succeed! Welcome, " + name + "!", Toast.LENGTH_LONG);
                    toast.show();
                    System.out.println("Login uid: " + uid + " succeed!");

                    Intent intent = new Intent(LoginActivity.this, ProductListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("uid", uid);
                    bundle.putInt("age", age);
                    bundle.putInt("credit", credit);
                    bundle.putString("name", name);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(LoginActivity.this, "Account not found! Are you sure the name and password are typed correctly?", Toast.LENGTH_LONG);
                    toast.show();
                    System.out.println("Login failed!");
                    return;
                }
            }
        });
    }
}
