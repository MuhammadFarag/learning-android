package com.mfarag.learn;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.mfarag.learn.content_provider.ContentProviderActivity;
import com.mfarag.learn.fragment.FragmentsActivity;
import com.mfarag.learn.intent.ImplicitIntentActivity;
import com.mfarag.learn.notifications.NotificationsActivity;
import com.mfarag.learn.service.ServiceManagementActivity;
import com.mfarag.learn.settings.SettingsActivity;
import com.mfarag.learn.udacity.content_provider.ContactsActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

    @OnClick({R.id.udacity_content_provider_user_dictionary,
            R.id.fragments_activity_button, R.id.content_providers,
            R.id.service_management, R.id.notifications,
            R.id.implicit_intent})
    public void startActivity(Button button) {
        switch (button.getId()) {
            case R.id.udacity_content_provider_user_dictionary:
                startActivity(new Intent(this, ContactsActivity.class));
                break;
            case R.id.fragments_activity_button:
                startActivity(new Intent(this, FragmentsActivity.class));
                break;
            case R.id.content_providers:
                startActivity(new Intent(this, ContentProviderActivity.class));
                break;
            case R.id.service_management:
                startActivity(new Intent(this, ServiceManagementActivity.class));
                break;
            case R.id.notifications:
                startActivity(new Intent(this, NotificationsActivity.class));
                break;
           case R.id.implicit_intent:
                startActivity(new Intent(this, ImplicitIntentActivity.class));
                break;
            default:
                Log.w(TAG, "Unknown item clicked, id: " + button.getId());
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
