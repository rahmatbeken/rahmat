package com.souttab.kamus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.souttab.kamus.database.CopyDatabase;
import com.souttab.kamus.database.DatabaseUtil;

import java.sql.SQLException;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Copy Database yang ada di folder assets
        // ke dalam handphone yang menginstall
        CopyDatabase copyDatabase = new CopyDatabase(getApplicationContext());
        try {
            copyDatabase.createdDatabase();
            copyDatabase.openDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseUtil databaseUtil = new DatabaseUtil(getApplicationContext());
                Kamus kamus = databaseUtil.getKamus("Gateway");
                Log.i("KAMUS", kamus.getIstilah());
                Log.i("Kamus", kamus.getPenjelasan());
            }
        });

        // test list
    }

    void getAllListKamus() {
        DatabaseUtil databaseUtil = new DatabaseUtil(getApplicationContext());
        for (Kamus kamus : databaseUtil.listKamus()) {
            Log.i("istilah", kamus.getIstilah());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
