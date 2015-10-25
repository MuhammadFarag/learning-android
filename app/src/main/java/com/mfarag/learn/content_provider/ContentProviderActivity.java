package com.mfarag.learn.content_provider;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.mfarag.learn.R;

/**
 * Created by muhammadfarag on 10/25/15.
 */
public class ContentProviderActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState,persistentState);
        setContentView(R.layout.activity_content_provider);
    }
}
