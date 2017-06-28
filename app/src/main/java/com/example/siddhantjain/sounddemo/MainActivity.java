package com.example.siddhantjain.sounddemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mplayer;
    AudioManager audio;

    public void clickPlay (View view){
        mplayer.start();
    }
    public void clickStop (View view){
        mplayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mplayer = MediaPlayer.create(this,R.raw.thunder);

        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int max = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int current = audio.getStreamVolume(AudioManager.STREAM_MUSIC);


        final SeekBar slider = (SeekBar) findViewById(R.id.seekBar2);
        slider.setMax(mplayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                slider.setProgress(mplayer.getCurrentPosition());
            }
        }, 0 , 1000);

        slider.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mplayer.seekTo(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mplayer.pause();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mplayer.start();

            }
        });


        SeekBar volume = (SeekBar) findViewById(R.id.seekBar);
        volume.setMax(max);
        volume.setProgress(current) ;
        volume.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                Toast.makeText(MainActivity.this , Integer.toString(i) , Toast.LENGTH_SHORT).show();
                audio.setStreamVolume(AudioManager.STREAM_MUSIC , i , 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
