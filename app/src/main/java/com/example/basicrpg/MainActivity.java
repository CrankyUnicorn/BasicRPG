package com.example.basicrpg;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
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

    private static final String TAG = "TEST_TAG";

    //private static final MainActivity currentMainActivity = new MainActivity();

    //public static MainActivity GetCurrentMainActivity() {return currentMainActivity;}

    Timer myTimer;

    TextView dungeonNameTitle;
    TextView dungeonLocationTitle;
    TextView dungeonRoomDescriptionTitle;
    ImageView dungeonImageView;
    ImageView dungeonImageViewFoe;
    ImageView dungeonImageViewChar;

    ImageView redbar;
    ImageView yellowbar;

    Button doorOneButton;
    Button doorTwoButton;
    Button doorThreeButton;
    Button doorFourButton;
    Button doorFiveButton;
    Button doorSixButton;

    TextView characterStatsTitle;
    TextView characterInventoryTitle;



    int imageDungeonEntrance =  R.drawable.nofoe00;

    MediaPlayer music;
    MediaPlayer sound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dungeonNameTitle = (TextView) findViewById(R.id.dungeonName);

        dungeonLocationTitle = (TextView) findViewById(R.id.dungeonLocation);
        dungeonRoomDescriptionTitle = (TextView) findViewById(R.id.roomDescription);

        dungeonRoomDescriptionTitle.setMovementMethod(new ScrollingMovementMethod());

        dungeonImageView = (ImageView) findViewById(R.id.dungeonImageView);
        dungeonImageViewFoe = (ImageView) findViewById(R.id.dungeonImageViewFoe);
        dungeonImageViewChar = (ImageView) findViewById(R.id.dungeonImageViewChar);

        redbar = (ImageView) findViewById(R.id.mainredbar);
        yellowbar = (ImageView) findViewById(R.id.mainyellowbar);

        doorOneButton = (Button) findViewById(R.id.buttonOne);
        doorTwoButton = (Button) findViewById(R.id.buttonTwo);
        doorThreeButton = (Button) findViewById(R.id.buttonThree);
        doorFourButton = (Button) findViewById(R.id.buttonFour);
        doorFiveButton = (Button) findViewById(R.id.buttonFive);
        doorSixButton = (Button) findViewById(R.id.buttonSix);

        characterStatsTitle = (TextView) findViewById(R.id.characterStats);
        characterInventoryTitle = (TextView) findViewById(R.id.characterInventory);

        characterInventoryTitle.setMovementMethod(new ScrollingMovementMethod());

        outputContent();

        PlayMusic();



    }


	//########## BUTTON PUSH #######################
    public void buttonOneClick(View view){//############# ACTIONS ##############
        UserInterfaceIOHandler.GetUserInterfaceIOHandler().SelectedActionButton(String.valueOf(doorOneButton.getText()));
        outputContent();
        CheckIfDead();
    }


    public void buttonTwoClick(View view){
        ButtonSound();
        UserInterfaceIOHandler.GetUserInterfaceIOHandler().SelectedDoorButton(0);
        outputContent();
        CheckIfDead();
    }

    public void buttonThreeClick(View view){
        ButtonSound();
        UserInterfaceIOHandler.GetUserInterfaceIOHandler().SelectedDoorButton(1);
        outputContent();
        CheckIfDead();
    }

    public void buttonFourClick(View view){//############# OPTIONS 2##############
        UserInterfaceIOHandler.GetUserInterfaceIOHandler().SelectedActionButton(String.valueOf(doorFourButton.getText()));
        outputContent();
        CheckIfDead();
    }

    public void buttonFiveClick(View view){
        ButtonSound();
        UserInterfaceIOHandler.GetUserInterfaceIOHandler().SelectedDoorButton(2);
        outputContent();
        CheckIfDead();
    }

    public void buttonSixClick(View view){
        ButtonSound();
        UserInterfaceIOHandler.GetUserInterfaceIOHandler().SelectedDoorButton(3);
        outputContent();
        CheckIfDead();
    }
	



	//######### OUTPUT ###########
    private void outputContent(){

        //dungeon stats
        dungeonNameTitle.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonNameTitle());

        dungeonLocationTitle.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonLocationTitle());
        dungeonRoomDescriptionTitle.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonRoomDescriptionTitle());

        //image
        dungeonImageView.setImageResource(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonRoomImage());
        dungeonImageViewFoe.setImageResource(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonRoomImageFoe());
        dungeonImageViewChar.setImageResource(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonRoomImageChar());

        //bars
        redbar.getLayoutParams().width = (int)((650/100)*Player.GetCurrentPlayer().GetPlayerHealth()) ;
        redbar.getLayoutParams().height = 22;
        redbar.requestLayout();
        yellowbar.getLayoutParams().width = (int)((650/100)*Player.GetCurrentPlayer().GetPlayerSanity()) ;
        yellowbar.getLayoutParams().height = 22;
        yellowbar.requestLayout();

        //buttons
        doorOneButton.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetACtivityButtonTitle(0));

        doorTwoButton.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDoorButtonTitle(0));

        doorThreeButton.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDoorButtonTitle(1));

        doorFourButton.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetACtivityButtonTitle(1));

        doorFiveButton.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDoorButtonTitle(2));

        doorSixButton.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDoorButtonTitle(3));

        //player stats
        characterStatsTitle.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetCharacterStatsTitle());
        characterInventoryTitle.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetCharacterInventoryTitle());
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
            } music.start();

        } catch(Exception e) { e.printStackTrace(); }

    }

    public void ButtonSound() {

        int doorSound = Util.GenerateNumberBetween(0,4);
        int soundRef = 0;

        switch(doorSound){

            case 0: soundRef= R.raw.dooropen01; break;
            case 1: soundRef= R.raw.dooropen02; break;
            case 2: soundRef= R.raw.dooropen03; break;
            case 3: soundRef= R.raw.dooropen04; break;
            default: break;
        }

        sound = MediaPlayer.create(this, soundRef);

        try {
            if (sound.isPlaying()) {
                sound.stop();
                sound.release();
                sound = MediaPlayer.create(this, soundRef);
            } sound.start();
        } catch(Exception e) { e.printStackTrace(); }
    }

	private void StopAllSounds(){
        try {
            if (sound.isPlaying()) {
                sound.stop();
                sound.release();
            }
        }catch (Exception e){}

        try {
            if (music.isPlaying()) {
                music.stop();
                music.release();
            }
        }catch (Exception e){}

    }

    //###END GAME METHOD###
    public void EndGame(){

        StopAllSounds();
        startActivity(new Intent(MainActivity.this, EndActivity.class));

    }

    //###CHECK DEAD###
    private void CheckIfDead() {
        if (            Player.GetCurrentPlayer().GetPlayerHealth() == 0) {
            EndGame();
        }
    }



    //############################ SOMETHING ELSE ##########################################
    public void rollButtonClick(View view) {

        int rolledNumber = rollDice();

        //imageView.setImageResource(imageDiceDrawables [rolledNumber]);

        rolledNumber++;

        if (rolledNumber == 1) {
            //crititical fail

        } else if (rolledNumber == 6) {
            //critical good

        } else{
            //depends on the value but lets assume is nor good nor bad
        }

    }

    private int rollDice() {

        Random random = new Random();
        int randomNumber = random.nextInt(6);
        return randomNumber;

    }


    private void enableButtons(boolean isEnabled) {
        doorOneButton.setEnabled(isEnabled);
        doorTwoButton.setEnabled(isEnabled);
        doorThreeButton.setEnabled(isEnabled);
    }


    /*
    public void computerTurn() {


        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {


                //disable the buttons while computer is playing
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        enableButtons(false);
                    }
                });

                //roll dice for comp
                int computerRolledNumber = rollDice();

                //update the image on the ui thread
                final int finalComputerRolledNumber = computerRolledNumber;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(drawables[finalComputerRolledNumber]);
                    }
                });

                computerRolledNumber++;

                //if computer looses, set turnSCore to 0 and enable buttons for user's turn
                if (computerRolledNumber == 1) {
                    computerTurnScore = 0;
                    labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore + userTurnScoreLabel + userTurnScore +
                            "\n computer rolled a one and lost it's chance";


                    //enable buttons, on ui thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            enableButtons(true);
                        }
                    });

                    //update the label
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            label.setText(Html.fromHtml(labelText));
                        }
                    });

                    //cancel the timer, this is exiting out of function
                    myTimer.cancel();

                }

                //if not 1, add the score to turn score
                else {
                    computerTurnScore += computerRolledNumber;
                    labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore + userTurnScoreLabel + userTurnScore
                            + "\nComputer rolled a " + computerRolledNumber
                            + compTurnScoreLabel + computerTurnScore;

                    //update the label
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            label.setText(Html.fromHtml(labelText));
                        }
                    });

                    //if the turn score is greater than 20 stop rolling and hold(update the comp score and cancel timer)
                    if (computerTurnScore > 20) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                enableButtons(true);
                            }
                        });

                        computerOverallScore += computerTurnScore;
                        computerTurnScore = 0;
                        labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore + "\nComputer holds";

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                label.setText(Html.fromHtml(labelText));
                            }
                        });


                        myTimer.cancel();


                    }

                }

            }

        }, 0, 2000);


    }
*/
}