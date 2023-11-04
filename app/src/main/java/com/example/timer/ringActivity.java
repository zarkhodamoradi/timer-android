package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ringActivity extends AppCompatActivity {

    Button btnStop;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);

        try {
            SetUpView();

            mediaPlayer = MediaPlayer.create(ringActivity.this, R.raw.be_happy);
            mediaPlayer.start();
            btnStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mediaPlayer.stop();
                    finish();
                }
            });

        } catch (Exception ex) {
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }

    public void SetUpView() {
        btnStop = findViewById(R.id.btnStop);

    }
}