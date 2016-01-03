package com.mfarag.learn.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.mfarag.learn.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceManagementActivity extends AppCompatActivity {

    public static final String TAG = ServiceManagementActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_management);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_service_start_async, R.id.button_service_start_timer, R.id.button_service_stop, R.id.button_intent_service_start})
    public void startService(Button button) {
        switch (button.getId()) {
            case R.id.button_service_start_async:
                startService(MyService.createIntent(this, MyService.TASK_ASYNC));
                break;
            case R.id.button_service_start_timer:
                startService(MyService.createIntent(this, MyService.TASK_TIMER));
                break;
            case R.id.button_service_stop:
                stopService(new Intent(this, MyService.class));
                break;
            case R.id.button_intent_service_start:
                MyIntentService.startService(this);
                break;
            default:

                break;
        }
    }

    public static class MyReceiver extends BroadcastReceiver {
        public MyReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Received call back broadcast from intent service");
        }
    }
}
