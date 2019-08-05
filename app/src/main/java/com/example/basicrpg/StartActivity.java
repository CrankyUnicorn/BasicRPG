package com.example.basicrpg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    Timer myTimer;

    MediaPlayer music;
    MediaPlayer sound;

    int goActivity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageButton btnStart = (ImageButton)findViewById(R.id.buttonStartGame);
        ImageButton btnAbout = (ImageButton)findViewById(R.id.buttonAbout);

        music = MediaPlayer.create(this, R.raw.startmusic);
        sound = MediaPlayer.create(this, R.raw.startbutton);

        btnStart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            goActivity=1;
            ButtonSound();

        }

        });



        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goActivity=2;
                ButtonSound();




            }

        });




        PlayMusic();
    }


    //MUSIC SOUND PLAY#############################
    public void PlayMusic() {



        try {
            if (music.isPlaying()) {
                music.stop();
                music.release();
                music = MediaPlayer.create(this, R.raw.startmusic);
            } music.start();
        } catch(Exception e) {  }

    }

    public void ButtonSound() {
        StopAllSounds();



        try {
            if (sound.isPlaying()) {

            }else{
                sound.start();
                sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    switch (goActivity) {

                        case 0:
                            break;
                        case 1:
                            startActivity(new Intent(StartActivity.this, MainActivity.class));
                            sound.release();
                            sound=null;
                            break;
                        case 2:
                            startActivity(new Intent(StartActivity.this, AboutActivity.class));
                            sound.release();
                            sound=null;
                            break;
                        default:
                            break;
                    }
                }
            });
        }
        } catch (Exception e) { }



    }


    private void StopAllSounds(){
        try{
            if (sound.isPlaying()) {

            }
        }catch (Exception e){}

        try{
            if (music.isPlaying()) {
                music.stop();
                music.release();
                music=null;
            }
        }catch (Exception e){}
    }

}
