package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    Spinner spTimes;
    TextView txtShowTime;

    String selectedTime = "";
    Button btnStart;
    int Second = 0, Minute = 0 ,DSecond =0 ;
    int runningTime = 0;
    String ps = "", pm = "", pds="";
    int receivedTime = 0;
    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetUpView();
        MediaPlayer mediaPlayer= MediaPlayer.create(this, R.raw.be_happy);
        ArrayList<String> Times = new ArrayList<String>((Arrays.asList("1","5", "10", "15", "20", "25", "30")));
        ArrayAdapter<String> TimesAdapted = new ArrayAdapter<String>(this, R.layout.custom_spinner, Times);
       TimesAdapted.setDropDownViewResource(R.layout.custom_spinner);

        spTimes.setAdapter(TimesAdapted);
        spTimes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedTime = Times.get(i).toString();
                receivedTime = Integer.parseInt(selectedTime);
                runningTime = receivedTime * 3600;
                Second = (runningTime / 60);
                Minute = (runningTime / 3600);
                DSecond = (runningTime%60);


                if (DSecond < 10) pds = "0";
                else pds= "";

                if (Second < 10) ps = "0";
                else ps= "";

                if (Minute < 10) pm = "0";
                else pm="";

                txtShowTime.setText(String.format("%s%d : %s%d : %s%d", pm, Minute, ps, Second, pds ,DSecond));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!running) {
                    running = true;
                    btnStart.setText("Stop");
                }else {
                    running= false ;
                    btnStart.setText("Start");
                }
            }
        });

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Second = ((runningTime%3600) / 60);
                Minute = (runningTime / 3600);
                DSecond = (runningTime%60);



                if (DSecond < 10) pds = "0";
                else pds= "";

                if (Second < 10) ps = "0";
                else ps= "";

                if (Minute < 10) pm = "0";
                else pm="";

                txtShowTime.setText(String.format("%s%d : %s%d : %s%d", pm, Minute, ps, Second, pds ,DSecond));
                if(running ) {
                    runningTime--;
                    if(runningTime<=0) {
                        running = false;
                        Intent intent = new Intent(MainActivity.this, ringActivity.class);
                        startActivity(intent);

                    }

                }
handler.postDelayed(this, 1);
            }
        });
//        handler.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//
//
//                Second = runningTime % 60;
//                Minute = (runningTime / 60);
//
//                if (Second < 10) ps = "0";
//                else ps= "";
//
//                if (Minute < 10) pm = "0";
//                else pm="";
//
//                txtShowTime.setText(String.format("%s%d : %s%d", pm, Minute, ps, Second));
//               if(running) {
//                   runningTime--;
//               }
//
//
//
//            }
//        }, 10);


    }

    public void SetUpView() {
        spTimes = findViewById(R.id.spTimes);
        txtShowTime = findViewById(R.id.txtShowTime);
        btnStart = findViewById(R.id.btnStart);

    }
}