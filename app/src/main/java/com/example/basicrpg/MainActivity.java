package com.example.basicrpg;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;

        import android.text.Html;
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

    Timer myTimer;

    TextView dungeonNameTitle;
    TextView dungeonLocationTitle;
    TextView dungeonRoomDescriptionTitle;
    ImageView dungeonImageView;

    Button doorOneButton;
    Button doorTwoButton;
    Button doorThreeButton;

    TextView characterStatsTitle;
    TextView characterInventoryTitle;

    int[] diceDrawables = { R.drawable.dice1,
                        R.drawable.dice2,
                        R.drawable.dice3,
                        R.drawable.dice4,
                        R.drawable.dice5,
                        R.drawable.dice6
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dungeonNameTitle = (TextView) findViewById(R.id.dungeonName);

        dungeonLocationTitle = (TextView) findViewById(R.id.dungeonLocation);
        dungeonRoomDescriptionTitle = (TextView) findViewById(R.id.roomDescription);

        dungeonImageView = (ImageView) findViewById(R.id.dungeonImageView);

        doorOneButton = (Button) findViewById(R.id.buttonOne);
        doorTwoButton = (Button) findViewById(R.id.buttonTwo);
        doorThreeButton = (Button) findViewById(R.id.buttonThree);

        characterStatsTitle = (TextView) findViewById(R.id.characterStats);
        characterInventoryTitle = (TextView) findViewById(R.id.characterInventory);
    }

    public void buttonOneClick(View view){
        UserInterfaceIOHandler.GetUserInterfaceIOHandler().SelectedDoorButton(0);
        outputContent();
    }
    public void buttonTwoClick(View view){
        UserInterfaceIOHandler.GetUserInterfaceIOHandler().SelectedDoorButton(1);
        outputContent();
    }
    public void buttonThreeClick(View view){
        UserInterfaceIOHandler.GetUserInterfaceIOHandler().SelectedDoorButton(2);
        outputContent();
    }

    private void outputContent(){

        dungeonNameTitle.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonNameTitle());

        dungeonLocationTitle.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonLocationTitle());
        dungeonRoomDescriptionTitle.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDungeonRoomDescriptionTitle());

        dungeonImageView.setImageResource(diceDrawables[1]);

        doorOneButton.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDoorOneButtonTitle());
        doorTwoButton.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDoorTwoButtonTitle());
        doorThreeButton.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetDoorThreeButtonTitle());

        characterStatsTitle.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetCharacterStatsTitle());
        characterInventoryTitle.setText(UserInterfaceIOHandler.GetUserInterfaceIOHandler().GetCharacterInventoryTitle());
    }

    public void rollButtonClick(View view) {

        int rolledNumber = rollDice();

        //imageView.setImageResource(diceDrawables [rolledNumber]);

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