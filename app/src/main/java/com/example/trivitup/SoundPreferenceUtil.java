package com.example.trivitup;

import android.content.Context;
import android.content.SharedPreferences;

public class SoundPreferenceUtil {
    private static final String PREFERENCES_NAME = "SoundPreferences";
    private static final String KEY_SOUND_ENABLED = "isSoundEnabled";

    public static boolean isSoundEnabled(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY_SOUND_ENABLED, true);
    }

    public static void setSoundEnabled(Context context, boolean isSoundEnabled) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(KEY_SOUND_ENABLED, isSoundEnabled);
        editor.apply();
    }
}

