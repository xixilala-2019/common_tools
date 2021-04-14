package com.xixilala.adapteui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Density.setDensity(getApplication(),this);
        setContentView(R.layout.activity_main);

        findViewById(R.id.text).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DisplayCutoutAdapte.class);
            startActivity(intent);
        });
    }
}