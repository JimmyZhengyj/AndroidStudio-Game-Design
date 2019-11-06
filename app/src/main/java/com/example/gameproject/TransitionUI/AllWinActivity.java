package com.example.gameproject.TransitionUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gameproject.MainGames.MainActivityLevelTwo;
import com.example.gameproject.R;

public class AllWinActivity extends AppCompatActivity {
    private Button restartBtn;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_win);
        String username = getIntent().getStringExtra("username");
        int life = getIntent().getIntExtra("lifeKey",0);
        int score = getIntent().getIntExtra("scoreKey",0);
        text = findViewById(R.id.textView13);
        text.setText("Congratulations," + username + "!\n" +
                "You have passed all the three levels ! \n " +
                "Your Score is: " + score + "\n" +
                "Your life remaining is: " + life + "\n" +
                "You also beat the final boss in third level! \n Press RESTART button if you want to play it again!");

        restartBtn = findViewById(R.id.restartBtn);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllWinActivity.this, CustomizationActivity.class);
                startActivity(intent);
            }
        });

    }
}
