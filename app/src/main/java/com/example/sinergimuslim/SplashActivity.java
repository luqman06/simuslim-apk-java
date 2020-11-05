package com.example.sinergimuslim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {

    ProgressBar pbLoading;


    private int waktu_loading = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
pbLoading = findViewById(R.id.pbLoading);
pbLoading.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                pbLoading.setVisibility(View.GONE);
                finish();

            }
        }, waktu_loading);


    }

    private class Unbinder {
    }
}
