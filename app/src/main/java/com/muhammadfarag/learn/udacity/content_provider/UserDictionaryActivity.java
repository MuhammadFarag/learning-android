package com.muhammadfarag.learn.udacity.content_provider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.muhammadfarag.learn.R;

public class UserDictionaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = (TextView) findViewById(R.id.udacity_content_provider_user_dictionary_text_view);
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);
        int wordColumnIndex = cursor.getColumnIndex(UserDictionary.Words.WORD);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            textView.append(cursor.getString(wordColumnIndex));
            textView.append("\n");
        }
        cursor.close();

        setContentView(R.layout.activity_user_dictionary);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_dictionary, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
