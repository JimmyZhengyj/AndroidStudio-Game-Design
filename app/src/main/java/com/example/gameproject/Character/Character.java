package com.example.gameproject.Character;

import android.widget.FrameLayout;
import android.widget.ImageView;

/*
Character is the character the player controls. It has three subclasses that are the three
different characters the player can choose
 */
public abstract class Character {
  public ImageView character;
  private String name;
  public float character_x;
  public float character_y;
  public int life;
  public boolean goingRight = true;

  /*
  Character is an abstract class, which should not be initialized
   **/
  public Character(ImageView character) {
    this.character = character;
  }

  /*
  determine how the character moves if the player press a button in Game1
   */
  public void move(
      FrameLayout frame, boolean act_up, boolean act_down, boolean act_left, boolean act_right) {
    character_x = character.getX();
    character_y = character.getY();

    if (act_up) {
      character_y -= 30;
    }
    if (act_down) {
      character_y += 30;
    }
    if (act_left) {
      character_x -= 30;
    }
    if (act_right) {
      character_x += 30;
    }

    if (character_y < 0) {
      character_y = 0;
    }
    if (character_y > frame.getHeight() - character.getHeight()) {
      character_y = frame.getHeight() - character.getHeight();
    }

    if (character_x < 0) {
      character_x = 0;
    }
    if (character_x > frame.getWidth() - character.getWidth()) {
      character_x = frame.getWidth() - character.getWidth();
    }

    character.setX(character_x);
    character.setY(character_y);
  }

  /*
  return true if the character hit any other objects, and false otherwise
  */
  public boolean hit(ImageView image) {
    // center of ImageView
    int image_x = (int) image.getX() + image.getWidth() / 2;
    int image_y = (int) image.getY() + image.getHeight() / 2;
    if (character_x <= image_x
        && image_x <= character_x + character.getWidth()
        && character_y <= image_y
        && image_y <= character_y + character.getHeight()) {
      return true;
    } else {
      return false;
    }
  }

  /*
  return true if character hit other objects, and remove the object being hit in Game1
   */
  public boolean hit_affect(ImageView image) {
    if (this.hit(image)) {
      image.setX(-100);
      image.setY(-100);
      return true;
    } else {
      return false;
    }
  }

  /*
  make the characters constantly moving from left to right in Game3
   */
  //    public void constantly_moving(FrameLayout frame){
  //        if(goingRight){
  //            character_x += 20;
  //            if(character_x > frame.getWidth()- character.getWidth()){
  //                character_x = frame.getWidth()- character.getWidth();
  //                goingRight = false;
  //            }
  //        }else{
  //            character_x -= 20;
  //            if(character_x < 0){
  //                character_x = 0;
  //                goingRight = true;
  //            }
  //        }
  //    }
}
