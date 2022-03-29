package hk.edu.cccu.y1718.gp10.appproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);*/
        Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("uid", 2);
        bundle.putInt("age", 14);
        bundle.putInt("credit", 200);
        bundle.putString("name", "john");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
