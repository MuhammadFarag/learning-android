package com.mfarag.learn.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.mfarag.learn.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImplicitIntentActivity extends AppCompatActivity {
    public static final String TAG = ImplicitIntentActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.open_url_in_browser, R.id.intent_no_valid_action})
    public void sendIntent(Button button) {
        Intent intent;
        switch (button.getId()) {
            case R.id.open_url_in_browser:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mfarag.com"));
                startActivity(intent);
                break;
            case R.id.intent_no_valid_action:
                intent = new Intent("Not a valid action");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Nothing to be done, the required action is not valid", Toast.LENGTH_SHORT).show();
                    Log.w(TAG, "No application available to handle the required action: \"Not a valid action\"");
                }
                break;
            default:
                Log.w(TAG, "Unknown button id");
                break;
        }
    }
}
