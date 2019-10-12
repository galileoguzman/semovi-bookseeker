package com.galileoguzman.bookseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.galileoguzman.bookseeker.utils.DataProcessor;

public class MainActivity extends AppCompatActivity {

    private DataProcessor dataProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataProcessor = new DataProcessor(this);
    }

    public void btnLogoutPressed(View view) {
        dataProcessor.saveStringValueForKey("userToken", "---");
        dataProcessor.saveBooleanValueForKey("isActiveSession", false);

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
