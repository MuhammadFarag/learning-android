package com.mfarag.learn.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {
    public static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startService(Context context) {
        Intent intent = new Intent(context, MyIntentService.class);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "handling an intent");
    }

}