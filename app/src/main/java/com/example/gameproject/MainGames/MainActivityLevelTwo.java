package com.example.gameproject.MainGames;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.gameproject.Character.Character;
import com.example.gameproject.Character.Eevee;
import com.example.gameproject.Character.Psyduck;
import com.example.gameproject.R;
import com.example.gameproject.TransitionUI.AllFailActivity;
import com.example.gameproject.TransitionUI.TransitionActivity2;

import java.util.Random;

public class MainActivityLevelTwo extends AppCompatActivity {

  private String username;
  private ImageView charView;
  public Character character;

  //    private ImageView Eevee;
  //    public Eevee eevee;
  //    private ImageView Psyduck;
  //    public Psyduck psyduck;
  //
  private TextView Scores;
  private TextView Life;
  private Button confirmbtn;

  private int score;
  private int life;

  // the times that the player guessed.
  private int guesstime = 0;

  private String characterName;
  private String backgroundColor;

  //
  ////    private Timer timer = new Timer();
  ////    private Handler handler = new Handler();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_game2);

    username = getIntent().getStringExtra("username");
    /*
    create the character according to the player's choice
     */
    characterName = getIntent().getStringExtra("character");
    charView = (ImageView) findViewById(R.id.character);
    charView.setImageResource(R.drawable.eevee);
    if (characterName.equals("eevee")) {
      charView.setImageResource(R.drawable.eevee);
      character = new Eevee(charView);
    } else if (characterName.equals("psyduck")) {
      charView.setImageResource(R.drawable.psyduck);
      character = new Psyduck(charView);
    }

    /*  Set the background color based on the user's choice from customization page.
     */
    ConstraintLayout layout = findViewById(R.id.layout);
    backgroundColor = getIntent().getStringExtra("background");
    if (backgroundColor.equals("white")) {
      layout.setBackgroundColor(Color.WHITE);
    } else if (backgroundColor.equals("blue")) {
      layout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
    }

    // set the answer of the number.
    Random ran = new Random();
    final int n = ran.nextInt(6) + 5;

    //    /*
    //    set up all the items and statistics in the game
    //     */
    Scores = findViewById(R.id.text0);
    Life = findViewById(R.id.text1);
    confirmbtn = findViewById(R.id.ConfirmBtn);

    /*
    show the initial view of the statistics
     */
    score = getIntent().getIntExtra("scoreKey",0);
    Scores.setText("Score : " + score);
    life = getIntent().getIntExtra("lifeKey", 3);
    Life.setText("Life : " + life);

    /*
    set up the onClick.
     */
    confirmbtn.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            EditText guessednum = (EditText) findViewById(R.id.InputText);
            TextView response = (TextView) findViewById(R.id.Guesstext);
            int num1 = Integer.parseInt(guessednum.getText().toString());
            if (num1 == n) {
              response.setText("Congratulations! You guessed the right number!");
              if (guesstime == 0) {
                score += 50;
                Scores.setText("Score : " + score);
              } else if (guesstime == 1) {
                score += 35;
                Scores.setText("Score : " + score);
              } else if (guesstime == 2) {
                score += 15;
                Scores.setText("Score : " + score);
              } else if (guesstime > 2) {
                score += 10;
                Scores.setText("Score : " + score);
              }

              Intent intent = new Intent(getApplicationContext(), TransitionActivity2.class);
              intent.putExtra("username", username);
              intent.putExtra("character", characterName);
              intent.putExtra("background", backgroundColor);
              intent.putExtra("lifeKey", life);
              intent.putExtra("scoreKey", score);
              startActivity(intent);
            } else if (num1 < 5 | num1 > 10) {
              response.setText(
                  "Oops, your number is out of range. Please input the number between 5 and 10.");
            } else if (num1 < n) {
              response.setText("Sorry, your number is too small.");
              guesstime += 1;
              life -= 1;
              Life.setText("Life: " + life);
            } else if (num1 > n) {
              response.setText("Sorry, your number is too big.");
              guesstime += 1;
              life -= 1;
              Life.setText("Life: " + life);
            }

            if (life <= 0) {
              Intent intent = new Intent(getApplicationContext(), AllFailActivity.class);
              startActivity(intent); // overall ending of the game
            }
          }
        });
  }
}
