package com.zx.application_stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private int seconds=0;
private boolean running;
private boolean wasrunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasrunning=savedInstanceState.getBoolean("wasrunning");
        }
        runTimer();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasrunning", wasrunning);
    }
    /*
    @Override
    protected void onStop() {
        super.onStop();
        wasrunning=running;
        running=false;
    }
     */
    @Override
    protected void onPause() {
        super.onPause();
        wasrunning=running;
        running=false;
    }
    /*
    @Override
    protected void onStart() {
        super.onStart();
        if(wasrunning) {
            running = true;
        }
     */
        @Override
        protected void onResume() {
            super.onResume();
            if(wasrunning) {
                running = true;
            }
    }
    public void onClickStart(View view){
        running=true;
    }
    public void onClickStop(View view){
        running=false;
    }
    public void onClickReset(View view){
        running=false;
        seconds=0;
    }
    private void runTimer(){
        final TextView timeview=(TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                timeview.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}