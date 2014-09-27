package com.souttab.kamus;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Menu extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        TextView textView = (TextView) findViewById(R.id.textView);
        Button buttonCari = (Button) findViewById(R.id.buttonCari);
        Button buttonAbout = (Button) findViewById(R.id.buttonAbout);

        Typeface font = Typeface.createFromAsset(getAssets(), "Days.otf");
        textView.setTypeface(font);

        buttonAbout.setOnClickListener(this);
        buttonCari.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.buttonCari :
                intent = new Intent(Menu.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonAbout :
                intent = new Intent(Menu.this, AboutAcvitity.class);
                startActivity(intent);
        }
    }
}
