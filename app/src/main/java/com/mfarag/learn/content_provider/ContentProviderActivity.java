package com.mfarag.learn.content_provider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.mfarag.learn.R;

/**
 * Created by muhammadfarag on 10/25/15.
 */
public class ContentProviderActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        TextView view = (TextView) findViewById(R.id.content_provider_text);
        view.setText("The items: \n");

        ContentResolver resolver = getContentResolver();

        Cursor cursor = resolver.query(ItemsContract.ItemsEntry.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            Log.e("Zal", "Cursor: " + cursor);
            while (cursor.moveToNext()) {
                view.setText(view.getText() + cursor.getString(0) + "\n");
            }
        } else {
            Log.e("Zal", "Cursor is null");
        }
    }
}
