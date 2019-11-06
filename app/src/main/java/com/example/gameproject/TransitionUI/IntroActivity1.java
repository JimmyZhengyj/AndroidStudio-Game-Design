package com.example.gameproject.TransitionUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gameproject.MainGames.MainActivityLevelOne;
import com.example.gameproject.R;

public class IntroActivity1 extends AppCompatActivity {
  private String username;
  private String characterName;
  private String backgroundColor;
  private String levelOfDifficulty;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intro1);
    username = getIntent().getStringExtra("username");
    characterName = getIntent().getStringExtra("character");
    backgroundColor = getIntent().getStringExtra("background");
    levelOfDifficulty = getIntent().getStringExtra("difficultyLevel");
  }

  public void StartGame(View view) {
    Intent intent = new Intent(getApplicationContext(), MainActivityLevelOne.class);
    intent.putExtra("username", username);
    intent.putExtra("character", characterName);
    intent.putExtra("background", backgroundColor);
    intent.putExtra("difficultyLevel", levelOfDifficulty);
    startActivity(intent);
  }
}
