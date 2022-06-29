package com.vcyber.myframe.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.vcyber.myframe.MyApplication;


public class DataCenter {

    private static final String ENCRYPT_KEY = "KEY_ABU";

    public static SharedPreferences getSharePrefs() {
        return MyApplication.getInstance().getSharedPreferences("com_name", Context.MODE_PRIVATE);
    }

    public static void putString(String key, String value) {
        getSharePrefs().edit().putString(AesUtils.encrypt(key, ENCRYPT_KEY), AesUtils.encrypt(value, "")).commit();
    }

    public static String getString(String key, String defaultValue) {
        return AesUtils.decrypt(getSharePrefs().getString(AesUtils.encrypt(key, ENCRYPT_KEY), defaultValue), ENCRYPT_KEY);
    }

    public static void putInt(String key, int value) {
        getSharePrefs().edit().putInt(AesUtils.encrypt(key, ENCRYPT_KEY), value).commit();
    }

    public static int getInt(String key, int defaultValue) {
        return getSharePrefs().getInt(AesUtils.encrypt(key, ENCRYPT_KEY), defaultValue);
    }

    public static void putLong(String key, long value) {
        getSharePrefs().edit().putLong(AesUtils.encrypt(key, ENCRYPT_KEY), value).commit();
    }

    public static long getLong(String key, long defaultValue) {
        return getSharePrefs().getLong(AesUtils.encrypt(key, ENCRYPT_KEY), defaultValue);
    }

    public static void putFloat(String key, float value) {
        getSharePrefs().edit().putFloat(AesUtils.encrypt(key, ENCRYPT_KEY), value).commit();
    }

    public static float getFloat(String key, float defaultValue) {
        return getSharePrefs().getFloat(AesUtils.encrypt(key, ENCRYPT_KEY), defaultValue);
    }

    public static void remove(String key) {
        getSharePrefs().edit().remove(AesUtils.encrypt(key, ENCRYPT_KEY)).commit();
    }
}
