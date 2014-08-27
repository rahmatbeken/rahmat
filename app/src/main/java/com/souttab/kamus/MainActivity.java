package com.souttab.kamus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.souttab.kamus.database.CopyDatabase;
import com.souttab.kamus.database.DatabaseUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private Button buttonCari;
    private AutoCompleteTextView autoCompleteTextView;
    private TextView textViewIstilah, textViewPengertian;

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

        final DatabaseUtil databaseUtil = new DatabaseUtil(getApplicationContext());

        // reference variable
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        textViewIstilah = (TextView) findViewById(R.id.textViewIstilah);
        textViewPengertian = (TextView) findViewById(R.id.textViewPengertian);
        buttonCari = (Button) findViewById(R.id.buttonSearch);


        // untuk set autocomplete
        // buat arraylist
        ArrayList<String> dataList = new ArrayList<String>();
        // masukan data  yang ada di database ke arraylist
        for (Kamus kamus : databaseUtil.listKamus()) {
            dataList.add(kamus.getIstilah());
        }

        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        // set ke adapternya
        autoCompleteTextView.setAdapter(stringArrayAdapter);

        // button click cari
        buttonCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // panggil method get kamus
                Kamus kamus = databaseUtil.getKamus(autoCompleteTextView.getText().toString());
                // check jika tidak null maka tampilkan hasilnya
                if (kamus != null) {
                    // tampilkan istilah yang didapatkan
                    textViewIstilah.setText(kamus.getIstilah());
                    // tampilkan pengertian yang didapatkan
                    textViewPengertian.setText(kamus.getPenjelasan());
                }
                else {
                    // jika tidak ada data maka
                    // kosongkan tampilan
                    Toast.makeText(getApplicationContext(), "Tidak ditemukan kata yang dimaksud", Toast.LENGTH_SHORT).show();
                    // kosongkan text istilah
                    textViewIstilah.setText("");
                    // kosongkan text pengertian
                    textViewPengertian.setText("");

                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.about) {
            // panggil class about
            Intent layoutPanggil = new Intent(this, AboutAcvitity.class);
            // panggil sekarang
            startActivity(layoutPanggil);
        }
        return super.onOptionsItemSelected(item);
    }
}
