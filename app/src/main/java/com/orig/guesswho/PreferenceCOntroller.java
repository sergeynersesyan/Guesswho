package com.orig.guesswho;

import android.content.Context;
import android.content.SharedPreferences;

import com.orig.guesswho.block.Package;

import static android.content.Context.MODE_PRIVATE;

/*
 * Created by Mr Nersesyan on 31/10/2016.
 */

public class PreferenceController {
    private SharedPreferences sPref;
    private Context context;
    private static PreferenceController thisInstance;
    private String[] defaultPackages = {"trial"};

    private PreferenceController(Context c) {
        context = c;
        sPref = context.getSharedPreferences(AppConstants.PREF_FOLDER, MODE_PRIVATE);
    }

    public static PreferenceController getInstance(Context c) {
        if (thisInstance == null) {
            thisInstance = new PreferenceController(c);
        }
        return thisInstance;
    }


    public void loadLock(Package p) {
        for (String s : defaultPackages) {
            if (p.packageUID.equals(s)) {
                p.locked = false;
                return;
            }
        }
        p.locked = sPref.getBoolean(p.packageUID + AppConstants.PREF_PACK_LOCK, true);
    }

    public int getTotalCoins() {
        return sPref.getInt(AppConstants.PREF_TOTAL_COINS, AppConstants.COINS_IN_START);
    }

    public void setTotalCoins(int newValue) {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt(AppConstants.PREF_TOTAL_COINS, newValue);
        ed.apply();
    }

    public void setLock(String packageUID, boolean value) {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putBoolean(packageUID + AppConstants.PREF_PACK_LOCK, value).apply();
    };

    public void setLanguage(String l) {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(AppConstants.PREF_LANGUAGE, l).apply();
    }

    public String getLanguage() {
        return sPref.getString(AppConstants.PREF_LANGUAGE, "en");
    }
}
