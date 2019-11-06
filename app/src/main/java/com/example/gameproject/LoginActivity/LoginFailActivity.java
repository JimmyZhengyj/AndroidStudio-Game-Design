package com.example.gameproject.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.gameproject.R;

public class LoginFailActivity extends AppCompatActivity implements View.OnClickListener {

  Button retry;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login_fail);
    retry = findViewById(R.id.retry);
    retry.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.retry) {
      startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
  }
}
