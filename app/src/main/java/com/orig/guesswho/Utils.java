package com.orig.guesswho;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.orig.guesswho.activity.QuestionActivity;
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

    public static void animateCoinsUp(final Context context, final TextView tv, int coins) {
        String plusText = "+" + coins;
        tv.setText(plusText);
        tv.setVisibility(View.VISIBLE);
        Animation animPlus = AnimationUtils.loadAnimation(context, R.anim.move_up);
        animPlus.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tv.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        tv.startAnimation(animPlus);
    }

}
