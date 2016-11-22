package com.orig.guesswho.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.orig.guesswho.AppConstants;
import com.orig.guesswho.PreferenceController;
import com.orig.guesswho.R;
import com.orig.guesswho.Utils;

public class SettingsActivity extends AppCompatActivity {

    private TextView headerText;
    private RadioButton radioEN, radioRU, radioHY;
    boolean languageChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        headerText = (TextView)findViewById(R.id.header_text);
        Typeface typeFace= Typeface.createFromAsset(getAssets(), "fonts/aladin.ttf");
        headerText.setTypeface(typeFace);
        radioEN = (RadioButton) findViewById(R.id.radio_en);
        radioRU = (RadioButton) findViewById(R.id.radio_ru);
        radioHY = (RadioButton) findViewById(R.id.radio_hy);
        radioEN.setOnClickListener(radioButtonClickListener);
        radioRU.setOnClickListener(radioButtonClickListener);
        radioHY.setOnClickListener(radioButtonClickListener);
        switch (PreferenceController.getInstance(getApplicationContext()).getLanguage()) {
            case "en":
                radioEN.setChecked(true);
                radioEN.setTypeface(null, Typeface.BOLD);
                break;
            case "ru":
                radioRU.setChecked(true);
                radioRU.setTypeface(null, Typeface.BOLD);
                break;
            case "hy":
                radioHY.setChecked(true);
                radioHY.setTypeface(null, Typeface.BOLD);
                break;
        }
    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton radioButton = (RadioButton)v;
            languageChanged = true;
            Intent intent = new Intent();
            intent.putExtra(AppConstants.EXTRA_LANGUAGE_CHANGED, languageChanged);
            setResult(RESULT_OK, intent);
            switch (radioButton.getId()) {
                case R.id.radio_en:
                    Utils.setLocale("en", SettingsActivity.this);
                    break;
                case R.id.radio_ru:
                    Utils.setLocale("ru", SettingsActivity.this);
                    break;
                case R.id.radio_hy:
                    Utils.setLocale("hy", SettingsActivity.this);
                    break;
                default:
                    break;
            }
        }
    };

}
