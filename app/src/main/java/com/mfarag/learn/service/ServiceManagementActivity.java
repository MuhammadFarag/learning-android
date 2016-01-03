package com.mfarag.learn.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.mfarag.learn.R;
import com.mfarag.learningandroid.MyService;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_management);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_service_start, R.id.button_service_stop})
    public void startService(Button button){
        switch(button.getId()){
            case R.id.button_service_start:
                startService(new Intent(this, MyService.class));
                break;
            case R.id.button_service_stop:
                stopService(new Intent(this, MyService.class));
                break;
            default:

                break;
        }
    }
}
