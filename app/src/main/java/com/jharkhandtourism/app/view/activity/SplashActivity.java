package com.jharkhandtourism.app.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.jharkhandtourism.app.R;

public class SplashActivity extends AppCompatActivity {

    ImageView jharkhand_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        jharkhand_logo = findViewById(R.id.jharkhand_logo);

        new Handler().postDelayed((Runnable) () -> {

            jharkhand_logo.setImageResource(R.drawable.splash1);

            new Handler().postDelayed((Runnable) () -> {

                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
                finish();

            },3000);

        },3000);



    }
}