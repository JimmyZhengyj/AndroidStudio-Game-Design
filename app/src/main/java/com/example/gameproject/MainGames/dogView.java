package com.example.gameproject.MainGames;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import android.view.MotionEvent;
import android.view.View;

import com.example.gameproject.R;
import com.example.gameproject.TransitionUI.AllFailActivity;
import com.example.gameproject.TransitionUI.AllWinActivity;


public class dogView extends View {
    private Bitmap dog;
    private Bitmap background;


    private int dogX = 30;
    private int dogY = 250;
    private int dogSpeed;
    private int minDogY = 30;
    private int maxDogY;

    private int canvasWidth;
    private int canvasHeight;

    private boolean touch = false;


    private String username;
    private String characterName;
    private Paint score = new Paint();
    private int mark;
    int life;

    private Bitmap Pokeball;
    private Bitmap Masterball;
    private Bitmap Bomb;
    private Bitmap Eevee;

    private float pokeball_x, pokeball_y, pokeball_speed;
    private float masterball_x, masterball_y, masterball_speed;

    private float bomb_x, bomb_y, bomb_speed;


    double initial = 0.6;

    double master_ratio = 0.6;
    double bomb_ratio = 0.6;


    public dogView(Context context, String username, String characterName, int life, int mark) {
        super(context);
        this.username = username;
        this.characterName = characterName;
        this.mark = mark;
        this.life = life;


        if (characterName.equals("eevee")) {
            dog = BitmapFactory.decodeResource(getResources(), R.drawable.eevee);
        } else if (characterName.equals("psyduck")) {
            dog = BitmapFactory.decodeResource(getResources(), R.drawable.psyduck);
        }

        dog = Bitmap.createScaledBitmap(dog, 100, 100, false);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.sky);


        Pokeball = BitmapFactory.decodeResource(getResources(), R.drawable.pokeball);
        Pokeball = Bitmap.createScaledBitmap(Pokeball, 80, 80, false);

        Masterball = BitmapFactory.decodeResource(getResources(), R.drawable.masterball);
        Masterball = Bitmap.createScaledBitmap(Masterball, 80, 80, false);

        Bomb = BitmapFactory.decodeResource(getResources(), R.drawable.bombooo);
        Bomb = Bitmap.createScaledBitmap(Bomb, 80, 80, false);

        Eevee = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        Eevee = Bitmap.createScaledBitmap(Eevee, 200, 200, false);

        score.setColor(Color.WHITE);
        score.setTypeface(Typeface.DEFAULT_BOLD);
        score.setTextSize(60);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();


        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawBitmap(Eevee, canvasWidth - 200, (canvasHeight - 200) / 2, null);


        canvas.drawText("Honey, your score is: " + mark + ", life is " + life, 80, 60, score);

        maxDogY = canvasHeight - dog.getHeight() * 2 + 100;
        dogY = dogY + dogSpeed;

        if (dogY < minDogY) {
            dogY = minDogY;
        }
        if (dogY > maxDogY) {
            dogY = maxDogY;
        }
        dogSpeed += 1;

        pokeball_speed = 15;
        pokeball_x -= pokeball_speed;

        double y_direction = pokeball_speed * initial;
        pokeball_y += y_direction;
        if (pokeball_x <= 0) {
            pokeball_x = canvasWidth + 10;
            pokeball_y = (canvasHeight - 200) / 2 + 100;
            double transfer = Math.random();
            if (transfer <= 0.5) {
                initial = -Math.random();
            } else {
                initial = Math.random();
            }
        }
        if (hit(pokeball_x, pokeball_y)) {
            mark += 15;
            if (mark >= 150) {
                Intent intent = new Intent(getContext(), AllWinActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("lifeKey", life);
                intent.putExtra("scoreKey", mark);
                getContext().startActivity(intent);
            }
            pokeball_x = canvasWidth + 10;
            pokeball_y = (canvasHeight - 200) / 2 + 100;
            double transfer = Math.random();
            if (transfer <= 0.5) {
                initial = -Math.random();
            } else {
                initial = Math.random();
            }
        }

        masterball_speed = 10;
        masterball_x -= masterball_speed;

        double master_y_dir = masterball_speed * master_ratio;
        masterball_y += master_y_dir;
        if (masterball_x <= 0) {
            masterball_x = canvasWidth + 10;
            masterball_y = (canvasHeight - 200) / 2 + 100;
            double transfer1 = Math.random();
            if (transfer1 <= 0.5) {
                master_ratio = -Math.random();
            } else {
                master_ratio = Math.random();
            }
        }
        if (hit(masterball_x, masterball_y)) {
            mark += 10;
            if (mark >= 150) {
                Intent intent = new Intent(getContext(), AllWinActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("lifeKey", life);
                intent.putExtra("scoreKey", mark);
                getContext().startActivity(intent);
            }
            masterball_x = canvasWidth + 10;
            masterball_y = (canvasHeight - 200) / 2 + 100;
            double transfer1 = Math.random();
            if (transfer1 <= 0.5) {
                master_ratio = -Math.random();
            } else {
                master_ratio = Math.random();
            }
        }

        bomb_speed = 10;
        bomb_x -= bomb_speed;
        bomb_speed += 3;

        double bomb_y_dir = bomb_speed * bomb_ratio;
        bomb_y += bomb_y_dir;
        if (bomb_x <= 0) {
            bomb_x = canvasWidth + 10;
            bomb_y = (canvasHeight - 200) / 2 + 100;
            double transfer2 = Math.random();
            if (transfer2 <= 0.5) {
                bomb_ratio = -Math.random();
            } else {
                bomb_ratio = Math.random();
            }
        }
        if (hit(bomb_x, bomb_y)) {
            life -= 1;
            if (life <= 0) {
                Intent intent = new Intent(getContext(), AllFailActivity.class);

                getContext().startActivity(intent);
            }
            bomb_x = canvasWidth + 10;
            bomb_y = (canvasHeight - 200) / 2 + 100;
            double transfer2 = Math.random();
            if (transfer2 <= 0.5) {
                bomb_ratio = -Math.random();
            } else {
                bomb_ratio = Math.random();
            }
        }


        canvas.drawBitmap(dog, dogX, dogY, null);

        canvas.drawBitmap(Bomb, bomb_x, bomb_y, null);

        canvas.drawBitmap(Pokeball, pokeball_x, pokeball_y, null);

        canvas.drawBitmap(Masterball, masterball_x, masterball_y, null);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch = true;
            dogSpeed = -17;
        }
        return true;
    }

    private boolean hit(float x, float y) {

        return dogX <= x && x <= dogX + 100 && dogY <= y && y <= dogY + 100;
    }
}
