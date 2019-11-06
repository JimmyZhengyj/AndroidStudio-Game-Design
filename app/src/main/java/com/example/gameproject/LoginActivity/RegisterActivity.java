package com.example.gameproject.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.gameproject.R;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

  EditText username;
  EditText password;
  Button register;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    username = findViewById(R.id.username);
    password = findViewById(R.id.password);
    register = findViewById(R.id.register);
    register.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.register) {
      final String username = this.username.getText().toString();
      final String password = this.password.getText().toString();
      UserAccount u = new UserAccount(username, password);
      saveUser(u);
      startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
  }

  public void saveUser(UserAccount u) {
    PrintWriter out = null;
    try {
      OutputStream outStream = openFileOutput(u.username + ".txt", Context.MODE_PRIVATE);
      out = new PrintWriter(outStream);
    } catch (FileNotFoundException i) {
      i.printStackTrace();
    }
    //        out.println(u.username);
    out.println(u.password);
    out.close();
  }

  //            try {
  //                String current = new java.io.File(".").getCanonicalPath();
  //                System.out.println(current);
  //            } catch (Exception i) {
  //                i.printStackTrace();
  //            }

  //            Response.Listener<String> responseListener = new Response.Listener<String>() {
  //                @Override
  //                public void onResponse(String response) {
  //                    try {
  //                        JSONObject jsonResponse = new JSONObject(response);
  //                        boolean success = jsonResponse.getBoolean("success");
  //                        if (success) {
  //                            startActivity(new Intent(getApplicationContext(),
  // LoginActivity.class));
  //                        } else {
  //                            AlertDialog.Builder builder = new
  // AlertDialog.Builder(RegisterActivity.this);
  //                            builder.setMessage("Register Failed")
  //                                    .setNegativeButton("Retry", null)
  //                                    .create()
  //                                    .show();
  //                        }
  //                    } catch (JSONException e) {
  //                        e.printStackTrace();
  //                    }
  //
  //                }
  //            };
  //            RegisterRequest registerRequest = new RegisterRequest(username, password,
  // responseListener);
  //            RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
  //            queue.add(registerRequest);

  //    @Override
  //    public void onClick(View v) {
  //        switch (v.getId()) {
  //            case R.id.register:
  //                break;
  //        }
  //    }
}
