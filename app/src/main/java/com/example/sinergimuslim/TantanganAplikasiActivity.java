package com.example.sinergimuslim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TantanganAplikasiActivity extends AppCompatActivity {
    Button backTentang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tantangan_aplikasi);
        backTentang = findViewById(R.id.buttonBackTentang);
        backTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TantanganAplikasiActivity.super.onBackPressed();
            }
        });
    }
}
