package io.focusminds.fractioncards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class InitialScreen extends AppCompatActivity {

    ProgressBar progressBar;
    int progressStatus;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_scren);
        progressBar = findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 5;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iReturnMain = new Intent(InitialScreen.this,StartScreen.class);
                startActivity(iReturnMain);
                overridePendingTransition(R.anim.zoom_in,R.anim.static_anim);
                finish();

            }
        },2500);
    }
}