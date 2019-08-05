package com.example.basicrpg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class EndActivity extends AppCompatActivity {


    MediaPlayer music;
    MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        ImageButton btn = (ImageButton)findViewById(R.id.buttonRestart);


        music = MediaPlayer.create(this, R.raw.endmusic);
        sound = MediaPlayer.create(this, R.raw.startbutton);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonSound();
                Player.GetCurrentPlayer().PlayerReset();
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
                music = MediaPlayer.create(this, R.raw.endmusic);
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
                        startActivity(new Intent(EndActivity.this, StartActivity.class));

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
