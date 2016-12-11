package com.orig.guesswho.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.orig.guesswho.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView headerText = (TextView) findViewById(R.id.header_text);
        Typeface typeFace= Typeface.createFromAsset(getAssets(), "fonts/aladin.ttf");
        headerText.setTypeface(typeFace);
    }
}
