package com.example.gameproject.TransitionUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.MainGames.MainActivityLevelThree;
import com.example.gameproject.R;

public class IntroActivity3 extends AppCompatActivity {
    private String username;
    private String characterName;
    private String backgroundColor;
    private int life;
    private int score;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intro3);
    username = getIntent().getStringExtra("username");
    characterName = getIntent().getStringExtra("character");
    backgroundColor = getIntent().getStringExtra("background");
    life = getIntent().getIntExtra("lifeKey",0);
    score = getIntent().getIntExtra("scoreKey",0);
    Button startButton = (Button) findViewById(R.id.startButton);
    startButton.setOnClickListener(onclick);
  }

  View.OnClickListener onclick =
      new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = new Intent(IntroActivity3.this, MainActivityLevelThree.class);
          intent.putExtra("username", username);
          intent.putExtra("character", characterName);
          intent.putExtra("background", backgroundColor);
          intent.putExtra("lifeKey", life);
          intent.putExtra("scoreKey", score);
          startActivity(intent);
        }
      };
}
