package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.MainMenu;

public class MyGame extends Game {

	public BitmapFont font;
	public SpriteBatch batch;

	@Override
	public void create() {
		font = new BitmapFont();
		batch = new SpriteBatch();
		this.setScreen(new  MainMenu (this)); //     GameScreen
	}


}
