package com.orig.guesswho;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.orig.guesswho.activity.SettingsActivity;

import java.util.Locale;

/**
 * Created by Mr Nersesyan on 16/11/2016.
 */

public class Utils {

    public static void setLocale(String lang, Activity activity) {
        Locale myLocale = new Locale(lang);
        Resources res = activity.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if (!conf.locale.getLanguage().equals(lang)){
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            PreferenceController.getInstance(activity.getApplicationContext()).setLanguage(lang);
            Intent refresh = new Intent(activity, activity.getClass());
            activity.startActivity(refresh);
            activity.finish();
        }
    }

}
