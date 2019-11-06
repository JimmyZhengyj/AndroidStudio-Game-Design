package com.example.gameproject.MainGames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gameproject.Character.Character;
import com.example.gameproject.Character.Eevee;
import com.example.gameproject.Character.Psyduck;
import com.example.gameproject.R;
import com.example.gameproject.TransitionUI.AllFailActivity;
import com.example.gameproject.TransitionUI.TransitionActivity1;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivityLevelOne extends AppCompatActivity implements View.OnTouchListener {

    private String username;
    private ImageView charView;
    public Character character;

    private FrameLayout frame;
    private TextView Scores;
    private TextView Life;

    private ImageView Pokeball, Masterball, Heal, Bomb;
    private int bomb_x, bomb_y;
    private Button PauseButton;

    private int score = 0;
    private int life = 4;

    private String characterName;
    private String backgroundColor;
    private String levelOfDifficulty;

    private boolean act_up, act_down, act_right, act_left;
    private boolean pause_signal = false;

    private Timer timer = new Timer();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game1);
        /*
    set up all the items and statistics in the game
     */
        username = getIntent().getStringExtra("username");
        frame = findViewById(R.id.frame);
        Scores = findViewById(R.id.text0);
        Life = findViewById(R.id.text1);
        Pokeball = findViewById(R.id.pokeball);
        Masterball = findViewById(R.id.masterball);
        Heal = findViewById(R.id.heal);
        Bomb = findViewById(R.id.bomb);
        PauseButton = findViewById(R.id.pausebutton);

    /*
    set the background color of the game according to selection
     */
        FrameLayout view = findViewById(R.id.frame);
        backgroundColor = getIntent().getStringExtra("background");
        if (backgroundColor.equals("white")) {
            view.setBackgroundColor(Color.WHITE);
        } else if (backgroundColor.equals("blue")) {
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
        }

    /*
    create the character according to the player's choice
     */
        characterName = getIntent().getStringExtra("character");
        charView = findViewById(R.id.character);
//    charView.setX((frame.getWidth()-charView.getWidth())/2);
//    charView.setY((frame.getHeight()-charView.getHeight())/2);
        if (characterName.equals("eevee")) {
            charView.setImageResource(R.drawable.eevee);
            charView.setX(200);
            charView.setY(200);
            character = new Eevee(charView);
        } else if (characterName.equals("psyduck")) {
            charView.setImageResource(R.drawable.psyduck);
            charView.setX(200);
            charView.setY(200);
            character = new Psyduck(charView);
        }



    /*
    show the initial view of the statistics
     */
        Scores.setText("Score : 0");
        levelOfDifficulty = getIntent().getStringExtra("difficultyLevel");
        if (levelOfDifficulty.equals("simple")) {
            life = 6;
            Life.setText("Life : " + life);
        } else if (levelOfDifficulty.equals("difficult")) {
            life = 4;
            Life.setText("Life : " + life);
        }

    /*
    get all the controller buttons
     */
        findViewById(R.id.upbutton).setOnTouchListener(this);
        findViewById(R.id.downbutton).setOnTouchListener(this);
        findViewById(R.id.rightbutton).setOnTouchListener(this);
        findViewById(R.id.leftbutton).setOnTouchListener(this);

    /*
    decide how and how often the character move
     */

        setTimer("Pokeball", 3000);
        setTimer("Masterball", 9000);
        setTimer("Heal", 15000);
        setTimer("character", 20);
        setTimer("bomb", 20);
    }


    public void setTimer(final String s, int period) {
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        switch (s) {
                                            case "Pokeball":
                                                itemAppear(Pokeball);
                                                break;
                                            case "Masterball":
                                                itemAppear(Masterball);
                                                break;
                                            case "Heal":
                                                itemAppear(Heal);
                                                break;
                                            case "character":
                                                character.move(frame, act_up, act_down, act_left, act_right);
                                                score_life_count();
                                                break;
                                            case "bomb":
                                                bomb_move();
                                                break;
                                        }
                                    }
                                });
                    }
                }, 0, period
        );
    }

    /*
    return true if the player press the controller button
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            switch (view.getId()) {
                case R.id.upbutton:
                    act_up = true;
                    break;
                case R.id.downbutton:
                    act_down = true;
                    break;
                case R.id.leftbutton:
                    act_left = true;
                    break;
                case R.id.rightbutton:
                    act_right = true;
                    break;
            }
        } else {
            act_up = false;
            act_down = false;
            act_right = false;
            act_left = false;
        }
        return true;
    }

  /*
  set up the items in random place on the screen
   */

    private void itemAppear(ImageView item) {
        int x = (int) Math.floor(Math.random() * (frame.getWidth() - Pokeball.getWidth()));
        int y = (int) Math.floor(Math.random() * (frame.getHeight() - Pokeball.getHeight()));
        item.setX(x);
        item.setY(y);
    }


    public void bomb_move() {
        bomb_y += 10;
        if (bomb_y > frame.getHeight()) {
            bomb_x = (int) Math.floor(Math.random() * (frame.getWidth() - Bomb.getWidth()));
            bomb_y = -40;
        }
        Bomb.setX(bomb_x);
        Bomb.setY(bomb_y);
    }

    /*
    count the score and life after each hit
     */
    public void score_life_count() {
        if (character.hit_affect(Pokeball)) {
            score += 10;
        }
        if (character.hit_affect(Masterball)) {
            score += 20;
        }
        if (character.hit_affect(Heal)) {
            if (life < 3) life += 1;
        }
        if (character.hit_affect(Bomb)) {
            bomb_x = (int) Math.floor(Math.random() * (frame.getWidth() - Bomb.getWidth()));
            bomb_y = -40;
            Bomb.setX(bomb_x);
            Bomb.setY(bomb_y);
            life -= 1;
        }
        Scores.setText("Score: " + score);
        Life.setText("Life: " + life);
        if (score >= 100) {
            timer.cancel();
            timer = null;
            Intent intent = new Intent(getApplicationContext(), TransitionActivity1.class);
            intent.putExtra("username", username);
            intent.putExtra("character", characterName);
            intent.putExtra("background", backgroundColor);
            intent.putExtra("lifeKey", life);
            intent.putExtra("scoreKey", score);
            startActivity(intent);
        }
        if (life <= 0) {
            timer.cancel();
            timer = null;
            Intent intentFail = new Intent(MainActivityLevelOne.this, AllFailActivity.class);
            startActivity(intentFail);
        }
    }

    /*
    pause the game if the player press Pause button, and resume the game if the player press again
     */
    public void Pause(View view) {
        if (!pause_signal) {
            pause_signal = true;
            timer.cancel();
            timer = null;
            PauseButton.setText("Resume");
        } else {
            pause_signal = false;
            PauseButton.setText("Pause");
            timer = new Timer();
            setTimer("Pokeball", 3000);
            setTimer("Masterball", 9000);
            setTimer("Heal", 15000);
            setTimer("character", 20);
            setTimer("bomb", 20);
        }
    }
}
