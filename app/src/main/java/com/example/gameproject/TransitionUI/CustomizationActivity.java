package com.example.gameproject.TransitionUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.R;

public class CustomizationActivity extends AppCompatActivity {

  private RadioGroup LevelGroup, CharacterGroup, BgColorGroup;
  private Button buttonStart;

  private String characterName;
  private String backgroundColor;
  private String levelOfDifficulty;
  private String username;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_customization);
    buttonStart = findViewById(R.id.startButton);
    username = getIntent().getStringExtra("username");

    buttonStart.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              CharacterGroup = findViewById(R.id.charGroup);
              if(CharacterGroup.getCheckedRadioButtonId() == findViewById(R.id.charEevee).getId()){
                  characterName = "eevee";
              }else{
                  characterName = "psyduck";
              }

              BgColorGroup = findViewById(R.id.bgGroup);
              if(BgColorGroup.getCheckedRadioButtonId() == findViewById(R.id.bgColorWhite).getId()){
                  backgroundColor = "white";
              }else{
                  backgroundColor = "blue";
              }

              LevelGroup = findViewById(R.id.levelGroup);
              if(LevelGroup.getCheckedRadioButtonId() == findViewById(R.id.levelSimple).getId()){
                  levelOfDifficulty = "simple";
              }else{
                  levelOfDifficulty = "difficult";
              }


            Intent intent = new Intent(CustomizationActivity.this, IntroActivity1.class);
            intent.putExtra("username", username);
            intent.putExtra("character", characterName);
            intent.putExtra("background", backgroundColor);
            intent.putExtra("difficultyLevel", levelOfDifficulty);
            startActivity(intent);
          }
        });

  }
}
