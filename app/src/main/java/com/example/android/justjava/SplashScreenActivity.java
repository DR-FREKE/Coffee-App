package com.example.android.justjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;


public class SplashScreenActivity extends AppCompatActivity {

    private final int splashtime = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Splashscreen();
    }

    private void Splashscreen(){
        TextView txtView = new TextView(this);
        txtView.setText("welcome");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, StartUpScreen.class);
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
                finish();
            }
        }, splashtime);
    }
}
