package com.example.securesmsservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    private ProgressBar loadingProgress;
    private Handler handler;
    private Runnable runnable;
    private int progress=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        loadingProgress=findViewById(R.id.splashProgBar);
        handler=handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                loadingProgress.setProgress(progress++);

                if(progress>100){
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                }else{
                    handler.postDelayed(runnable,10);
                }
            }
        };
        handler.post(runnable);
    }
}