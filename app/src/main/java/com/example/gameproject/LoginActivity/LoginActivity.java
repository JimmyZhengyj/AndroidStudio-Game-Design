package com.example.gameproject.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gameproject.TransitionUI.CustomizationActivity;
import com.example.gameproject.R;

import java.io.IOException;
import java.util.Scanner;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

  Button sign_up;
  Button sign_in;
  EditText username;
  EditText password;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    username = findViewById(R.id.username);
    password = findViewById(R.id.password);
    sign_up = findViewById(R.id.sign_up);
    sign_in = findViewById(R.id.sign_in);
    sign_up.setOnClickListener(this);
    sign_in.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.sign_in:
        String inputUsername = username.getText().toString();
        String inputPassword = password.getText().toString();
        String savedPassword = readUser(inputUsername);
        if (inputPassword.equals(savedPassword)) {
          //                if (true) {
          Intent intent = new Intent(getApplicationContext(), CustomizationActivity.class);
          intent.putExtra("username", inputUsername);
          startActivity(intent);
        } else {
          startActivity(new Intent(getApplicationContext(), LoginFailActivity.class));
        }

        break;
      case R.id.sign_up:
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        break;
    }
  }

  public String readUser(String username) {
    String password = null;
    try (Scanner scanner = new Scanner(openFileInput(username + ".txt"))) {
      password = scanner.nextLine();
    } catch (IOException i) {
      i.printStackTrace();
    }
    return password;
  }
}
