package com.mfarag.learn.settings;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mfarag.learn.R;

public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String TAG = SettingsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Fragment fragment = getFragmentManager().findFragmentById(R.id.settings_container);
        if (fragment == null) {
            fragment = SettingsFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.settings_container, fragment).commit();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d(TAG, "Preferences has changed for key: " + key);
        if (key.equals(getString(R.string.pref_key_checkbox))) {
            Log.d(TAG, "New value is: " + sharedPreferences.getBoolean(getString(R.string.pref_key_checkbox), false));
        } else if (key.equals(getString(R.string.pref_key_checkbox1))) {
            Log.d(TAG, "New value is: " + sharedPreferences.getBoolean(getString(R.string.pref_key_checkbox1), false));
        } else if (key.equals(getString(R.string.pref_key_checkbox2))) {
            Log.d(TAG, "New value is: " + sharedPreferences.getBoolean(getString(R.string.pref_key_checkbox2), false));
        } else if (key.equals(getString(R.string.pref_key_checkbox3))) {
            Log.d(TAG, "New value is: " + sharedPreferences.getBoolean(getString(R.string.pref_key_checkbox3), false));
        } else {
            Log.d(TAG, "New value is: " + sharedPreferences.getString(key, "No value set"));
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }
}
