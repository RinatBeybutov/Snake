package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class FoodEat extends FoodParent{
    private Vector2 tmp;
    private int CountEat=0, CountDoubleFood=0;
    private int ChanceDoubleFood = 700;

    public FoodEat(String nameTexture,float x,float y) {
        super(nameTexture,x,y);
        INTERVAL_BETWEEN_LIGHT=5;
        active = true;
        tmp = new Vector2(x,y);
    }
    //я съем тебя!

    @Override
    public void render(SpriteBatch batch, BitmapFont font) {
        if(active) {
            if (Counter > 0 && Counter < INTERVAL_BETWEEN_LIGHT) {
                batch.draw(foodTexture, position.x, position.y, 16, 16, 0, 0, 0.22f, 1);
            }

            if (Counter > INTERVAL_BETWEEN_LIGHT && Counter < 2 * INTERVAL_BETWEEN_LIGHT) {
                batch.draw(foodTexture, position.x, position.y, 16, 16, 0.48f, 0, 0.24f, 1);
            }

            if (Counter > 2 * INTERVAL_BETWEEN_LIGHT && Counter < 3 * INTERVAL_BETWEEN_LIGHT) {
                batch.draw(foodTexture, position.x, position.y, 16, 16, 0.50f, 0, 0.73f, 1);
            }
            if (Counter > 3 * INTERVAL_BETWEEN_LIGHT && Counter < 4 * INTERVAL_BETWEEN_LIGHT) {
                batch.draw(foodTexture, position.x, position.y, 16, 16, 0.74f, 0, 0.98f, 1);

            }
            if (Counter > 4 * INTERVAL_BETWEEN_LIGHT)
                Counter = 0;
            Counter++;
        }

        font.draw(batch,"Food.Eat = " + CountEat, 50, 360);
        font.draw(batch,"Food.Bingo! = " + CountDoubleFood, 50, 340);
        font.draw(batch,"F.Chance = " + (double)CountDoubleFood/CountEat, 50, 320);
        font.draw(batch,"F.pos.x = " + getPosition().x, 50, 300);
        font.draw(batch,"F.pos.y = " + getPosition().y, 50, 280);
    }

    @Override
    public void recreate() {
        tmp.x = getPosition().x;
       if( (int) (Math.random()*1000)>=(1000-ChanceDoubleFood) && tmp.x<680) //750
       {
           active = true;
           position.x = tmp.x+32;
           centr.x = position.x+8;
           centr.y = position.y+8;
           CountDoubleFood++;

       }
       else {
            super.recreate();
       }
       CountEat++;
    }




    /*
    Texture foodTexture;
    int Counter;
    int INTERVAL_BETWEEN_LIGHT=5;
    private Vector2 position,
            centr;
    private int X=100,Y=100;
    boolean active = true;


    public Food()
    {
     foodTexture = new Texture("foodTexture.png");
     Counter = 0;
     position = new Vector2(X,Y);
     centr = new Vector2(X+8,Y+8);
    }

    public void render(SpriteBatch batch)
    {
        if(active) {
            if (Counter > 0 && Counter < INTERVAL_BETWEEN_LIGHT) {
                batch.draw(foodTexture, position.x, position.y, 16, 16, 0, 0, 0.22f, 1);
            }

            if (Counter > INTERVAL_BETWEEN_LIGHT && Counter < 2 * INTERVAL_BETWEEN_LIGHT) {
                batch.draw(foodTexture, position.x, position.y, 16, 16, 0.48f, 0, 0.24f, 1);
            }

            if (Counter > 2 * INTERVAL_BETWEEN_LIGHT && Counter < 3 * INTERVAL_BETWEEN_LIGHT) {
                batch.draw(foodTexture, position.x, position.y, 16, 16, 0.50f, 0, 0.73f, 1);
            }
            if (Counter > 3 * INTERVAL_BETWEEN_LIGHT && Counter < 4 * INTERVAL_BETWEEN_LIGHT) {
                batch.draw(foodTexture, position.x, position.y, 16, 16, 0.74f, 0, 0.98f, 1);

            }
            if (Counter > 4 * INTERVAL_BETWEEN_LIGHT)
                Counter = 0;
            Counter++;
        }
    }

    public Vector2 getPosition()
    {
        return centr;
    }


    public void destroy() {
        active = false;
    }

    public void recreate(){
       // if(Counter >1*INTERVAL_BETWEEN_LIGHT && Counter < 4*INTERVAL_BETWEEN_LIGHT) {
            active = true;
            position.x = 20.0f + (float) Math.random() * 680;
            position.y = 20.f + (float) Math.random() * 490;
            centr.x = position.x+8;
            centr.y = position.y+8;
        //}
    } */
}
