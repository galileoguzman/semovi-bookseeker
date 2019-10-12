package com.galileoguzman.bookseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.galileoguzman.bookseeker.utils.DataProcessor;

public class SplashActivity extends AppCompatActivity {

    private DataProcessor dataProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        dataProcessor = new DataProcessor(this);

        Boolean isActiveSession = dataProcessor.getBooleamValueForKey("isActiveSession");
        final Intent intent;

        if (isActiveSession) {
            intent = new Intent(SplashActivity.this, MainActivity.class);
        } else {
            intent = new Intent(SplashActivity.this, LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
