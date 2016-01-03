package com.mfarag.learningandroid;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.util.Arrays;

public class MyService extends Service {

    public static final String TAG = MyService.class.getSimpleName();

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "OnCreate has been called");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "On Bind has been called");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "On start command has been called");
        DoInBackground background = new DoInBackground();
        background.execute("A parameter");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "OnDestroy has been called");
    }

    private class DoInBackground extends AsyncTask<String, Integer, Long> {

        @Override
        protected Long doInBackground(String... params) {
            Log.d(TAG, "Do in background started with parameters: " + Arrays.toString(params));
            publishProgress(1);
            return 80L;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d(TAG, "Progress updated: " + Arrays.toString(values));
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            Log.d(TAG, "Execution completed: " + aLong);
            stopSelf();
        }
    }
}
