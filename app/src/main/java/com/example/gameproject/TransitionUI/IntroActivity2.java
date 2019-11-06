package com.example.gameproject.TransitionUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.MainGames.MainActivityLevelTwo;
import com.example.gameproject.R;

public class IntroActivity2 extends AppCompatActivity {
  private String username;
  private String characterName;
  private String backgroundColor;
  public static int life;
  public static int score;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intro2);
    username = getIntent().getStringExtra("username");
    characterName = getIntent().getStringExtra("character");
    backgroundColor = getIntent().getStringExtra("background");
    life = getIntent().getIntExtra("lifeKey",0);
    score = getIntent().getIntExtra("scoreKey",0);
  }

  public void StartGame(View view) {
    Intent intent = new Intent(getApplicationContext(), MainActivityLevelTwo.class);
    intent.putExtra("username", username);
    intent.putExtra("character", characterName);
    intent.putExtra("background", backgroundColor);
    intent.putExtra("lifeKey", life);
    intent.putExtra("scoreKey", score);
    startActivity(intent);
  }
}
