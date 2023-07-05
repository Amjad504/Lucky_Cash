package com.lucky.cash.utils;

import android.app.Application;
import android.content.SharedPreferences;

public class MyApp extends Application {
    static SharedPreferences preferences;
    static SharedPreferences.Editor prefEditor;

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = getSharedPreferences("news", MODE_PRIVATE);
        prefEditor = preferences.edit();
    }

    public static void SetCoin(int count) {
        prefEditor.putInt("coin", count).commit();
    }

    public static int GetCoin() {
        return preferences.getInt("coin", 0);
    }
}
