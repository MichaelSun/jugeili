package com.tugou.jgl.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by michael on 14-3-8.
 */
public class SettingManager {

    private static SettingManager mInstance;

    private Context mContext;

    private SharedPreferences mSharedPreferences;

    private SharedPreferences.Editor mEditor;

    public static synchronized SettingManager getInstance() {
        if (mInstance == null) {
            mInstance = new SettingManager();
        }

        return mInstance;
    }

    private static final String SHARE_PREFERENCE_NAME = "setting_manager_share_pref_custom";

    // 在Application中一定要调用
    public synchronized void init(Context context) {
        mContext = context.getApplicationContext();
        mSharedPreferences = mContext.getSharedPreferences(SHARE_PREFERENCE_NAME, 0);
        mEditor = mSharedPreferences.edit();
    }

    private SettingManager() {
    }

    public void setIntProperty(String key, int data) {
        mEditor.putInt(key, data).commit();
    }

    public int getIntProperty(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    public void setStringProperty(String key, String data) {
        mEditor.putString(key, data).commit();
    }

    public String getStringProperty(String key) {
        return mSharedPreferences.getString(key, null);
    }

    public void setBooleanProperty(String key, Boolean data) {
        mEditor.putBoolean(key, data).commit();
    }

    public boolean getBooleanProperty(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }
}
