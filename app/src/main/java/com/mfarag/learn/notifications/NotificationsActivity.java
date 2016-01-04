package com.mfarag.learn.notifications;

import android.app.Notification;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;

import com.mfarag.learn.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_basic_notification)
    public void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(getString(R.string.notification_simple_title));
        builder.setContentText(getString(R.string.notification_simple_text));
        Notification notification = builder.build();
        NotificationManagerCompat.from(this).notify(101, notification);
    }
}
