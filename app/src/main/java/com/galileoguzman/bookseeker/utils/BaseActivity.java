package com.galileoguzman.bookseeker.utils;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.galileoguzman.bookseeker.R;

public class BaseActivity extends AppCompatActivity {

    public ProgressDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loader = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showLoaderIndicator(String message) {
        loader.setTitle(R.string.app_name);
        loader.setMessage(message);
        loader.setCancelable(false);

        loader.show();
    }

    public void closeLoaderIndicator() {
        loader.dismiss();
    }
}
