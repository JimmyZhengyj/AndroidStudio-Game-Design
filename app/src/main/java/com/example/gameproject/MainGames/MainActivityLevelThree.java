package com.example.gameproject.MainGames;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivityLevelThree extends AppCompatActivity {
  private com.example.gameproject.MainGames.dogView dogView;
  private Handler handler = new Handler();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    String  characterName = getIntent().getStringExtra("character");
    String  username = getIntent().getStringExtra("username");
    int  life = getIntent().getIntExtra("lifeKey",0);
    int  score = getIntent().getIntExtra("scoreKey",0);
    dogView = new dogView(this, username, characterName, life, score);
    setContentView(dogView);

    Timer timer = new Timer();
    timer.schedule(
        new TimerTask() {
          @Override
          public void run() {
            handler.post(
                new Runnable() {
                  @Override
                  public void run() {
                    dogView.invalidate();
                  }
                });
          }
        },
        0,
        30);
  }
}
