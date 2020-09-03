package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class FoodParent {

    Texture foodTexture;
    int Counter;
    int INTERVAL_BETWEEN_LIGHT;
    public Vector2 position,
            centr;
    boolean active;

    public  FoodParent(String nameTexture,float x,float y)
    {
        foodTexture = new Texture(nameTexture);
        Counter = 0;
        position = new Vector2(x,y);
        centr = new Vector2(x+8,y+8);

    }

    public void render(SpriteBatch batch, BitmapFont font)
    {

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
    }


}
