package com.example.gameproject.TransitionUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.R;

public class TransitionActivity1 extends AppCompatActivity {

  private String username;
  private String characterName;
  private String backgroundColor;
  private int life;
  private int score;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transition1);
    username = getIntent().getStringExtra("username");
    characterName = getIntent().getStringExtra("character");
    backgroundColor = getIntent().getStringExtra("background");
    life = getIntent().getIntExtra("lifeKey",0);
    score = getIntent().getIntExtra("scoreKey",0);


  }


  public void NextGame(View view) {
    Intent intent1 = new Intent(getApplicationContext(), IntroActivity2.class);
    intent1.putExtra("username", username);
    intent1.putExtra("character", characterName);
    intent1.putExtra("background", backgroundColor);
    intent1.putExtra("lifeKey", life);
    intent1.putExtra("scoreKey",score);
    startActivity(intent1);
  }
}
