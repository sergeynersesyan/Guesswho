package com.orig.guesswho.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.orig.guesswho.AppConstants;
import com.orig.guesswho.PreferenceController;
import com.orig.guesswho.QuestionHelper;
import com.orig.guesswho.R;
import com.orig.guesswho.Utils;


public class HomePageActivity extends AppCompatActivity {

    private View playButton, settingsButton, aboutButton;
    private TextView headerText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QuestionHelper.getInstance().initStringIDs();
        setContentView(R.layout.activity_home_page);

        Utils.setLocale(PreferenceController.getInstance(getApplicationContext()).getLanguage(), this);
        playButton = findViewById(R.id.play_button_linearLayout);
        settingsButton = findViewById(R.id.settings_button_linearLayout);
        headerText = (TextView)findViewById(R.id.header_text);
        Typeface typeFace= Typeface.createFromAsset(getAssets(), "fonts/aladin.ttf");
        headerText.setTypeface(typeFace);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, PackageListActivity.class);
                startActivity(intent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, SettingsActivity.class);
                startActivityForResult(intent, AppConstants.REQ_CODE_LANGUAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.REQ_CODE_LANGUAGE && resultCode == RESULT_OK && data.getBooleanExtra(AppConstants.EXTRA_LANGUAGE_CHANGED, true)) {
            ((TextView)findViewById(R.id.settings_text)).setText(R.string.action_settings);
            ((TextView)findViewById(R.id.about_text)).setText(R.string.about);
            ((TextView)findViewById(R.id.play_text)).setText(R.string.play);
        }
    }
}
