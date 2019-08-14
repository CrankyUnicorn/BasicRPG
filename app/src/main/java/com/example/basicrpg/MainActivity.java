/*
* BASIC RPG - DUNGEON CRAWLER
* CRANKYUNICORN 2019
* azedo.peter@gmail.com
*/

package com.example.basicrpg;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.graphics.Color;
        import android.media.MediaPlayer;
        import android.os.Bundle;

        import android.text.Html;
        import android.text.method.ScrollingMovementMethod;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.Random;
        import java.util.Timer;
        import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    Timer myTimer;
    boolean animationPlaying;

    TextView dungeonNameTitle;
    TextView dungeonLocationTitle;

    ImageView dungeonImageView;
    ImageView dungeonImageViewFoe;
    ImageView dungeonImageViewEffects;
    ImageView dungeonImageViewChar;


    TextView characterIlluminationTitle;

    ImageView redbar;
    ImageView yellowbar;

    TextView dungeonRoomDescriptionTitle;

    Button doorOneButton;
    Button doorTwoButton;
    Button doorThreeButton;
    Button doorFourButton;
    Button doorFiveButton;
    Button doorSixButton;

    ImageView overlayWindow;
    TextView overlayTextView;


    MediaPlayer music;
    MediaPlayer sound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animationPlaying=false;

        dungeonNameTitle = (TextView) findViewById(R.id.dungeonName);

        dungeonLocationTitle = (TextView) findViewById(R.id.dungeonLocation);

        dungeonImageView = (ImageView) findViewById(R.id.dungeonImageView);
        dungeonImageViewFoe = (ImageView) findViewById(R.id.dungeonImageViewFoe);
        dungeonImageViewEffects = (ImageView) findViewById(R.id.dungeonImageViewEffects);
        dungeonImageViewChar = (ImageView) findViewById(R.id.dungeonImageViewChar);

        characterIlluminationTitle = (TextView) findViewById(R.id.characterStats);

        redbar = (ImageView) findViewById(R.id.mainredbar);
        yellowbar = (ImageView) findViewById(R.id.mainyellowbar);

        dungeonRoomDescriptionTitle = (TextView) findViewById(R.id.characterInventory);
        dungeonRoomDescriptionTitle.setMovementMethod(new ScrollingMovementMethod());

        doorOneButton = (Button) findViewById(R.id.buttonOne);
        doorTwoButton = (Button) findViewById(R.id.buttonTwo);
        doorThreeButton = (Button) findViewById(R.id.buttonThree);
        doorFourButton = (Button) findViewById(R.id.buttonFour);
        doorFiveButton = (Button) findViewById(R.id.buttonFive);
        doorSixButton = (Button) findViewById(R.id.buttonSix);

        overlayWindow = (ImageView) findViewById(R.id.mainoverlayscreen);
        overlayTextView = (TextView) findViewById(R.id.mainoverlayscreenText);
        overlayTextView.setMovementMethod(new ScrollingMovementMethod());


        outputContent();

        PlayMusic();

    }


    //########## BUTTON PUSH #######################
    public void buttonOneClick(View view) {//############# ACTIONS ##############

        if (UserInterfaceIOHandler.GetUserInterfaceIOHandler().overlayWindowOpen == false) {
            if (!animationPlaying) {
                UserInterfaceIOHandler.GetUserInterfaceIOHandler().SelectedActionButton(String.valueOf(doorOneButton.getText()));
                AnimateTurn();
                CheckIfDead();
            }
        }
    }

    public void buttonTwoClick(View view) {
        if (UserInterfaceIOHandler.GetUserInterfaceIOHandler().overlayWindowOpen == false) {
            if (!animationPlaying) {
                ButtonSound();
                UserInterfaceIOHandler.GetUserInterfaceIOHandler().SelectedDoorButton(0);
                AnimateTurn();
                CheckIfDead();
            }
        }
    }

    public void buttonThreeClick(View view) {
        if (UserInterfaceIOHandler.GetUserInterfaceIOHandler().overlayWindowOpen == false) {
            if (!animationPlaying) {
                ButtonSound();
                UserInterfaceIOHandler.GetUserInterfaceIOHandler().SelectedDoorButton(1);
                AnimateTurn();
                CheckIfDead();
            }
        }
    }

    public void buttonFourClick(View view) {//############# JOURNAL ##############
        UserInterfaceIOHandler.GetUserInterfaceIOHandler().SelectedActionButton(String.valueOf(doorFourButton.getText()));
        outputContent();
        CheckIfDead();
    }

    public void buttonFiveClick(View view) {
        if (UserInterfaceIOHandler.GetUserInterfaceIOHandler().overlayWindowOpen == false) {
            if (!animationPlaying) {
                ButtonSound();
                UserInterfaceIOHandler.GetUserInterfaceIOHandler().SelectedDoorButton(2);
                AnimateTurn();
                CheckIfDead();
            }
        }
    }

    public void buttonSixClick(View view) {
        if (UserInterfaceIOHandler.GetUserInterfaceIOHandler().overlayWindowOpen == false) {
            if (!animationPlaying) {
                ButtonSound();
                UserInterfaceIOHandler.GetUserInterfaceIOHandler().SelectedDoorButton(3);
                AnimateTurn();
                CheckIfDead();
            }
        }
    }


    //######### OUTPUT ###########
    private void outputContent() {//you dont need to replace this every turns. you only need to replace the ones that area changed

        //dungeon stats
        dungeonNameTitle.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonNameTitle());
        dungeonLocationTitle.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonLocationTitle());

        //image
        dungeonImageView.setImageResource(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonRoomImage());
        dungeonImageViewFoe.setImageResource(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonRoomImageFoe());
        dungeonImageViewEffects.setImageResource(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonRoomImageEffect());
        dungeonImageViewChar.setImageResource(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonRoomImageChar());

        //player illumination
        characterIlluminationTitle.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetCharacterIlluminationTitle());

        //bars
        redbar.getLayoutParams().width = (int) ((650 / 100) * Player.GetCurrentPlayer().GetPlayerHealth());
        redbar.getLayoutParams().height = 22;
        redbar.requestLayout();
        yellowbar.getLayoutParams().width = (int) ((650 / 100) * Player.GetCurrentPlayer().GetPlayerSanity());
        yellowbar.getLayoutParams().height = 22;
        yellowbar.requestLayout();

        //room description
        dungeonRoomDescriptionTitle.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonRoomDescriptionTitle());

        //buttons
        doorOneButton.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetACtivityButtonTitle(0));
        doorOneButton.setEnabled(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetACtivityButtonTitle(0) != "" ? true : false);

        doorTwoButton.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDoorButtonTitle(0));
        doorTwoButton.setEnabled(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDoorButtonTitle(0) != "" ? true : false);

        doorThreeButton.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDoorButtonTitle(1));
        doorThreeButton.setEnabled(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDoorButtonTitle(1) != "" ? true : false);

        doorFourButton.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetACtivityButtonTitle(1));
        doorFourButton.setEnabled(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetACtivityButtonTitle(1) != "" ? true : false);

        doorFiveButton.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDoorButtonTitle(2));
        doorFiveButton.setEnabled(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDoorButtonTitle(2) != "" ? true : false);

        doorSixButton.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDoorButtonTitle(3));
        doorSixButton.setEnabled(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDoorButtonTitle(3) != "" ? true : false);

        //overlay screen
        overlayWindow.setVisibility(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetOverlayWindow() ? View.VISIBLE : View.INVISIBLE);
        overlayTextView.setVisibility(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetOverlayWindow() ? View.VISIBLE : View.INVISIBLE);
        overlayTextView.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetOverlayText());

    }

    //MUSIC SOUND PLAY#############################
    public void PlayMusic() {


        music = MediaPlayer.create(this, R.raw.catsouls);
        music.setLooping(true);
        try {
            if (music.isPlaying()) {
                music.stop();
                music.release();
                music = MediaPlayer.create(this, R.raw.catsouls);
            }
            music.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ButtonSound() {

        int doorSound = Util.GenerateNumberBetween(0, 4);
        int soundRef = 0;

        switch (doorSound) {

            case 0:
                soundRef = R.raw.dooropen01;
                break;
            case 1:
                soundRef = R.raw.dooropen02;
                break;
            case 2:
                soundRef = R.raw.dooropen03;
                break;
            case 3:
                soundRef = R.raw.dooropen04;
                break;
            default:
                break;
        }

        sound = MediaPlayer.create(this, soundRef);

        try {
            if (sound.isPlaying()) {
                sound.stop();
                sound.release();
                sound = MediaPlayer.create(this, soundRef);
            }
            sound.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void StopAllSounds() {
        try {
            if (sound.isPlaying()) {
                sound.stop();
                sound.release();
            }
        } catch (Exception e) {
        }

        try {
            if (music.isPlaying()) {
                music.stop();
                music.release();
            }
        } catch (Exception e) {
        }

    }

    //###END GAME METHOD###
    public void EndGame() {
        myTimer.cancel();
        StopAllSounds();
        startActivity(new Intent(MainActivity.this, EndActivity.class));

    }

    //###CHECK DEAD###
    private void CheckIfDead() {
        if (Player.GetCurrentPlayer().GetPlayerHealth() == 0) {
            EndGame();
        }
    }


    //############################ SOMETHING ELSE ##########################################

    public void AnimateTurn() {
        if (!animationPlaying) {
            animationPlaying=true;

            myTimer = new Timer();
            myTimer.schedule(new TimerTask() {
                @Override
                public void run() {


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switch (Player.GetCurrentPlayer().GetPlayerLastTurnAction()) {
                                case "FOE_HIT":
                                    dungeonImageViewEffects.setImageResource(ImageReferences.imageSlashFoe[Util.GenerateNumberBetween(0,ImageReferences.imageSlashFoe.length)]);
                                    //dungeonImageViewFoe.setImageResource(ImageReferences.imageFoe[0]);
                                    dungeonImageViewFoe.setX(20.0f);
                                    dungeonImageViewFoe.setColorFilter(Color.argb(127, 255, 127, 127));
                                    //dungeonImageViewChar.setImageResource(ImageReferences.imageCharacter[0]);
                                    dungeonImageViewChar.setX(20.0f);
                                    dungeonImageViewChar.setColorFilter(Color.argb(0, 0, 0, 0));
                                    break;
                                case "FOE_DODGE":
                                    dungeonImageViewEffects.setImageResource(R.drawable.dodge00);
                                    //dungeonImageViewFoe.setImageResource(ImageReferences.imageFoe[0]);
                                    dungeonImageViewFoe.setX(-20.0f);
                                    //dungeonImageViewFoe.setColorFilter(Color.argb(127, 255, 127, 127));
                                    //dungeonImageViewChar.setImageResource(ImageReferences.imageCharacter[0]);
                                    dungeonImageViewChar.setX(20.0f);
                                    //dungeonImageViewChar.setColorFilter(Color.argb(0, 0, 0, 0));
                                    break;
                                case "PLAYER_HIT":
                                    dungeonImageViewEffects.setImageResource(R.drawable.charhit00);
                                    //dungeonImageViewFoe.setImageResource(ImageReferences.imageFoe[0]);
                                    dungeonImageViewFoe.setX(-20.0f);
                                    dungeonImageViewFoe.setColorFilter(Color.argb(0, 0, 0, 0));
                                    //dungeonImageViewChar.setImageResource(ImageReferences.imageCharacter[0]);
                                    dungeonImageViewChar.setX(-20.0f);
                                    dungeonImageViewChar.setColorFilter(Color.argb(127, 255, 127, 127));
                                    break;
                                case "PLAYER_DODGE":
                                    dungeonImageViewEffects.setImageResource(R.drawable.dodge01);
                                    //dungeonImageViewFoe.setImageResource(ImageReferences.imageFoe[0]);
                                    dungeonImageViewFoe.setX(-20.0f);
                                    //dungeonImageViewFoe.setColorFilter(Color.argb(127, 255, 127, 127));
                                    //dungeonImageViewChar.setImageResource(ImageReferences.imageCharacter[0]);
                                    dungeonImageViewChar.setX(20.0f);
                                    //dungeonImageViewChar.setColorFilter(Color.argb(0, 0, 0, 0));
                                    break;
                                case "PLAYER_INVESTIGATE":
                                    dungeonImageViewEffects.setImageResource(ImageReferences.imageInvestigate[Util.GenerateNumberBetween(0,ImageReferences.imageInvestigate.length)]);
                                    break;
                                case "PLAYER_TRAP":
                                    dungeonImageViewEffects.setImageResource(R.drawable.trap01);
                                    dungeonImageViewChar.setColorFilter(Color.argb(127, 255, 127, 127));
                                    break;
                                case "PLAYER_DETECT_TRAP":
                                    dungeonImageViewEffects.setImageResource(R.drawable.trap00);
                                    break;
                                case "PLAYER_DISARM_TRAP":
                                    dungeonImageViewEffects.setImageResource(R.drawable.trap02);
                                    break;
                                case "PLAYER_TREASURE":
                                    dungeonImageViewEffects.setImageResource(R.drawable.treasu01);
                                    break;
                                case "PLAYER_DETECT_TREASURE":
                                    dungeonImageViewEffects.setImageResource(R.drawable.treasu00);
                                    break;
                                case "FOE_ARMOR_BREAK":
                                    dungeonImageViewEffects.setImageResource(R.drawable.armor00);
                                    break;
                                case "NEUTRAL":
                                    dungeonImageViewEffects.setImageResource(R.drawable.nofoe00);
                                    //dungeonImageViewFoe.setImageResource(ImageReferences.imageFoe[0]);
                                    dungeonImageViewFoe.setX(0.0f);
                                    dungeonImageViewFoe.setColorFilter(Color.argb(0, 0, 0, 0));
                                    //dungeonImageViewChar.setImageResource(ImageReferences.imageCharacter[0]);
                                    dungeonImageViewChar.setX(0.0f);
                                    dungeonImageViewChar.setColorFilter(Color.argb(0, 0, 0, 0));
                                    break;
                                default:
                                    dungeonImageViewEffects.setImageResource(R.drawable.nofoe00);
                                    //dungeonImageViewFoe.setImageResource(ImageReferences.imageFoe[0]);
                                    dungeonImageViewFoe.setX(0.0f);
                                    dungeonImageViewFoe.setColorFilter(Color.argb(0, 0, 0, 0));
                                    //dungeonImageViewChar.setImageResource(ImageReferences.imageCharacter[0]);
                                    dungeonImageViewChar.setX(0.0f);
                                    dungeonImageViewChar.setColorFilter(Color.argb(0, 0, 0, 0));
                                    break;
                            }
                        }
                    });


                    Log.d("Actions", Player.GetCurrentPlayer().GetPlayerLastTurnAction());


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (Player.GetCurrentPlayer().GetPlayerLastTurnAction() == "Empty") {

                                outputContent();
                            }
                        }
                    });


                    if (Player.GetCurrentPlayer().GetPlayerLastTurnAction() == "Empty") {
                        myTimer.cancel();
                        animationPlaying = false;
                    } else {

                        Player.GetCurrentPlayer().DeletePlayerLastTurnAction();
                    }
                }
            }, 0, 400);
        }
    }
}