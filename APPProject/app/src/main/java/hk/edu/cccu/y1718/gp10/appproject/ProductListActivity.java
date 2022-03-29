package hk.edu.cccu.y1718.gp10.appproject;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private int uid = 0;
    private int age = 0;
    private int credit = 0;
    private String name = "";
    private Cursor cursor = null;
    private DbHelper mDbHelper = null;
    private SQLiteDatabase db = null;
    private CustomAdapter customAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = this.getIntent().getExtras();
        uid = bundle.getInt("uid");
        age = bundle.getInt("age");
        credit = bundle.getInt("credit");
        name = bundle.getString("name");

        if(uid <= 0)
            this.finish();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        mDbHelper = new DbHelper(ProductListActivity.this);
        db = mDbHelper.getWritableDatabase();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView =  navigationView.getHeaderView(0);
        ImageView avatarView = (ImageView) headerView.findViewById(R.id.nav_header_avatar);
        TextView nameView = (TextView) headerView.findViewById(R.id.nav_header_name);
        TextView creditView = (TextView) headerView.findViewById(R.id.nav_header_credit);
        avatarView.setOnClickListener(this);
        nameView.setOnClickListener(this);
        creditView.setOnClickListener(this);

        nameView.setText(name.substring(0, 1).toUpperCase() + name.substring(1));
        creditView.setText(ProductListActivity.this.getString(R.string.navigation_drawer_header_credit) + ": " + credit);

        cursor = getStudentList();
        customAdapter = new CustomAdapter(ProductListActivity.this,  cursor, 0);
        ListView listView = (ListView) findViewById(R.id.lstProduct);
        listView.setAdapter(customAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_list, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String s) {
                cursor = getProductListByKeyword(s);
                if (cursor == null) {
                    Toast.makeText(ProductListActivity.this,"No records found!",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ProductListActivity.this, cursor.getCount() + " records found!",Toast.LENGTH_LONG).show();
                }
                customAdapter.swapCursor(cursor);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                cursor = getProductListByKeyword(s);
                if (cursor != null){
                    customAdapter.swapCursor(cursor);
                }
                return false;
            }

            public Cursor getProductListByKeyword(String s) {
                String selectQuery =  "SELECT rowid as " + "_id," +
                        DbHelper.COLUMN_NAME_PRODUCT_ID + "," +
                        DbHelper.COLUMN_NAME_PRODUCT_NAME + "," +
                        DbHelper.COLUMN_NAME_PRODUCT_POSTER + "," +
                        DbHelper.COLUMN_NAME_PRODUCT_RATING + "," +
                        DbHelper.COLUMN_NAME_PRODUCT_CATEGORY + "," +
                        DbHelper.COLUMN_NAME_PRODUCT_PRICE +
                        " FROM " + DbHelper.TABLE_NAME_PRODUCT +
                        " WHERE (" +  DbHelper.COLUMN_NAME_PRODUCT_NAME + "  LIKE  '%" + s + "%')";

                Cursor cursor = db.rawQuery(selectQuery, null);
                // looping through all rows and adding to list

                if (cursor == null) {
                    return null;
                } else if (!cursor.moveToFirst()) {
                    cursor.close();
                    return null;
                }
                return cursor;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_movies) {
            // Handle the camera action
        } else if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_invoices) {

        } else if (id == R.id.nav_refill) {

        } else if (id == R.id.nav_donate) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_header_avatar: case R.id.nav_header_name: case R.id.nav_header_credit:
                break;
            default:
                break;
        }
    }

    public Cursor  getStudentList() {
        //Open connection to read only
        String selectQuery =  "SELECT  rowid as " + "_id," +
                DbHelper.COLUMN_NAME_PRODUCT_ID + "," +
                DbHelper.COLUMN_NAME_PRODUCT_NAME + "," +
                DbHelper.COLUMN_NAME_PRODUCT_POSTER + "," +
                DbHelper.COLUMN_NAME_PRODUCT_RATING + "," +
                DbHelper.COLUMN_NAME_PRODUCT_CATEGORY + "," +
                DbHelper.COLUMN_NAME_PRODUCT_PRICE +
                " FROM " + DbHelper.TABLE_NAME_PRODUCT;

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }
}
