package com.mfarag.learn.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    public static final String TAG = MyService.class.getSimpleName();
    public static final String SERVICE_TASK_TYPE_EXTRA = "SERVICE_TASK_TYPE_EXTRA";

    public static final int TASK_ASYNC = 0;
    public static final int TASK_TIMER = 1;
    public static final int TIMER_DELAY_BEFORE_EXECUTION_MILLISECONDS = 1000;
    public static final int TIMER_PERIOD_BETWEEN_EXECUTIONS_MILLISECONDS = 1000;
    private Timer mTimer;


    public MyService() {
    }

    public static Intent createIntent(Context context, @TaskType int taskType) {
        Intent intent = new Intent(context, MyService.class);
        intent.putExtra(SERVICE_TASK_TYPE_EXTRA, taskType);
        return intent;
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
        @TaskType int taskType = intent.getIntExtra(SERVICE_TASK_TYPE_EXTRA, -1);
        switch (taskType) {
            case TASK_ASYNC:
                Log.d(TAG, "Async task will start");
                DoInBackground background = new DoInBackground();
                background.execute("A parameter");
                break;
            case TASK_TIMER:
                mTimer = new Timer();
                mTimer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Executing a timer task");
                    }
                }, TIMER_DELAY_BEFORE_EXECUTION_MILLISECONDS, TIMER_PERIOD_BETWEEN_EXECUTIONS_MILLISECONDS);
                break;
            default:
                Log.w(TAG, "Unknown task type");
                break;
        }


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "OnDestroy has been called");
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    @IntDef({TASK_ASYNC, TASK_TIMER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TaskType {

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
            if (mTimer == null) {
                stopSelf();
            }
        }
    }
}
